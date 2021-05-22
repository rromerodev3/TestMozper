package com.roy.testmozper.ui

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.roy.testmozper.R
import com.roy.testmozper.databinding.FragmentDetailBinding
import com.roy.testmozper.viewmodel.DetailViewModel
import com.roy.testmozper.viewmodel.MainViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment: Fragment() {

    private val ownViewModel by viewModels<DetailViewModel>()
    private val parentViewModel by activityViewModels<MainViewModel>()

    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDetailBinding.inflate(inflater, container, false)
        binding.viewmodel = ownViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObserves()
    }

    private fun setupObserves() {
        parentViewModel.selectedEmployee.observe(viewLifecycleOwner, { selectedEmployee ->
            ownViewModel.setEmploye(selectedEmployee)
        })
    }

    override fun onResume() {
        super.onResume()
        parentViewModel.setTitle(R.string.string_details)
    }
}

@BindingAdapter("imageUrl", "error")
fun loadImage(view: ImageView, url: String?, error: Drawable) {
    url?.let {
        Picasso.get().load(url).error(error).into(view)
    }
}
