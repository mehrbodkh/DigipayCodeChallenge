package com.mehrbod.digipaycodechallenge.track

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mehrbod.digipaycodechallenge.api.LatestReleasesResponse
import kotlinx.coroutines.launch
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class TrackViewModel : ViewModel(), KoinComponent {
    private val repository: TrackRepository by inject()

    val latestTracks = MutableLiveData<LatestReleasesResponse?>()

    init {
        viewModelScope.launch {
            val response = repository.fetchNewReleases()
            latestTracks.value = response
        }
    }
}