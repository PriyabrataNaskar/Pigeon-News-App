package com.priyo.news.ui.newslist.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.priyo.news.domain.model.Article

/**
 * Created by Priyabrata Naskar on 04-04-2022.
 */
class NewsAdapter : RecyclerView.Adapter<NewsViewHolder>() {

    var onItemClick: ((Article, Int) -> Unit)? = null
    var onShareItemClick: ((Article) -> Unit)? = null
    private var articles: List<Article> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder.create(
            parent,
            onItemClick,
            onShareItemClick,
        )
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(
        holder: NewsViewHolder,
        position: Int,
    ) {
        holder.bindData(articles[position], position)
    }

    fun setData(items: List<Article>) {
        items.let {
            val diffUtil =
                NewsDiffUtils(articles, items)
            val diffResult = DiffUtil.calculateDiff(diffUtil)
            this.articles = it
            diffResult.dispatchUpdatesTo(this)
        }
    }
}
