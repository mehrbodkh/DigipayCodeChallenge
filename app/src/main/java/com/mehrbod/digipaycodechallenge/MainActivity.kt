package com.mehrbod.digipaycodechallenge

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mehrbod.digipaycodechallenge.login.LoginRepository
import com.mehrbod.digipaycodechallenge.track.TrackActivity
import com.spotify.sdk.android.authentication.AuthenticationClient
import com.spotify.sdk.android.authentication.AuthenticationRequest
import com.spotify.sdk.android.authentication.AuthenticationResponse
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.module
import com.spotify.sdk.android.authentication.LoginActivity.REQUEST_CODE




class MainActivity : AppCompatActivity() {

    private val applicationModule = module {
        single { LoginRepository() }
    }

    companion object {
        const val SHARED_PREFERENCES_NAME = "DIGIPAY_CODE_CHALLENGE"
        const val IS_LOGGED_IN = "is_logged_in_already"
        private val REQUEST_CODE = 1337
        private val REDIRECT_URI = "yourcustomprotocol://callback"
        private val CLIENT_ID = "ba05b9cd59634cefa8493ac961d76ed6"
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
        val builder = AuthenticationRequest.Builder(CLIENT_ID, AuthenticationResponse.Type.TOKEN, REDIRECT_URI)
        builder.setScopes(arrayOf("streaming"))
        val request = builder.build()

        AuthenticationClient.openLoginActivity(this, REQUEST_CODE, request)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)

        // Check if result comes from the correct activity
        if (requestCode == REQUEST_CODE) {
            val response = AuthenticationClient.getResponse(resultCode, intent)

            when (response.type) {
                // Response was successful and contains auth token
                AuthenticationResponse.Type.TOKEN -> {
                }

                // Auth flow returned an error
                AuthenticationResponse.Type.ERROR -> {
                }
            }// Handle successful response
            // Handle error response
            // Most likely auth flow was cancelled
            // Handle other cases
        }
    }
}
