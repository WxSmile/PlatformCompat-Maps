package com.murphy.map.client.uri

import android.content.Context
import android.net.Uri
import com.murphy.map.client.domain.MapUri
import com.murphy.map.client.util.gcj02ToBd09

class BaiDuUri(mapUri: MapUri): PlatformUri(mapUri) {

    override fun buildUri(lat: Double, lng: Double, label: String, context: Context?): Uri {
        val bd09 = gcj02ToBd09(lat, lng)
        val uri = "baidumap://map/marker?location=${bd09[0]},${bd09[1]}&title=$label&src=app"
        return Uri.parse(uri)
    }
}