package com.murphy.map.client

import android.content.Context
import android.support.v4.app.Fragment
import com.murphy.map.client.domain.GCJ02LatLng

interface MapClient {

    fun makeFragment(context: Context?): Fragment

    fun setOnMapLoadedCallback(callback: OnMapLoadedCallback)

    fun addMarkAndZoomFocus(latLng: GCJ02LatLng, resource: Int)

    fun registerLocation(locationLisetner: LocationLisetner, context: Context?)
}