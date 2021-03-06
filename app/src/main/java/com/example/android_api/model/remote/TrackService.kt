package com.example.android_api.model.remote

import retrofit2.Call
import com.example.android_api.common.BASE_URL
import com.example.android_api.common.END_POINT
import com.example.android_api.model.TrackList
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET

interface TrackService {
    @GET(END_POINT)
    fun getTracks(): Call<TrackList> // Call <List of Tracks>

    companion object{
        fun initRetrofit(): TrackService{
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TrackService::class.java)
        }
    }
}