package com.ynemreuslu.weatherapp.domain.repository

import com.ynemreuslu.weatherapp.domain.weather.WeatherInfo
import com.ynemreuslu.weatherapp.util.Resource

interface WeatherRepository {
    suspend fun getWeatherDayData(lat: Double, long: Double): Resource<WeatherInfo>
    suspend fun getWeatherWeekData(lat: Double, long: Double, pastDays: Int): Resource<WeatherInfo>
}