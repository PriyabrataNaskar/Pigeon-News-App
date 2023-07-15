package com.priyo.pigeon.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.priyo.pigeon.model.repository.NewsRepository

/**
 * Created by Priyabrata Naskar on 05-04-2022.
 */
class NewsViewModelProviderFactory(val newsRepository: NewsRepository, val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(newsRepository = newsRepository, application = application) as T
    }
}
