package com.mehrbod.digipaycodechallenge.track

import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mehrbod.digipaycodechallenge.MainActivity.Companion.SHARED_PREFERENCES_NAME
import com.mehrbod.digipaycodechallenge.MainActivity.Companion.TOKEN
import com.mehrbod.digipaycodechallenge.api.LatestReleasesResponse
import com.mehrbod.digipaycodechallenge.api.SpotifyRepository
import com.mehrbod.digipaycodechallenge.api.isNetworkAvailable
import com.squareup.okhttp.Request
import kotlinx.coroutines.launch
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import java.lang.Exception

class TrackViewModel(val app: Application) : AndroidViewModel(app), KoinComponent {
    private val repository: SpotifyRepository by inject()

    val latestTracks = MutableLiveData<LatestReleasesResponse?>()
    val error = MutableLiveData<String?>()

    private var shouldSendRequest = false

    init {
        val receiver = object: BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                if (context != null && isNetworkAvailable(context)) {
                    sendQueuedRequests()
                    shouldSendRequest = false
                }
            }
        }

        app.registerReceiver(receiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    private fun sendQueuedRequests() {
        doHandleInitialTracks()
    }

    fun doHandleInitialTracks() {
        val sharedPreferences = app.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        viewModelScope.launch {
            sharedPreferences.getString(TOKEN, null)?.let {
                try {
                    if (!isNetworkAvailable(app)) {
                        shouldSendRequest = true
                        throw Exception("Check internet connection.")
                    }
                    val response = repository.fetchNewReleases(it)
                    latestTracks.value = response
                } catch (e: Exception) {
                    error.value = e.message
                }
            }
        }
    }
}