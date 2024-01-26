package com.priyo.news.ui.analytics

import com.priyo.core.domain.events.IAnalyticsLogger
import com.priyo.news.domain.model.Article
import com.priyo.news.ui.analytics.NewsListEventsLogger.Actions.CHOOSE_ARTICLE_SHARE
import com.priyo.news.ui.analytics.NewsListEventsLogger.Properties.IS_SCREEN
import com.priyo.news.ui.analytics.NewsListEventsLogger.Properties.SOURCE
import com.priyo.news.ui.analytics.NewsListEventsLogger.Properties.SOURCE_HOME
import com.priyo.news.ui.analytics.NewsListEventsLogger.Values.IS_SCREEN_TRUE
import javax.inject.Inject

class NewsListEventsLogger @Inject constructor(
	private val analyticsLogger: IAnalyticsLogger,
) {
	fun logArticleShare(
		article: Article,
	) {
		val mapPairs = getArticleMap(article)
		mapPairs[IS_SCREEN] = IS_SCREEN_TRUE
		mapPairs[SOURCE] = SOURCE_HOME

		analyticsLogger.trackEvent(CHOOSE_ARTICLE_SHARE, mapPairs)
	}

	fun logArticleOpen(
		article: Article,
	) {
		val mapPairs = getArticleMap(article)

		mapPairs[IS_SCREEN] = IS_SCREEN_TRUE
		mapPairs[SOURCE] = SOURCE_HOME

		analyticsLogger.trackEvent(Actions.CHOOSE_ARTICLE_OPEN, mapPairs)
	}

	private fun getArticleMap(
		article: Article
	): HashMap<String, Any> {
		val mapPairs = HashMap<String, Any>()
		article.author?.let {
			mapPairs[Values.AUTHOR] = it
		}
		article.content?.let {
			mapPairs[Values.CONTENT] = it
		}

		article.description?.let {
			mapPairs[Values.DESCRIPTION] = it
		}

		article.publishedAt?.let {
			mapPairs[Values.PUBLISHED_AT] = it
		}

		article.source.name?.let {
			mapPairs[Values.NEWS_SOURCE] = it
		}

		article.title?.let {
			mapPairs[Values.NEWS_TITLE] = it
		}

		article.url?.let {
			mapPairs[Values.NEWS_URL] = it
		}

		article.urlToImage?.let {
			mapPairs[Values.NEWS_THUMBNAIL] = it
		}

		return mapPairs
	}

	object Events {
		const val HOME = "Home"
	}


	object Properties {
		const val SOURCE_HOME = "home"
		const val SOURCE = "source" //todo move to core module common const
		const val IS_SCREEN = "is_screen" //todo move to core module common const
	}

	object Values {
		const val AUTHOR = "author"
		const val CONTENT = "content"
		const val DESCRIPTION = "description"
		const val PUBLISHED_AT = "published_at"
		const val NEWS_SOURCE = "news_source"
		const val NEWS_TITLE = "news_title"
		const val NEWS_URL = "news_url"
		const val NEWS_THUMBNAIL = "news_thumbnail"
		const val IS_SCREEN_TRUE = true //todo move to core module common const
	}

	object Actions {
		const val CHOOSE_ARTICLE_SHARE = "share_news"
		const val CHOOSE_ARTICLE_OPEN = "open_article"
	}

}