package com.mehrbod.digipaycodechallenge.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class LoginViewModel : ViewModel(), KoinComponent {
    val isLoggedIn = MutableLiveData<Boolean>().apply { value = false }

    private val repository: LoginRepository by inject()

    fun doHandleLogin(username: String, password: String) {
        viewModelScope.launch {
            val loggedIn = repository.sendLoginRequestAndConfirm(username, password)
            isLoggedIn.value = loggedIn
        }
    }
}

