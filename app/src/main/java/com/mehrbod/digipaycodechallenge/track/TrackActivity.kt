package com.mehrbod.digipaycodechallenge.track

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mehrbod.digipaycodechallenge.R
import com.mehrbod.digipaycodechallenge.search.SearchActivity
import kotlinx.android.synthetic.main.activity_track.*

class TrackActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_track)
        setupViews()
    }

    private fun setupViews() {
        setupSearchButton()
    }

    private fun setupSearchButton() {
        search_button.setOnClickListener {
            openSearchActivity()
        }
    }

    private fun openSearchActivity() {
        startActivity(Intent(this, SearchActivity::class.java))
    }

}
