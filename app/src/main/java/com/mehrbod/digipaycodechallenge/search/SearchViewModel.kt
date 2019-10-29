package com.mehrbod.digipaycodechallenge.search

import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mehrbod.digipaycodechallenge.MainActivity
import com.mehrbod.digipaycodechallenge.MainActivity.Companion.TOKEN
import com.mehrbod.digipaycodechallenge.api.SearchResult
import com.mehrbod.digipaycodechallenge.api.SpotifyRepository
import com.mehrbod.digipaycodechallenge.api.isNetworkAvailable
import kotlinx.coroutines.launch
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class SearchViewModel(val app: Application) : AndroidViewModel(app), KoinComponent {
    private val repository: SpotifyRepository by inject()

    val searchResult = MutableLiveData<SearchResult?>()
    val error = MutableLiveData<String>()


    fun doHandleSearch(query: String) {
        val sharedPreferences = app.getSharedPreferences(MainActivity.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        sharedPreferences.getString(TOKEN, null)?.let {
            try {
                if (!isNetworkAvailable(app)) {
                    throw Exception("Check internet connection.")
                }
                viewModelScope.launch {
                    val result = repository.doHandleSearch(query, it)
                    searchResult.value = result
                }
            } catch (e: java.lang.Exception) {
                error.value = e.message
            }
        }
    }
}