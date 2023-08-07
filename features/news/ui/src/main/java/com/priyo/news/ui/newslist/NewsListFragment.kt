package com.priyo.news.ui.newslist

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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.priyo.coreui.mvi.MVIView
import com.priyo.news.domain.model.Article
import com.priyo.news.ui.databinding.FragmentNewsListBinding
import com.priyo.news.ui.newslist.adapter.NewsAdapter
import com.priyo.news.ui.newslist.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class NewsListFragment @Inject constructor() :
    Fragment(),
    MVIView<NewsIntent, NewsState, NewsEffect> {

    private var _binding: FragmentNewsListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val newsAdapter by lazy { NewsAdapter() }
    private val viewModel: NewsViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentNewsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observerInView(
            lifecycleOwner = viewLifecycleOwner,
            uiState = viewModel.uiState,
            uiEffect = viewModel.uiEffect,
        )
        setAdapter()
        initOnClickListeners()
        triggerEvent(NewsIntent.Init)
    }

    private fun initOnClickListeners() {
        newsAdapter.onItemClick = { article, position ->
            triggerEvent(
                NewsIntent.ArticleItemCta(
                    article,
                    position,
                ),
            )
        }
        newsAdapter.onShareItemClick = { article ->
            triggerEvent(
                NewsIntent.ShareArticleCta(
                    article,
                ),
            )
        }
    }

    private fun showError() {
        binding.errorAnim.apply {
            visibility = View.VISIBLE
            playAnimation()
        }
    }

    private fun hideError() {
        binding.errorAnim.apply {
            cancelAnimation()
            visibility = View.GONE
        }
    }

    private fun showProgressBar() {
        binding.shimmerView.apply {
            visibility = View.VISIBLE
            startShimmer()
        }
    }

    private fun hideProgressBar() {
        binding.shimmerView.apply {
            stopShimmer()
            visibility = View.GONE
        }
    }

    private fun setAdapter() {
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(context, 1)
            adapter = newsAdapter
        }
    }

    private fun setArticles(list: List<Article>) {
        newsAdapter.setData(list)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun observeUiState(state: NewsState) {
        when (state) {
            is NewsState.Init -> {
                hideProgressBar()
                hideError()
                setArticles(state.articles)
            }

            is NewsState.Error -> {
                showError()
                hideProgressBar()
                state.message.let { message ->
                    // todo: show error snackbar
                }
            }

            is NewsState.Idle -> {}
            is NewsState.Loading -> {
                showProgressBar()
                hideError()
            }
        }
    }

    override fun observeUiEffect(effect: NewsEffect) {
        when (effect) {
            is NewsEffect.NavigateToNewsDetails -> {
                val action = NewsListFragmentDirections.actionNewsListFragmentToNewsDetailsFragment(
                    article = effect.article,
                )
                findNavController().navigate(action)
            }
            is NewsEffect.ShareArticle -> {
                shareArticle(effect.article)
            }
        }
    }

    private fun shareArticle(article: Article) {
        Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(
                Intent.EXTRA_TEXT,
                "${article.title} \nDescription:${article.description} \nby- ${article.author} ${article.urlToImage}",
            )
        }.let {
            startActivity(Intent.createChooser(it, "Share News With"))
        }
    }

    override fun triggerEvent(onEvent: NewsIntent) {
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
