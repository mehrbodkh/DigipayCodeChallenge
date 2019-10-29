package com.mehrbod.digipaycodechallenge.track

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mehrbod.digipaycodechallenge.MainActivity.Companion.SHARED_PREFERENCES_NAME
import com.mehrbod.digipaycodechallenge.MainActivity.Companion.TOKEN
import com.mehrbod.digipaycodechallenge.api.LatestReleasesResponse
import com.mehrbod.digipaycodechallenge.api.isNetworkAvailable
import kotlinx.coroutines.launch
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import java.lang.Exception

class TrackViewModel(val app: Application) : AndroidViewModel(app), KoinComponent {
    private val repository: TrackRepository by inject()

    val latestTracks = MutableLiveData<LatestReleasesResponse?>()
    val error = MutableLiveData<String?>()

    fun doHandleInitialTracks() {
        val sharedPreferences = app.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        viewModelScope.launch {
            sharedPreferences.getString(TOKEN, null)?.let {
                try {
                    if (!isNetworkAvailable(app)) throw Exception("Check internet connection.")
                    val response = repository.fetchNewReleases(it)
                    latestTracks.value = response
                } catch (e: Exception) {
                    error.value = e.message
                }
            }
        }
    }
}