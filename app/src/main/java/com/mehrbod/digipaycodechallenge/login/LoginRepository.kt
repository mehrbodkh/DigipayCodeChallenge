package com.mehrbod.digipaycodechallenge.login

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginRepository {
    suspend fun sendLoginRequestAndConfirm(): Boolean = withContext(Dispatchers.IO) {


        true
    }
}