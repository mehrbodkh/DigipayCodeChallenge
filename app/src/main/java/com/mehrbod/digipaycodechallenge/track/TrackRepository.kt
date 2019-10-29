package com.mehrbod.digipaycodechallenge.track

import com.google.gson.Gson
import com.mehrbod.digipaycodechallenge.api.LatestReleasesResponse
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TrackRepository {
    companion object {
        const val BASE_URL = "https://api.spotify.com/v1"
    }

    private val client = OkHttpClient()
    private val gson = Gson()

    suspend fun fetchNewReleases(token: String): LatestReleasesResponse? = withContext(Dispatchers.IO) {
        val request = Request.Builder()
            .header("Authorization", "Bearer $token")
            .url("$BASE_URL/browse/new-releases")
            .build()

        val response = client.newCall(request).execute().body().string()
        gson.fromJson(response, LatestReleasesResponse::class.java)
    }
}