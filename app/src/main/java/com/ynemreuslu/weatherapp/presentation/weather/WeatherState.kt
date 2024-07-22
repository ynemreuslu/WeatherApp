package com.ynemreuslu.weatherapp.presentation.weather

import com.ynemreuslu.weatherapp.domain.weather.WeatherInfo

data class WeatherState(
    val weatherInfo: WeatherInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    var latVsLng: LatVsLng? = null
)
