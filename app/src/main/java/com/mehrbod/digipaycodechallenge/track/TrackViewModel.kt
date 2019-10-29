package com.mehrbod.digipaycodechallenge.track

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mehrbod.digipaycodechallenge.MainActivity.Companion.SHARED_PREFERENCES_NAME
import com.mehrbod.digipaycodechallenge.MainActivity.Companion.TOKEN
import com.mehrbod.digipaycodechallenge.api.LatestReleasesResponse
import kotlinx.coroutines.launch
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class TrackViewModel(val app: Application) : AndroidViewModel(app), KoinComponent {
    private val repository: TrackRepository by inject()

    val latestTracks = MutableLiveData<LatestReleasesResponse?>()

    fun doHandleInitialTracks() {
        val sharedPreferences = app.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        viewModelScope.launch {
            sharedPreferences.getString(TOKEN, null)?.let {
                val response = repository.fetchNewReleases(it)
                latestTracks.value = response
            }
        }
    }
}