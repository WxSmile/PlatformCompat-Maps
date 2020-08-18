package com.murphy.map.client

import android.content.Context
import android.support.v4.app.Fragment
import com.murphy.map.client.domain.GCJ02LatLng

class MapManager private constructor() {

    companion object {
        val instance = Holder.instance
    }

    private object Holder {
        val instance = MapManager()
    }

    var mapClient: MapClient? = null

    fun makeFragment(context: Context? = null): Fragment? {
        return mapClient?.makeFragment(context)
    }

    fun setOnMapLoadedCallback(callback: OnMapLoadedCallback?) {
        mapClient?.setOnMapLoadedCallback(callback)
    }

    fun addMarkAndZoomFocus(latLng: GCJ02LatLng?, resource: Int) {
        mapClient?.addMarkAndZoomFocus(latLng, resource)
    }

    fun registerLocation(locationLisetner: LocationLisetner, context: Context? = null) {
        mapClient?.registerLocation(locationLisetner, context)
    }


}