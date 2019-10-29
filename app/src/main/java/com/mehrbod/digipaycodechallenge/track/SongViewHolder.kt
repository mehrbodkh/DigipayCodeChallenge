package com.mehrbod.digipaycodechallenge.track

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.song_view_holder.view.*

class SongViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val coverArt: ImageView = view.cover_art
    val songName: TextView = view.song_name
    val artistsName: TextView = view.artist_name
    val date: TextView = view.date
}