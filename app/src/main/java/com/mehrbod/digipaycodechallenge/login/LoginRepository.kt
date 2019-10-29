package com.mehrbod.digipaycodechallenge.login

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginRepository {
    suspend fun sendLoginRequestAndConfirm(
        username: String,
        password: String
    ): Boolean = withContext(Dispatchers.IO) {


        true
    }
}