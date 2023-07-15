package com.priyo.pigeon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.priyo.pigeon.adapter.NewsAdapter
import com.priyo.pigeon.databinding.FragmentNewsListBinding
import com.priyo.pigeon.model.data.ArticleResponse
import com.priyo.pigeon.util.Resource
import com.priyo.pigeon.viewmodel.NewsViewModel

class NewsListFragment : Fragment() {
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

    private lateinit var viewModel: NewsViewModel
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
        viewModel = (activity as MainActivity).viewModel

        observeLiveData()
//        binding.buttonFirst.setOnClickListener {
//            findNavController().navigate(R.id.action_NewsListFragment_to_NewsDetailsFragment)
//        }
    }

    private fun observeLiveData() {
        viewModel.topNews.observe(
            viewLifecycleOwner,
        ) { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    hideError()
                    response.data?.let {
                        mAdapter = NewsAdapter(response.data.list, requireContext())
                        setRecyclerView(response.data.list)
                    }
                }

                is Resource.Error -> {
                    showError()
                    hideProgressBar()
                    response.message?.let { message ->
                    }
                }

                is Resource.Loading -> {
                    showProgressBar()
                    hideError()
                }
            }
        }
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

    private fun setRecyclerView(list: List<ArticleResponse>) {
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
}
