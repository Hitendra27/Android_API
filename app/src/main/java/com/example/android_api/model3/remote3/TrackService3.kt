package com.example.android_api.model3.remote3

import retrofit2.Call
import com.example.android_api.common.BASE_URL
import com.example.android_api.common.END_POINT
import com.example.android_api.model.TrackList
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET

interface TrackService3 {
    @GET(END_POINT)
    fun getTracks3(): Call<TrackList> // Call <List of Tracks>

    companion object{
        fun initRetrofit(): TrackService3{
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TrackService3::class.java)
        }
    }
}