package com.example.android_api.model

import android.os.Parcelable
import com.example.android_api.view.TracksAdapter
import kotlinx.parcelize.Parcelize
import java.lang.reflect.Array
//import java.util.ArrayList
import java.io.Serializable

class TrackList: ArrayList<TrackResponse>()


 data class TrackResponse(

     val results: Tracks,
    // val collectionName: CharSequence

 )

data class Tracks (
        val image: String,
        val collectionName: String,
        val trackName: String,
        val trackPrice: Float,
        val currency: String

)