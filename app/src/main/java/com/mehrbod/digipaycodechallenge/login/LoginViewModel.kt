package com.mehrbod.digipaycodechallenge.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LoginViewModel(
    val repository: LoginRepository
) : ViewModel() {
    val isLoggedIn = MutableLiveData<Boolean>().apply { value = false }

    fun doHandleLogin(username: String, password: String) {
        viewModelScope.launch {
            val loggedIn = TODO("Make login request")
            isLoggedIn.value = loggedIn
        }
    }
}

