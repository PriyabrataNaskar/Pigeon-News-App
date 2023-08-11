package com.priyo.news.ui.newsdetails.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.priyo.coreui.mvi.IModel
import com.priyo.news.domain.model.Article
import com.priyo.news.ui.newsdetails.NewsDetailsEffect
import com.priyo.news.ui.newsdetails.NewsDetailsIntent
import com.priyo.news.ui.newsdetails.NewsDetailsState
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
 * Created by Priyabrata Naskar on 11-08-2023.
 */

@HiltViewModel
class NewsDetailsViewModel @Inject constructor() :
    ViewModel(), IModel<NewsDetailsState, NewsDetailsIntent, NewsDetailsEffect> {

    override val intents: Channel<NewsDetailsIntent> =
        Channel(Channel.UNLIMITED)

    private val _uiState =
        MutableStateFlow<NewsDetailsState>(NewsDetailsState.Idle)
    override val uiState: StateFlow<NewsDetailsState>
        get() = _uiState

    private val _uiEffect = MutableSharedFlow<NewsDetailsEffect>()
    override val uiEffect: Flow<NewsDetailsEffect>
        get() = _uiEffect.asSharedFlow()

    private var article: Article? = null

    init {
        handleIntent()
    }

    override fun handleIntent() {
        viewModelScope.launch {
            intents.consumeAsFlow().collect {
                when (it) {
                    is NewsDetailsIntent.Init -> {
                        article = it.article
                        _uiState.emit(NewsDetailsState.Init(it.article))
                    }
                    is NewsDetailsIntent.ShareArticleCta -> {
                        shareArticle()
                    }
                }
            }
        }
    }

    private suspend fun shareArticle() {
        article?.let { article ->
            val text =
                "${article.title} \nDescription:${article.description} \nby- ${article.author} ${article.urlToImage}"
            _uiEffect.emit(NewsDetailsEffect.ShareArticle(text))
        }
    }
}
