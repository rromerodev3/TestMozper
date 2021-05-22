package com.roy.testmozper.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.roy.testmozper.R
import com.roy.testmozper.databinding.FragmentLoginBinding
import com.roy.testmozper.viewmodel.LoginViewModel
import com.roy.testmozper.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment: Fragment() {

    private val ownViewModel by viewModels<LoginViewModel>()
    private val parentViewModel by activityViewModels<MainViewModel>()

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.viewmodel = ownViewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener {
            ownViewModel.doLogin()
        }

        setupObserves()
    }

    private fun setupObserves() {
    }

    override fun onResume() {
        super.onResume()
        parentViewModel.setTitle(R.string.string_login)
    }
}