package com.priyo.news.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.priyo.coreui.mvi.MVIView
import com.priyo.news.domain.model.Article
import com.priyo.news.ui.adapter.NewsAdapter
import com.priyo.news.ui.databinding.FragmentNewsListBinding
import com.priyo.news.ui.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class NewsListFragment @Inject constructor() :
    Fragment(),
    MVIView<NewsIntent, NewsState, NewsEffect> {
    companion object {
        const val TAG: String = "NewsListFragment"
    }
    private var _binding: FragmentNewsListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    // Member variables
    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: NewsAdapter? = null

    private val viewModel: NewsViewModel by activityViewModels()
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
        // setUpAdapter()
        initOnClickListeners()
        triggerEvent(NewsIntent.Init)
    }

    private fun initOnClickListeners() {
        // binding.buttonFirst.setOnClickListener {}
    }

    private fun showError() {
        binding.errorAnim.visibility = View.VISIBLE
        binding.errorAnim.playAnimation()
    }

    private fun hideError() {
        binding.errorAnim.cancelAnimation()
        binding.errorAnim.visibility = View.GONE
    }

    private fun showProgressBar() {
        binding.shimmerView.visibility = View.VISIBLE
        binding.shimmerView.startShimmer()
    }

    private fun hideProgressBar() {
        binding.shimmerView.stopShimmer()
        binding.shimmerView.visibility = View.GONE
    }

    private fun setRecyclerView(list: List<Article>) {
        // Initialize the RecyclerView.
        mRecyclerView = binding.recyclerView
        // Set the Layout Manager.
        mRecyclerView?.layoutManager = GridLayoutManager(context, 1)

        mAdapter = context?.let { it1 -> NewsAdapter(list, it1) }
        mRecyclerView?.adapter = mAdapter
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
                state.articles.let {
                    mAdapter = NewsAdapter(it, requireContext())
                    setRecyclerView(it)
                }
            }
            is NewsState.Error -> {
                showError()
                hideProgressBar()
                state.message.let { message ->
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
                    authorName = effect.article.author,
                    newsTitle = effect.article.title,
                    newsDescription = effect.article.description,
                    newsImageResource = effect.article.urlToImage,
                    newsPublishTime = effect.article.publishedAt,
                    content = effect.article.content,
                )
                findNavController().navigate(action)
            }
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
