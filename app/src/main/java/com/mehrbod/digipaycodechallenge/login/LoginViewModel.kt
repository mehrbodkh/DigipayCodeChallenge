package com.mehrbod.digipaycodechallenge.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class LoginViewModel : ViewModel(), KoinComponent {
    val repository: LoginRepository by inject()
    val isLoggedIn = MutableLiveData<Boolean>().apply { value = false }

    fun doHandleLogin(username: String, password: String) {
        viewModelScope.launch {
            val loggedIn = repository.sendLoginRequestAndConfirm()
            isLoggedIn.value = loggedIn
        }
    }
}

