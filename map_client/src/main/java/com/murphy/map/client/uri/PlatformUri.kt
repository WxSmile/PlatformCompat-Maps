package com.murphy.map.client.uri

import android.content.Context
import android.net.Uri
import com.murphy.map.client.domain.MapUri

abstract class PlatformUri(val mapUri: MapUri) {

    abstract fun buildUri(lat: Double, lng: Double, label: String, context: Context? = null): Uri
}