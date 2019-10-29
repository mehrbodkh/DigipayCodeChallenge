package com.mehrbod.digipaycodechallenge.track

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.mehrbod.digipaycodechallenge.R
import com.mehrbod.digipaycodechallenge.api.LatestReleasesResponse
import com.mehrbod.digipaycodechallenge.search.SearchActivity
import kotlinx.android.synthetic.main.activity_track.*

class TrackActivity : AppCompatActivity() {
    private val viewModel by viewModels<TrackViewModel>()

    private lateinit var latestReleasesAdapter: LatestReleasesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_track)
        setupViews()
    }

    override fun onResume() {
        super.onResume()
        viewModel.doHandleInitialTracks()
    }

    private fun setupViews() {
        setupSearchButton()
        setupList()
        bindTracks()
    }

    private fun setupSearchButton() {
        search_button.setOnClickListener {
            openSearchActivity()
        }
    }

    private fun openSearchActivity() {
        startActivity(Intent(this, SearchActivity::class.java))
    }

    private fun setupList() {
        latestReleasesAdapter = LatestReleasesAdapter(this)
        tracks_list.apply {
            this.adapter = latestReleasesAdapter
            layoutManager = GridLayoutManager(context, 2)
        }
    }

    private fun bindTracks() {
        viewModel.latestTracks.observe(this,
            Observer { latestReleases ->
                Log.d("MehrbodLog", latestReleases.toString())
                latestReleases?.let { updateList(it) }
            })
    }

    private fun updateList(latestReleasesResponse: LatestReleasesResponse) {
        latestReleasesAdapter.songs = latestReleasesResponse
        latestReleasesAdapter.notifyDataSetChanged()
    }

}
