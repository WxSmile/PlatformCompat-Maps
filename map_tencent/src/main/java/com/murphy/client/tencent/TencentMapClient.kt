package com.murphy.client.tencent

import android.content.Context
import android.os.Looper
import android.support.v4.app.Fragment
import com.murphy.map.client.LocationLisetner
import com.murphy.map.client.MapClient
import com.murphy.map.client.OnMapLoadedCallback
import com.murphy.map.client.domain.GCJ02LatLng
import com.tencent.map.geolocation.TencentLocation
import com.tencent.map.geolocation.TencentLocationListener
import com.tencent.map.geolocation.TencentLocationManager
import com.tencent.tencentmap.mapsdk.maps.CameraUpdateFactory
import com.tencent.tencentmap.mapsdk.maps.LocationSource
import com.tencent.tencentmap.mapsdk.maps.SupportMapFragment
import com.tencent.tencentmap.mapsdk.maps.model.BitmapDescriptorFactory
import com.tencent.tencentmap.mapsdk.maps.model.CameraPosition
import com.tencent.tencentmap.mapsdk.maps.model.LatLng
import com.tencent.tencentmap.mapsdk.maps.model.MarkerOptions

/**
 * 腾讯地图使用国测局GCJ02坐标系
 */
class TencentMapClient : MapClient, TencentLocationListener {

    private var tencentLocationManager: TencentLocationManager? = null
    private var supportMapFragment: SupportMapFragment? = null
    private var locationLisetner: LocationLisetner? = null

    override fun makeFragment(context: Context?): Fragment {
        return supportMapFragment ?: SupportMapFragment.newInstance(context)
    }

    override fun setOnMapLoadedCallback(callback: OnMapLoadedCallback) {
        supportMapFragment?.map?.addOnMapLoadedCallback {
            callback.onMapLoaded()
        }
    }

    override fun addMarkAndZoomFocus(latLng: GCJ02LatLng, resource: Int) {
        val map = supportMapFragment?.map
        val point = LatLng(latLng.lat, latLng.lng)
        val bitmapDescriptor = BitmapDescriptorFactory.fromResource(resource)
        val markerOptions = MarkerOptions(point).icon(bitmapDescriptor)
        map?.addMarker(markerOptions)

        val cameraPosition = CameraPosition.Builder()
            .target(point)
            .zoom(15f)
            .build()
        val cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition)
        map?.moveCamera(cameraUpdate)
    }

    override fun registerLocation(locationLisetner: LocationLisetner, context: Context?) {
        this.locationLisetner = locationLisetner

        val map = supportMapFragment?.map
        map?.isMyLocationEnabled = false
        tencentLocationManager = TencentLocationManager.getInstance(context)
        map?.setLocationSource(object : LocationSource {
            override fun deactivate() {
                tencentLocationManager?.requestSingleFreshLocation(
                    null,
                    this@TencentMapClient,
                    Looper.getMainLooper()
                )
            }

            override fun activate(p0: LocationSource.OnLocationChangedListener?) {
                tencentLocationManager?.removeUpdates(this@TencentMapClient)
            }

        })
    }

    override fun onLocationChanged(tencentLocation: TencentLocation, p1: Int, p2: String) {
        val gcJ02LatLng = GCJ02LatLng(tencentLocation.latitude, tencentLocation.longitude)
        locationLisetner?.onRequestedLocation(gcJ02LatLng)
    }

    override fun onStatusUpdate(p0: String?, p1: Int, p2: String?) {
        //"Not implemented"
    }
}