package com.priyo.news.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsSource(
    val id: String?,
    val name: String?,
) : Parcelable
