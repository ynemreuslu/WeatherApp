package com.ynemreuslu.weatherapp.presentation.weather

import android.os.Build
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ynemreuslu.weatherapp.util.LocationUtils


@Composable
fun LocationInformation(state: WeatherState, modifier: Modifier = Modifier) {
    state.latVsLng?.let { coordinates ->
        var locationDetails by remember { mutableStateOf("") }
        val context = LocalContext.current

        if (Build.VERSION.SDK_INT > 33) {
            LocationUtils.fetchLocationInfoWithCallback(
                latitude = coordinates.lat,
                longitude = coordinates.long,
                context = context
            ) {
                locationDetails = it
            }
        } else {
            locationDetails = LocationUtils.fetchLocationInfo(
                latitude = coordinates.lat,
                longitude = coordinates.long,
                context = context
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.padding(start = 8.dp)
        ) {
            Icon(
                imageVector = Icons.Sharp.LocationOn,
                contentDescription = null,
                tint = Color.White
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = locationDetails,
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                color = Color.White,
                fontSize = 16.sp
            )
        }
    }
}