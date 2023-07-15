package com.priyo.news.domain.usecase

import com.priyo.core.domain.ErrorConstants.SOMETHING_WENT_WRONG
import com.priyo.core.domain.coroutine.CoroutineDispatcherProvider
import com.priyo.core.domain.executeSafeCall
import com.priyo.core.result.NetworkResult
import com.priyo.core.result.UiResult
import com.priyo.news.domain.model.Article
import com.priyo.news.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FetchTopNewsUseCase @Inject constructor(
    private val repository: NewsRepository,
    private val coroutineContextController: CoroutineDispatcherProvider,
) {
    suspend operator fun invoke(
        pageNumber: Int,
        countryCode: String,
    ): Flow<UiResult<List<Article>, Exception>> =
        flow {
            executeSafeCall(
                block = {
                    when (
                        val result =
                            repository.getTopNews(
                                pageNumber,
                                countryCode,
                            )
                    ) {
                        is NetworkResult.Success -> {
                            emit(UiResult.Success(result.data))
                        }

                        is NetworkResult.Error -> {
                            val errorMessage = result.throwable?.message.orEmpty()
                            emit(
                                UiResult.Error(
                                    Exception(
                                        errorMessage,
                                    ),
                                ),
                            )
                        }
                    }
                },
                error = {
                    emit(UiResult.Error(it))
                },
            )
        }.catch {
            emit(
                UiResult.Error(
                    Exception(
                        message = SOMETHING_WENT_WRONG,
                    ),
                ),
            )
        }.flowOn(coroutineContextController.dispatcherIO)
}
