package com.priyo.news.data.mapper

import com.priyo.news.data.source.dto.ArticleDto
import com.priyo.news.data.source.dto.SourceDto
import com.priyo.news.domain.model.Article
import com.priyo.news.domain.model.NewsSource

object NewsMapper {
    fun toArticleList(articleListDto: List<ArticleDto>?): List<Article> {
        return articleListDto?.map { toArticle(it) } ?: emptyList()
    }

    private fun toArticle(articleDto: ArticleDto):
        Article {
        return Article(
            author = articleDto.author,
            content = articleDto.content,
            description = articleDto.description,
            publishedAt = articleDto.publishedAt,
            source = toNewsSource(articleDto.source),
            title = articleDto.title,
            url = articleDto.url,
            urlToImage = articleDto.urlToImage,

        )
    }

    private fun toNewsSource(sourceDto: SourceDto): NewsSource {
        return NewsSource(
            id = sourceDto.id,
            name = sourceDto.name,
        )
    }
}
