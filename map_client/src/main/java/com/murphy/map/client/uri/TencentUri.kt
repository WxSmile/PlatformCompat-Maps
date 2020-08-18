package com.murphy.map.client.uri

import android.content.Context
import android.net.Uri
import com.murphy.map.client.domain.MapUri
import com.murphy.map.client.util.getMetaData

class TencentUri(mapUri: MapUri) : PlatformUri(mapUri) {

    override fun buildUri(lat: Double, lng: Double, label: String, context: Context?): Uri {
        val metaData = context?.getMetaData("TencentMapSDK") ?: ""
        val uri = "qqmap://map/marker?marker=coord:$lat,$lng;title:$label&referer=$metaData"
        return Uri.parse(uri)
    }
}