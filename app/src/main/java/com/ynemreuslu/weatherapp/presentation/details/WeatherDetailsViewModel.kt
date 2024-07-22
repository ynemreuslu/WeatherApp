package com.ynemreuslu.weatherapp.presentation.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ynemreuslu.weatherapp.domain.repository.WeatherRepository
import com.ynemreuslu.weatherapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherDetailsViewModel @Inject constructor(private val weatherRepository: WeatherRepository) :
    ViewModel() {

    var state by mutableStateOf(WeatherDetailsState())
        private set


    fun loadWeatherWeekInfo(lat: Double, long: Double) {
        viewModelScope.launch {
            state = state.copy(isLoading = true, error = null)
            val result = weatherRepository.getWeatherWeekData(lat, long, 7)
            when (result) {
                is Resource.Error -> {
                    state = state.copy(isLoading = false, error = result.message)
                }

                is Resource.Success -> {
                    state = state.copy(
                        weatherInfo = result.data,
                        isLoading = false,
                        error = null
                    )
                }
            }
        }
    }
}


