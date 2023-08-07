package com.priyo.news.ui.newslist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.priyo.news.domain.model.Article
import com.priyo.news.ui.R
import com.priyo.news.ui.databinding.ItemNewsCardBinding

class NewsViewHolder(
    private val binding: ItemNewsCardBinding,
    private val onItemClick: ((Article, Int) -> Unit)?,
    private val onShareIconClick: ((Article) -> Unit)?,
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(
            viewGroup: ViewGroup,
            onItemClick: ((Article, Int) -> Unit)?,
            onShareIconClick: ((Article) -> Unit)?,
        ): NewsViewHolder {
            return NewsViewHolder(
                ItemNewsCardBinding.inflate(
                    LayoutInflater.from(viewGroup.context),
                    viewGroup,
                    false,
                ),
                onItemClick,
                onShareIconClick,
            )
        }
    }

    fun bindData(article: Article, position: Int) {
        setView(article)
        setOnClickListener(article, position)
    }

    private fun setView(article: Article) {
        with(binding) {
            newsTitle.text = article.title
            authorNameText.text = article.author
            binding.newsImage
            binding.descriptionText.text = article.description
            Glide.with(binding.root.context).load(article.urlToImage)
                .placeholder(R.drawable.ic_placeholder_image)
                .centerCrop().into(newsImage)
        }
    }

    private fun setOnClickListener(article: Article, position: Int) {
        binding.shareButton.setOnClickListener {
            onShareIconClick?.invoke(article)
        }
        binding.root.setOnClickListener {
            onItemClick?.invoke(article, position)
        }
    }
}
