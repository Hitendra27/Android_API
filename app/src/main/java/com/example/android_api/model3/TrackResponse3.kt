package com.example.android_api.model3

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.lang.reflect.Array
//import java.util.ArrayList
import java.io.Serializable

class TrackList: ArrayList<TrackResponse>()

@Parcelize
 data class TrackResponse(

    //val results: AList<resultsobject>
            val result: ArrayList<String>,
            val image: String,
            val collectionName: String,
            val trackName: String,
            val trackPrice: Float,
            val currency: String
): Parcelable