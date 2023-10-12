package com.example.testapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.testapplication.R
import com.example.testapplication.databinding.FragmentDetailsBinding
import com.example.testapplication.databinding.FragmentListBinding
import com.example.testapplication.models.Article
import com.google.gson.Gson


class DetailsFragment : Fragment() {


    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var articleObject: Article

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val articleJson = arguments?.getString("article")
        articleJson?.let {
            articleObject = Gson().fromJson(articleJson, Article::class.java)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        Glide.with(this).load(articleObject.urlToImage).into(binding.ivTitleImage)
        binding.tvAuthor.text = articleObject.author
        binding.tvDate.text = articleObject.publishedAt
        binding.tvDescription.text = articleObject.description
        binding.tvTitle.text = articleObject.title
        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}