package com.example.android_api.view3

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android_api.R
import com.example.android_api.model.TrackList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "TrackListFragment"

class TrackListFragment: Fragment() {

    private lateinit var trackList: RecyclerView
    private lateinit var adapter: TracksAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(
            R.layout.track_list_fragment_layout,
            container,
            false
        )
        initViews(view)
        getTracks()
        return view
    }

    private fun initViews(view: View) {
        trackList = view.findViewById(R.id.track_list)
        trackList.layoutManager = GridLayoutManager(context, 3)
    }

    private fun getTracks(){
        TrackService.initRetrofit().getTracks()
            .enqueue(
                object : Callback<TrackList> {
                    override fun onResponse(
                        call: Call<TrackList>,
                        response: Response<TrackList>
                    ) {
                        Log.d(TAG, "onResponse: $response")
                        if (response.isSuccessful){
                          //  updateAdapter(response.body())
                        }else
                            showError(response.message())
                    }

                    override fun onFailure(call: Call<TrackList>, t: Throwable) {
                        Log.d(TAG, "onFailure: $t")
                        showError(t.message ?: "Unknown error")
                    }

                }
            )
    }

    private fun showError(errorMessage: String) {

    }

   // private fun updateAdapter(body: TrackList?) {
      //  body?.let{
         //   adapter = TracksAdapter(it) {trackDetails ->
             //   activity?.openTrackDetail(trackDetails)
           // }
           // trackList.adapter = adapter
       // } ?: showError("No response from server")
   // }

   // private fun FragmentActivity.openMovieDetail(trackDetails: TrackResponse){
      //  supportFragmentManager.beginTransaction()
         //   .replace(android.R.id.content, .newInstance(movieDetail))
          //  .addToBackStack(null)
         //   .commit()
   // }
}