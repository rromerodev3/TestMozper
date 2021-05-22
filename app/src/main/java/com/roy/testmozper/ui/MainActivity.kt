package com.roy.testmozper.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.roy.testmozper.R
import com.roy.testmozper.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupObservers()
    }

    private fun setupObservers() {
        viewModel.title.observe(this, { resourceId ->
            resourceId?.let {
                title = getString(resourceId)

                if (resourceId == R.string.string_login)
                    supportActionBar?.hide()
                else
                    supportActionBar?.show()

            } ?: kotlin.run {
                title = ""
            }
        })

        viewModel.loginState.observe(this, { isSessionOpened ->
            if (isSessionOpened) {
                goToFragment(HomeFragment())
            } else {
                goToFragment(LoginFragment())
            }
        })

        viewModel.selectedEmployee.observe(this, {
            goToFragment(DetailFragment(), true)
        })
    }

    private fun goToFragment(fragment: Fragment, addToBackStack: Boolean = false) {
        supportFragmentManager.commit {
            setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out)
            setReorderingAllowed(true)
            replace(R.id.fragment_container_view, fragment)

            if (addToBackStack) {
                addToBackStack(null)
            }
        }
    }
}