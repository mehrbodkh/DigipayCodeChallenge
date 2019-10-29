package com.mehrbod.digipaycodechallenge.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.mehrbod.digipaycodechallenge.MainActivity
import com.mehrbod.digipaycodechallenge.MainActivity.Companion.IS_LOGGED_IN
import com.mehrbod.digipaycodechallenge.MainActivity.Companion.LAST_LOGIN
import com.mehrbod.digipaycodechallenge.MainActivity.Companion.TOKEN
import com.mehrbod.digipaycodechallenge.R
import com.mehrbod.digipaycodechallenge.track.TrackActivity
import com.spotify.sdk.android.authentication.AuthenticationClient
import com.spotify.sdk.android.authentication.AuthenticationRequest
import com.spotify.sdk.android.authentication.AuthenticationResponse
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    companion object {
        const val REQUEST_CODE = 1337
        const val REDIRECT_URI = "https://mydigipay.com/"
        const val CLIENT_ID = "ba05b9cd59634cefa8493ac961d76ed6"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupViews()
    }

    private fun setupViews() {
        setupLoginButton()
    }

    private fun setupLoginButton() {
        login_button.setOnClickListener {
            openLoginSDK()
        }
    }

    private fun openLoginSDK() {
        val builder = AuthenticationRequest.Builder(CLIENT_ID, AuthenticationResponse.Type.TOKEN, REDIRECT_URI)

        builder.setScopes(arrayOf("streaming"))
        val request = builder.build()

        AuthenticationClient.openLoginActivity(this, REQUEST_CODE, request)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)

        if (requestCode == REQUEST_CODE) {
            val response = AuthenticationClient.getResponse(resultCode, intent)

            when (response.type) {
                AuthenticationResponse.Type.TOKEN -> {
                    doHandleLoginEvent(response.accessToken)
                }

                AuthenticationResponse.Type.ERROR -> {
                    Toast.makeText(this, "Cannot log in.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun doHandleLoginEvent(token: String) {
        val sharedPreferences = getSharedPreferences(MainActivity.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        sharedPreferences.edit(commit = true) {
            putBoolean(IS_LOGGED_IN, true)
            putLong(LAST_LOGIN, System.currentTimeMillis())
            putString(TOKEN, token)
        }
        finish()
        startActivity(Intent(this, TrackActivity::class.java))
    }
}
