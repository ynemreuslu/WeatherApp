package com.ynemreuslu.weatherapp.presentation.weather

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDateTime


@Composable
fun DailyWeatherForecast(
    state: WeatherState,
    onWeekScreenClick: (lat: Double, lng: Double) -> Unit
) {
    val currentDateTime = LocalDateTime.now()
    val currentHour =
        if (currentDateTime.minute < 30) currentDateTime.hour else currentDateTime.hour + 1

    val lat = state.latVsLng?.lat ?: 10.0
    val lng = state.latVsLng?.long ?: 30.0
    state.weatherInfo?.weatherDataPerDay?.get(0)?.let { dailyWeatherData ->

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Today",
                    fontSize = 16.sp,
                    color = Color.White
                )
                TextButton(
                    onClick = {
                        onWeekScreenClick(lat, lng)
                        Log.i("Location", "$lat $lng")
                    },
                    modifier = Modifier.background(Color.Transparent)
                ) {
                    Text(
                        text = "Next 7 Days",
                        color = Color.White,
                        fontSize = 16.sp,
                        style = TextStyle(
                            textDecoration = TextDecoration.Underline
                        )
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            LazyRow(
                content = {
                    items(dailyWeatherData.filter { it.time.hour >= currentHour }) { hourlyWeatherData ->
                        HourlyWeatherItem(
                            weatherData = hourlyWeatherData,
                            modifier = Modifier
                                .height(150.dp)
                                .padding(16.dp)
                        )
                    }
                }
            )
        }
    }
}