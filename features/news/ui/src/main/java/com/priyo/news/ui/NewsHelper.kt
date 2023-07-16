package com.priyo.news.ui

import com.priyo.coreui.mvi.IEffect
import com.priyo.coreui.mvi.IIntent
import com.priyo.coreui.mvi.IState
import com.priyo.news.domain.model.Article

sealed class NewsEffect : IEffect {
    data class NavigateToNewsDetails(val article: Article) : NewsEffect()
}

sealed class NewsState : IState {
    object Idle : NewsState()
    object Loading : NewsState()
    data class Error(val message: String) : NewsState()
    data class Init(val filters: List<Article>) : NewsState()
}

sealed class NewsIntent : IIntent {
    object Init : NewsIntent()
    data class ArticleItemCta(val article: Article, val position: Int) : NewsIntent()
}
