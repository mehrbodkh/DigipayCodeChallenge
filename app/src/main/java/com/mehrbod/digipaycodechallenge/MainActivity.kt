package com.mehrbod.digipaycodechallenge

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mehrbod.digipaycodechallenge.login.LoginActivity
import com.mehrbod.digipaycodechallenge.track.TrackActivity
import com.mehrbod.digipaycodechallenge.api.SpotifyRepository
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.module


class MainActivity : AppCompatActivity() {

    private val applicationModule = module {
        single { SpotifyRepository() }
    }

    companion object {
        const val SHARED_PREFERENCES_NAME = "DIGIPAY_CODE_CHALLENGE"
        const val IS_LOGGED_IN = "is_logged_in_already"
        const val LAST_LOGIN = "last_login"
        const val TOKEN = "token"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        startKoin(applicationContext, listOf(applicationModule))
        openNeededActivity(sharedPreferences)
    }

    private fun openNeededActivity(preferences: SharedPreferences) {
        val isLoggedIn = preferences.getBoolean(IS_LOGGED_IN, false)
        val lastLogin = preferences.getLong(LAST_LOGIN, 0L)
        if (isLoggedIn && !isTokenExpired(lastLogin)) {
            openTrackActivity()
        } else {
            openLoginActivity()
        }
        finish()

    }

    private fun isTokenExpired(lastLogin: Long) = (System.currentTimeMillis() - lastLogin) >= 60 * 60 * 1000

    private fun openTrackActivity() {
        startActivity(Intent(this, TrackActivity::class.java))
    }

    private fun openLoginActivity() {
        startActivity(Intent(this, LoginActivity::class.java))
    }
}
