package com.priyo.news.ui.newslist.adapter

import androidx.recyclerview.widget.DiffUtil
import com.priyo.news.domain.model.Article

class NewsDiffUtils(
    private val oldList: List<Article>,
    private val newList: List<Article>,
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int,
    ): Boolean {
        return oldList.get(oldItemPosition) == newList.get(
            newItemPosition,
        )
    }

    override fun areContentsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int,
    ): Boolean {
        return when {
            oldList.get(oldItemPosition).author != newList.get(
                newItemPosition,
            ).author -> {
                false
            }

            oldList.get(oldItemPosition).content != newList.get(
                newItemPosition,
            ).content -> {
                false
            }

            oldList.get(oldItemPosition).title != newList.get(
                newItemPosition,
            ).title -> {
                false
            }

            oldList.get(oldItemPosition).url != newList.get(
                newItemPosition,
            ).url -> {
                false
            }

            oldList.get(oldItemPosition).source != newList.get(
                newItemPosition,
            ).source -> {
                false
            }

            else -> true
        }
    }
}
