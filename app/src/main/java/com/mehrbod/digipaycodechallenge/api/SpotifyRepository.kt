package com.mehrbod.digipaycodechallenge.api

import com.google.gson.Gson
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.squareup.okhttp.HttpUrl



class SpotifyRepository {
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

    suspend fun doHandleSearch(query: String, token: String): SearchResult? = withContext(Dispatchers.IO) {
        val urlBuilder = HttpUrl.parse("$BASE_URL/search").newBuilder().apply {
            addQueryParameter("q", query)
            addQueryParameter("type", "track")
        }
        val url = urlBuilder.build().toString()
        val request = Request.Builder()
            .header("Authorization", "Bearer $token")
            .url(url)
            .build()

        val response = client.newCall(request).execute().body().string()
        gson.fromJson(response, SearchResult::class.java)
    }
}