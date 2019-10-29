package com.mehrbod.digipaycodechallenge.track

import android.util.Log
import com.mehrbod.digipaycodechallenge.api.LatestReleasesResponse
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TrackRepository {
    private val client = OkHttpClient()

    suspend fun fetchNewReleases(token: String): LatestReleasesResponse? = withContext(Dispatchers.IO) {
        val request = Request.Builder()
            .header("Authorization", "Bearer $token")
            .url("\thttps://api.spotify.com/v1/browse/new-releases")
            .build()

        val response = client.newCall(request).execute()
        Log.d("MehrbodLog", response.body().string())
        null
    }
}