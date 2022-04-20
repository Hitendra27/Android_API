package com.example.android_api.view3

import android.telecom.Call
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android_api.R
import com.example.android_api.model.TrackList
import com.example.android_api.model.TrackResponse
import com.squareup.picasso.Picasso

class TracksAdapter3(private val dataSet: TrackList,
                     private val openDetails: (TrackResponse) -> Unit) :
              RecyclerView.Adapter<TracksAdapter3.TracksViewHolder>() {

                  class TracksViewHolder(private val view: View) :
                          RecyclerView.ViewHolder(view){
                              private val trackimage: ImageView =
                                  view.findViewById(R.id.iv_track_poster)
                      private val trackTitle : TextView =
                          view.findViewById(R.id.tv_track_title)

                      fun onBind(dataItem: TrackResponse,
                      openDetails: (TrackResponse) -> Unit) {
                          trackTitle.text = dataItem.collectionName
                          Picasso.get().load(dataItem.image).into(trackimage)
                          view.setOnClickListener{openDetails(dataItem) }
                         }
                          }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TracksViewHolder {
        return TracksViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.track_item_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TracksViewHolder, position: Int) {
        holder.onBind(dataSet[position], openDetails)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

}