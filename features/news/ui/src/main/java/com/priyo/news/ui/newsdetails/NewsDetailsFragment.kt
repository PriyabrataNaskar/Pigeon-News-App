package com.priyo.news.ui.newsdetails

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.priyo.core.extentions.shareText
import com.priyo.coreui.mvi.MVIView
import com.priyo.news.domain.model.Article
import com.priyo.news.ui.R
import com.priyo.news.ui.databinding.FragmentNewsDetailsBinding
import com.priyo.news.ui.newsdetails.viewmodel.NewsDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class NewsDetailsFragment @Inject constructor() :
    Fragment(),
    MVIView<NewsDetailsIntent, NewsDetailsState, NewsDetailsEffect> {

    private var _binding: FragmentNewsDetailsBinding? = null
    private val args: NewsDetailsFragmentArgs by navArgs()
    private val viewModel: NewsDetailsViewModel by viewModels()

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentNewsDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        args.article?.let {
            triggerEvent(NewsDetailsIntent.Init(it))
        }
        initOnClickListener()
    }

    private fun initOnClickListener() {
        binding.shareButton.setOnClickListener {
            triggerEvent(NewsDetailsIntent.ShareArticleCta)
        }
    }

    private fun setArticle(article: Article) {
        binding.apply {
            titleText.text = article.title
            contentDescription.text = article.description
            newsArticleText.text = article.content
            newsMetaData.text = "${article.author} ${article.publishedAt}"
            Glide.with(binding.root.context).load(article.urlToImage).centerCrop()
                .placeholder(R.drawable.ic_placeholder_image).centerCrop()
                .into(newsImageDetail)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun observeUiState(state: NewsDetailsState) {
        when (state) {
            is NewsDetailsState.Init -> {
                // hideProgressBar()
                // hideError()
                setArticle(state.article)
            }

            is NewsDetailsState.Error -> {
                // showError()
                // hideProgressBar()
                state.message.let { message ->
                    // todo: show error snackbar
                }
            }

            is NewsDetailsState.Idle -> {}
            is NewsDetailsState.Loading -> {
                // showProgressBar()
                // hideError()
            }
        }
    }

    override fun observeUiEffect(effect: NewsDetailsEffect) {
        when (effect) {
            is NewsDetailsEffect.ShareArticle -> {
                shareArticle(effect.sharingText)
            }
        }
    }

    private fun shareArticle(text: String) {
        startActivity(Intent.createChooser(Intent().shareText(text), "Share News With"))
    }

    override fun triggerEvent(onEvent: NewsDetailsIntent) {
        viewLifecycleOwner.lifecycleScope.launch {
            // repeatOnLifecycle launches the block in a new coroutine every time the
            // lifecycle is in the STARTED state (or above) and cancels it when it's STOPPED.
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.intents.send(onEvent)
                }
            }
        }
    }
}
