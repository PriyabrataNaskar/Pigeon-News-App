package com.priyo.news.ui.newsdetails

import com.priyo.coreui.mvi.IEffect
import com.priyo.coreui.mvi.IIntent
import com.priyo.coreui.mvi.IState
import com.priyo.news.domain.model.Article

sealed class NewsDetailsEffect : IEffect {
    data class ShareArticle(val sharingText: String) : NewsDetailsEffect()
}

sealed class NewsDetailsState : IState {
    object Idle : NewsDetailsState()
    object Loading : NewsDetailsState()
    data class Error(val message: String) : NewsDetailsState()
    data class Init(val article: Article) : NewsDetailsState()
}

sealed class NewsDetailsIntent : IIntent {
    data class Init(val article: Article) : NewsDetailsIntent()
    object ShareArticleCta : NewsDetailsIntent()
}
