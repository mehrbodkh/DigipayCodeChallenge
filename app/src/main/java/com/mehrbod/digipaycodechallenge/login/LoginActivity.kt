package com.mehrbod.digipaycodechallenge.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.mehrbod.digipaycodechallenge.R
import com.mehrbod.digipaycodechallenge.track.TrackActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupViews()
    }

    private fun setupViews() {
        setupLoginButton()
        bindLogin()
    }

    private fun setupLoginButton() {
        login_button.setOnClickListener {
            if (isUserPassFilled()) {
                viewModel.doHandleLogin(
                    login_user_name.text.toString(),
                    login_password.text.toString()
                )
            } else {
                Toast.makeText(this, "Please enter your username and password", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun isUserPassFilled(): Boolean {
        return login_user_name.text.toString().isNotEmpty() && login_password.text.toString().isNotEmpty()
    }

    private fun bindLogin() {
        viewModel.isLoggedIn.observe(this,
            Observer { isLoggedIn ->
                if (isLoggedIn) {
                    finish()
                    startActivity(Intent(this, TrackActivity::class.java))
                }
            })
    }
}
