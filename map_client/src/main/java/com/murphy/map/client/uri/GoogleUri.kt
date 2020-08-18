package com.murphy.map.client.uri

import android.content.Context
import android.net.Uri
import com.murphy.map.client.domain.MapUri
import com.murphy.map.client.util.gcj02ToGps84

class GoogleUri(mapUri: MapUri) : PlatformUri(mapUri) {

    override fun buildUri(lat: Double, lng: Double, label: String, context: Context?): Uri {
        val gps84 = gcj02ToGps84(lat, lng)
        val uri = "geo:${gps84[0]},${gps84[1]}?q=${gps84[0]},${gps84[1]}($label)"
        return Uri.parse(uri)
    }
}