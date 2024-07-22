package com.ynemreuslu.weatherapp.presentation.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.time.LocalDate

@Composable
fun WeeklyWeatherData(state: WeatherDetailsState) {
    val today = LocalDate.now()
    val nextDay = today.plusDays(1)
    state.weatherInfo?.weatherDataPerDay?.get(0)?.let { dailyWeatherData ->
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn(
                contentPadding = PaddingValues(4.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(dailyWeatherData.filter { it.time.dayOfWeek >= nextDay.dayOfWeek }) { hourlyWeatherData ->
                    HourlyWeatherWeek(
                        weatherData = hourlyWeatherData,
                        modifier = Modifier
                            .height(100.dp)
                            .padding(vertical = 8.dp)
                    )
                }
            }
        }
    }
}