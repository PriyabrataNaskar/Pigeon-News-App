package com.priyo.news.ui.newsdetails

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.priyo.news.domain.model.Article
import com.priyo.news.ui.R
import com.priyo.news.ui.databinding.FragmentNewsDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NewsDetailsFragment @Inject constructor() : Fragment() {

    private var _binding: FragmentNewsDetailsBinding? = null
    private val args: NewsDetailsFragmentArgs by navArgs()
    private lateinit var article: Article

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        args.article?.let {
            this.article = it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentNewsDetailsBinding.inflate(inflater, container, false)

        binding.shareButton.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND)

            // Intent shareIntent = new Intent();
            shareIntent.type = "text/plain"
            shareIntent.putExtra(
                Intent.EXTRA_TEXT,
                "${article.title} \nDescription:${article.description} \nby- ${article.author} ${article.urlToImage}",
            )
            context?.startActivity(Intent.createChooser(shareIntent, "Share News With"))
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setInitialUI()
    }

    private fun setInitialUI() {
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
}
