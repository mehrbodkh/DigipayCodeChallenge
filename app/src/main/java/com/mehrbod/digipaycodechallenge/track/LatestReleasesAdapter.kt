package com.mehrbod.digipaycodechallenge.track

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mehrbod.digipaycodechallenge.R
import com.mehrbod.digipaycodechallenge.api.LatestReleasesResponse

class LatestReleasesAdapter(private val context: Context) : RecyclerView.Adapter<SongViewHolder>() {
    var songs: LatestReleasesResponse? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val res = LayoutInflater.from(parent.context)
            .inflate(R.layout.song_view_holder, parent, false)

        return SongViewHolder(res)
    }

    override fun getItemCount() = songs?.albums?.items?.size ?: 0

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val item = songs?.albums?.items?.get(position)

        Glide
            .with(context)
            .load(item?.images?.get(0)?.url)
            .centerCrop()
            .fitCenter()
            .into(holder.coverArt)

        holder.songName.text = item?.name
        holder.artistsName.text = item?.artists?.joinToString { it.name }
        holder.date.text = item?.releaseDate
    }
}