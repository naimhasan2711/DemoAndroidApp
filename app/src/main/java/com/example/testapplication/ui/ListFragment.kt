package com.example.testapplication.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapplication.R
import com.example.testapplication.adapter.NewsAdapter
import com.example.testapplication.databinding.FragmentListBinding
import com.example.testapplication.utils.Status
import com.example.testapplication.viewmodel.EmployeeViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private val mainViewModel: EmployeeViewModel by viewModels()
    private lateinit var adapter: NewsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentListBinding.inflate(inflater, container, false)
        setupRecyclerView()
        adapter.setOnItemClickListener {
            Toast.makeText(requireContext(), it.title, Toast.LENGTH_SHORT).show()
            Log.d("list of article>>>",it.toString())
            findNavController().navigate(R.id.action_listFragment_to_detailsFragment,
                bundleOf(
                    Pair(
                        "article", Gson().toJson(
                            it
                        )
                    )
                )
                )
        }

        mainViewModel.resArticle.observe(viewLifecycleOwner, Observer { it ->
            when (it.status) {
                Status.SUCCESS -> {
                    binding.paginationProgressBar.visibility = View.GONE
                    binding.rvBreakingNews.visibility = View.VISIBLE
                    it.data.let {
                        if (it?.totalResults!! >= 0) {
                            it.articles.let { it2 ->
                                adapter.submitList(it2)
                            }
                        } else {
                            Snackbar.make(binding.rootView, "Status = false", Snackbar.LENGTH_SHORT)
                                .show()
                        }
                    }
                }

                Status.LOADING -> {
                    binding.paginationProgressBar.visibility = View.VISIBLE
                    binding.rvBreakingNews.visibility = View.GONE
                }

                Status.ERROR -> {
                    binding.paginationProgressBar.visibility = View.GONE
                    binding.rvBreakingNews.visibility = View.VISIBLE
                    Snackbar.make(binding.rootView, "Something went wrong", Snackbar.LENGTH_SHORT)
                        .show()
                }
            }
        })
        return binding.root
    }

    private fun setupRecyclerView() {
        adapter = NewsAdapter()
        binding.rvBreakingNews.layoutManager = LinearLayoutManager(requireContext())
        binding.rvBreakingNews.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}