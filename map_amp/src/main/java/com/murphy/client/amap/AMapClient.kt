package com.murphy.client.amap

import android.content.Context
import android.support.v4.app.Fragment
import com.amap.api.location.AMapLocation
import com.amap.api.location.AMapLocationClient
import com.amap.api.maps.CameraUpdateFactory
import com.amap.api.maps.LocationSource
import com.amap.api.maps.TextureSupportMapFragment
import com.amap.api.maps.model.BitmapDescriptorFactory
import com.amap.api.maps.model.CameraPosition
import com.amap.api.maps.model.LatLng
import com.amap.api.maps.model.MarkerOptions
import com.murphy.map.client.LocationLisetner
import com.murphy.map.client.MapClient
import com.murphy.map.client.OnMapLoadedCallback
import com.murphy.map.client.domain.GCJ02LatLng

class AMapClient : MapClient {

    private var locationClient: AMapLocationClient? = null
    private var supportMapFragment: TextureSupportMapFragment? = null

    override fun makeFragment(context: Context?): Fragment {
        return supportMapFragment ?: TextureSupportMapFragment.newInstance()
    }

    override fun setOnMapLoadedCallback(callback: OnMapLoadedCallback) {
        supportMapFragment?.map?.setOnMapLoadedListener {
            callback.onMapLoaded()
        }
    }

    override fun addMarkAndZoomFocus(latLng: GCJ02LatLng, resource: Int) {
        val map = supportMapFragment?.map
        val point = LatLng(latLng.lat, latLng.lng)
        val bitmapDescriptor = BitmapDescriptorFactory.fromResource(resource)
        val position = MarkerOptions()
            .position(point)
            .icon(bitmapDescriptor)
        map?.addMarker(position)
        val cameraPosition = CameraPosition.Builder()
            .target(point)
            .zoom(20f)
            .build()
        val cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition)
        map?.moveCamera(cameraUpdate)
    }

    override fun registerLocation(locationLisetner: LocationLisetner, context: Context?) {
        locationClient = AMapLocationClient(context)
        val map = supportMapFragment?.map
        map?.isMyLocationEnabled = false
        map?.setLocationSource(object : LocationSource {
            override fun deactivate() {
                locationClient?.setLocationListener {
                    onLocationChange(it, locationLisetner)
                }
                locationClient?.startLocation()
            }

            override fun activate(p0: LocationSource.OnLocationChangedListener?) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun onLocationChange(
        aMapLocation: AMapLocation,
        locationLisetner: LocationLisetner
    ) {
        val gcJ02LatLng = GCJ02LatLng(aMapLocation.latitude, aMapLocation.longitude)
        locationLisetner.onRequestedLocation(gcJ02LatLng)
    }
}