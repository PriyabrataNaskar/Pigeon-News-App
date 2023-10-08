package com.priyo.news.domain.usecase

import com.priyo.core.domain.ErrorConstants.SOMETHING_WENT_WRONG
import com.priyo.core.domain.coroutine.CoroutineDispatcherProvider
import com.priyo.core.domain.executeSafeCall
import com.priyo.core.result.DomainException
import com.priyo.core.result.Error
import com.priyo.core.result.Success
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
                execute = {
                    when (
                        val result =
                            repository.getTopNews(
                                pageNumber,
                                countryCode,
                            )
                    ) {
                        is Success -> {
                            emit(UiResult.Success(result.data))
                        }

                        is Error -> {
                            val errorMessage = result.message.orEmpty()
                            emit(
                                UiResult.Error(
                                    Exception(
                                        errorMessage,
                                    ),
                                ),
                            )
                        }

                        is DomainException -> {
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
                onError = {
                    emit(UiResult.Error(it))
                },
            )
        }.catch {
            emit(
                UiResult.Error(
                    Exception(SOMETHING_WENT_WRONG),
                ),
            )
        }.flowOn(coroutineContextController.dispatcherIO)
}
