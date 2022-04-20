package com.example.android_api.view2

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android_api.R
import com.example.android_api.model.TrackList
import com.example.android_api.model.TrackResponse
import com.squareup.picasso.Picasso
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

class TracksAdapter2(private val dataSet: TrackList,
                     private val openDetails: (TrackResponse) -> Unit) :
              RecyclerView.Adapter<TracksAdapter2.TracksViewHolder>() {

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