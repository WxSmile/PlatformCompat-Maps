package com.murphy.map.client

import com.murphy.map.client.domain.GCJ02LatLng

interface LocationLisetner {

    fun onRequestedLocation(gcJ02LatLng: GCJ02LatLng)
}