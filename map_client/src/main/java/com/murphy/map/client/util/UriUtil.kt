package com.murphy.map.client.util

import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.net.Uri
import com.murphy.map.client.uri.PlatformUri


/**
 * example: Intent.setPackage("com.google.android.apps.maps");
 * @return if true, the google map app is installed
 */
fun Intent.resolveMapInstalled(context: Context): Boolean {
    return resolveActivity(context.packageManager) != null
}

fun Context.getMetaData(key: String): String? {
    val appInfo: ApplicationInfo
    return try {
        appInfo = packageManager
            .getApplicationInfo(packageName, PackageManager.GET_META_DATA)
        val o = appInfo.metaData[key] ?: throw PackageManager.NameNotFoundException("")
        o.toString()
    } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
        null
    }
}

fun PlatformUri.buildIntent(uri: Uri): Intent {
    val intent = Intent()
    intent.data = uri
    intent.setPackage(mapUri.packageName)
    return intent
}