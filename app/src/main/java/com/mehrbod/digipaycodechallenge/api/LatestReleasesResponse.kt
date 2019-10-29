package com.mehrbod.digipaycodechallenge.api


import com.google.gson.annotations.SerializedName

data class LatestReleasesResponse(
    @SerializedName("albums")
    val albums: Albums
) {
    data class Albums(
        @SerializedName("href")
        val href: String,
        @SerializedName("items")
        val items: List<Item>,
        @SerializedName("limit")
        val limit: Int,
        @SerializedName("next")
        val next: String,
        @SerializedName("offset")
        val offset: Int,
        @SerializedName("previous")
        val previous: Any?,
        @SerializedName("total")
        val total: Int
    ) {
        data class Item(
            @SerializedName("album_type")
            val albumType: String,
            @SerializedName("artists")
            val artists: List<Artist>,
            @SerializedName("available_markets")
            val availableMarkets: List<String>,
            @SerializedName("external_urls")
            val externalUrls: ExternalUrls,
            @SerializedName("href")
            val href: String,
            @SerializedName("id")
            val id: String,
            @SerializedName("images")
            val images: List<Image>,
            @SerializedName("name")
            val name: String,
            @SerializedName("release_date")
            val releaseDate: String,
            @SerializedName("release_date_precision")
            val releaseDatePrecision: String,
            @SerializedName("total_tracks")
            val totalTracks: Int,
            @SerializedName("type")
            val type: String,
            @SerializedName("uri")
            val uri: String
        ) {
            data class ExternalUrls(
                @SerializedName("spotify")
                val spotify: String
            )

            data class Artist(
                @SerializedName("external_urls")
                val externalUrls: ExternalUrls,
                @SerializedName("href")
                val href: String,
                @SerializedName("id")
                val id: String,
                @SerializedName("name")
                val name: String,
                @SerializedName("type")
                val type: String,
                @SerializedName("uri")
                val uri: String
            ) {
                data class ExternalUrls(
                    @SerializedName("spotify")
                    val spotify: String
                )
            }

            data class Image(
                @SerializedName("height")
                val height: Int,
                @SerializedName("url")
                val url: String,
                @SerializedName("width")
                val width: Int
            )
        }
    }
}