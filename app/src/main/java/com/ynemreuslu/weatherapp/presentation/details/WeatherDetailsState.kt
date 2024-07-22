package com.ynemreuslu.weatherapp.presentation.details

import com.ynemreuslu.weatherapp.domain.weather.WeatherInfo

data class WeatherDetailsState(
    val weatherInfo: WeatherInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)



