package com.example.testapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.testapplication.R


class DetailsFragment : Fragment() {

    val args: DetailsFragmentArgs by navArgs()
    private lateinit var name:TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_details, container, false)
        val article = args.employee
        name = view.findViewById(R.id.textView_name)
        name.text = article.employee_name
        name.setOnClickListener {
            findNavController().navigate(R.id.action_detailsFragment_to_listFragment)
        }
        return view
    }


}