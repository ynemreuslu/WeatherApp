package com.ynemreuslu.weatherapp.data.repository



import com.ynemreuslu.weatherapp.data.mappers.toWeatherDayInfo
import com.ynemreuslu.weatherapp.data.mappers.toWeatherWeekInfo
import com.ynemreuslu.weatherapp.data.remote.WeatherApi
import com.ynemreuslu.weatherapp.domain.repository.WeatherRepository
import com.ynemreuslu.weatherapp.domain.weather.WeatherInfo
import com.ynemreuslu.weatherapp.util.Resource
import javax.inject.Inject


class WeatherRepositoryImpl @Inject constructor(private val weatherApi: WeatherApi) :
    WeatherRepository {
    override suspend fun getWeatherDayData(lat: Double, long: Double): Resource<WeatherInfo> {
        return try {
            Resource.Success(
                data = weatherApi.getDayWeatherData(
                    latitude = lat, longitude = long
                ).toWeatherDayInfo()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }

    }

    override suspend fun getWeatherWeekData(
        lat: Double, long: Double, pastDays: Int
    ): Resource<WeatherInfo> {
        return try {
            Resource.Success(
                data = weatherApi.getWeekWeatherData(
                    latitude = lat, longitude = long, pastDays = pastDays
                ).toWeatherWeekInfo()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }
}


