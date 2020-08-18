package com.murphy.client.google

import android.content.Context
import android.support.v4.app.Fragment
import com.murphy.map.client.LocationLisetner
import com.murphy.map.client.MapClient
import com.murphy.map.client.OnMapLoadedCallback
import com.murphy.map.client.domain.GCJ02LatLng

class GoogleMapClient: MapClient {

    override fun makeFragment(context: Context?): Fragment {
        TODO("Not yet implemented")
    }

    override fun setOnMapLoadedCallback(callback: OnMapLoadedCallback?) {
        TODO("Not yet implemented")
    }

    override fun addMarkAndZoomFocus(latLng: GCJ02LatLng, resource: Int) {
        TODO("Not yet implemented")
    }

    override fun registerLocation(locationLisetner: LocationLisetner, context: Context?) {
        TODO("Not yet implemented")
    }
}