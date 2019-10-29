package com.mehrbod.digipaycodechallenge

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mehrbod.digipaycodechallenge.login.LoginActivity
import com.mehrbod.digipaycodechallenge.login.LoginRepository
import com.mehrbod.digipaycodechallenge.track.TrackActivity
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.module

class MainActivity : AppCompatActivity() {

    private val applicationModule = module {
        single { LoginRepository() }
    }

    companion object {
        const val SHARED_PREFERENCES_NAME = "DIGIPAY_CODE_CHALLENGE"
        const val IS_LOGGED_IN = "is_logged_in_already"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        startKoin(applicationContext, listOf(applicationModule))
        openNeededActivity(sharedPreferences)
        finish()
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
        startActivity(Intent(this, TrackActivity::class.java))
    }

    private fun openLoginActivity() {
        startActivity(Intent(this, LoginActivity::class.java))
    }
}
