package com.ynemreuslu.weatherapp.util

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import java.util.Locale

object LocationUtils {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    inline fun fetchLocationInfoWithCallback(
        latitude: Double,
        longitude: Double,
        context: Context,
        crossinline callback: (String) -> Unit
    ) {
        val turkishLocale = Locale.getDefault()
        val geocoder = Geocoder(context, turkishLocale)

        val geocodeListener =
            @RequiresApi(Build.VERSION_CODES.TIRAMISU) object : Geocoder.GeocodeListener {
                override fun onGeocode(addresses: MutableList<Address>) {
                    val address = addresses.firstOrNull()
                    val adminArea = address?.adminArea ?: ""
                    val district = address?.subAdminArea ?: ""
                    val addressInfo = "$district $adminArea"
                    callback(addressInfo)
                }

                override fun onError(errorMessage: String?) {
                    Log.e("LocationInfo", "Error: $errorMessage")
                }
            }

        geocoder.getFromLocation(latitude, longitude, 1, geocodeListener)
    }

    fun fetchLocationInfo(latitude: Double, longitude: Double, context: Context): String {
        val geocoder = Geocoder(context, Locale.ENGLISH)
        val addresses = geocoder.getFromLocation(latitude, longitude, 1)
        val addressInfo = addresses!!.firstOrNull()
        val adminArea = addressInfo?.adminArea ?: ""
        val subAdminArea = addressInfo?.subAdminArea ?: ""

        return "$subAdminArea $adminArea"
    }
}