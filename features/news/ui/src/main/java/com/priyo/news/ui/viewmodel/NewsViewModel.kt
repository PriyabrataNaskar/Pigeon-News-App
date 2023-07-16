package com.priyo.news.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.priyo.core.domain.ErrorConstants.SOMETHING_WENT_WRONG
import com.priyo.core.result.UiResult
import com.priyo.coreui.mvi.IModel
import com.priyo.news.domain.usecase.FetchTopNewsUseCase
import com.priyo.news.ui.NewsEffect
import com.priyo.news.ui.NewsIntent
import com.priyo.news.ui.NewsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Priyabrata Naskar on 15-07-2023.
 */

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val fetchTopNewsUseCase: FetchTopNewsUseCase,
) : ViewModel(), IModel<NewsState, NewsIntent, NewsEffect> {

    override val intents: Channel<NewsIntent> =
        Channel(Channel.UNLIMITED)

    private val _uiState =
        MutableStateFlow<NewsState>(NewsState.Idle)
    override val uiState: StateFlow<NewsState>
        get() = _uiState

    private val _uiEffect = MutableSharedFlow<NewsEffect>()
    override val uiEffect: Flow<NewsEffect>
        get() = _uiEffect.asSharedFlow()

    init {
        handleIntent()
    }

    override fun handleIntent() {
        viewModelScope.launch {
            intents.consumeAsFlow().collect {
                when (it) {
                    NewsIntent.Init -> {
                        getTopNews()
                    }
                    is NewsIntent.ArticleItemCta -> _uiEffect.emit(NewsEffect.NavigateToNewsDetails(it.article))
                }
            }
        }
    }

    private suspend fun getTopNews() {
        viewModelScope.launch {
            _uiState.emit(NewsState.Loading)
            fetchTopNewsUseCase.invoke(
                1,
                "us",
            ).collect {
                when (it) {
                    is UiResult.Error -> {
                        _uiState.emit(
                            NewsState.Error(
                                it.error.message ?: SOMETHING_WENT_WRONG,
                            ),
                        )
                    }

                    is UiResult.Success -> {
                        _uiState.emit(
                            NewsState.Init(
                                it.data.map { it2 ->
                                    it2.copy()
                                },
                            ),
                        )
                    }
                }
            }
            _uiState.emit(NewsState.Idle)
        }
    }

    private fun hasInternetConnection() {
		/*val connectivityManager = getApplication<Application>().getSystemService(
			Context.CONNECTIVITY_SERVICE,
		) as ConnectivityManager
		val activeNetwork = connectivityManager.activeNetwork ?: return false
		val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
		return when {
			capabilities.hasTransport(TRANSPORT_WIFI) -> true
			capabilities.hasTransport(TRANSPORT_CELLULAR) -> true
			capabilities.hasTransport(TRANSPORT_ETHERNET) -> true
			else -> false
		}*/
    }
}