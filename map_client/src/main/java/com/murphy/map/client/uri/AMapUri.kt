package com.murphy.map.client.uri

import android.content.Context
import android.net.Uri
import com.murphy.map.client.domain.MapUri

class AMapUri(mapUri: MapUri) : PlatformUri(mapUri) {

    override fun buildUri(lat: Double, lng: Double, label: String, context: Context?): Uri {
        val uri: String =
            "androidamap://viewMap?sourceApplication=app&poiname=$label&lat=$lat&lon=$lng&dev=0"
        return Uri.parse(uri)
    }

}