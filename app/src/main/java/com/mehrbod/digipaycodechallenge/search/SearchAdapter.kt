package com.mehrbod.digipaycodechallenge.search

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mehrbod.digipaycodechallenge.R
import com.mehrbod.digipaycodechallenge.api.SearchResult
import com.mehrbod.digipaycodechallenge.track.SongViewHolder

class SearchAdapter(private val context: Context) : RecyclerView.Adapter<SongViewHolder>() {
    var songs: SearchResult? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val res = LayoutInflater.from(parent.context)
            .inflate(R.layout.song_view_holder, parent, false)

        return SongViewHolder(res)
    }

    override fun getItemCount() = songs?.tracks?.items?.size ?: 0

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val item = songs?.tracks?.items?.get(position)

        Glide
            .with(context)
            .load(item?.album?.images?.get(0)?.url)
            .centerCrop()
            .fitCenter()
            .into(holder.coverArt)

        holder.songName.text = item?.name
        holder.artistsName.text = item?.artists?.joinToString { it.name }
        holder.date.text = item?.album?.releaseDate
    }
}