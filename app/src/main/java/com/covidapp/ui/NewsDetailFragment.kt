package com.covidapp.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.covidapp.databinding.FragmentNewsDetailBinding

/**
 * @author emre.memis@ovidos.com
 */

class NewsDetailFragment : Fragment() {
    private lateinit var binding: FragmentNewsDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            binding.news = NewsDetailFragmentArgs.fromBundle(it).news
            binding.image = NTV_IMAGE_URL
        }

        binding.fragmentNewsDetailBackButton
            .setOnClickListener {
                it.findNavController().navigateUp()
            }

        binding.fragmentNewsDetailSourceButton
            .setOnClickListener { _ ->
                arguments?.let {
                    val uri = Uri.parse(NewsDetailFragmentArgs.fromBundle(it).news.url)
                    Intent(Intent.ACTION_VIEW, uri)
                        .apply { flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP }
                        .also { intent -> startActivity(intent) }
                }
            }
    }

    companion object {
        private const val NTV_IMAGE_URL = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/66/NTV_Symbol_1953.svg/1200px-NTV_Symbol_1953.svg.png"
    }
}