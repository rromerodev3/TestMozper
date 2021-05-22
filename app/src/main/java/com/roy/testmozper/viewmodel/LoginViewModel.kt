package com.roy.testmozper.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roy.testmozper.db.model.User
import com.roy.testmozper.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
        private val userRepository: UserRepository
) : ViewModel() {

    val userEmail = ObservableField<String>()
    val userPassword = ObservableField<String>()

    fun doLogin() {
        viewModelScope.launch {
            if (validateFields(userEmail.get(), userPassword.get())) {
                userRepository.insert(User(0, userEmail.get()!!))
            }
        }
    }

    fun validateFields(email: String?, password: String?): Boolean {
        return !(email == null || password == null)
    }
}