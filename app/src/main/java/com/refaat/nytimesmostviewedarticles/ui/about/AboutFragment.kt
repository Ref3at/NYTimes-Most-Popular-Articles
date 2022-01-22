package com.refaat.nytimesmostviewedarticles.ui.about

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.refaat.nytimesmostviewedarticles.databinding.FragmentAboutBinding


class AboutFragment : Fragment() {

    private lateinit var _binding: FragmentAboutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        val view = _binding.root
        return view
    }
}