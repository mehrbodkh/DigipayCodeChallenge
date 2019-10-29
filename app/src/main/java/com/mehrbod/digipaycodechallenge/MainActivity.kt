package com.mehrbod.digipaycodechallenge

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mehrbod.digipaycodechallenge.login.LoginActivity

class MainActivity : AppCompatActivity() {

    companion object {
        const val SHARED_PREFERENCES_NAME = "DIGIPAY_CODE_CHALLENGE"
        const val IS_LOGGED_IN = "is_logged_in_already"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        openNeededActivity(sharedPreferences)
    }

    private fun openNeededActivity(preferences: SharedPreferences) {
        preferences.getBoolean(IS_LOGGED_IN, false).let { isLoggedIn ->
            if (isLoggedIn) {
                openTrackActivity()
            } else {
                openLoginActivity()
            }
        }
    }

    private fun openTrackActivity() {

    }

    private fun openLoginActivity() {
        startActivity(Intent(this, LoginActivity::class.java))
    }
}
