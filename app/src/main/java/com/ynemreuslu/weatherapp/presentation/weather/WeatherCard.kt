package com.ynemreuslu.weatherapp.presentation.weather

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ynemreuslu.weatherapp.R
import com.ynemreuslu.weatherapp.domain.weather.WeatherData
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

@Composable
fun WeatherCard(
    state: WeatherState,
    modifier: Modifier = Modifier,
) {
    val localTime = LocalDateTime.now()
    state.weatherInfo?.currentWeatherData?.let { data ->
        Card(
            shape = RoundedCornerShape(10.dp),
            modifier = modifier.padding(16.dp),
            colors = CardDefaults.cardColors(
                containerColor =  Color.Transparent,
            ),
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                WeatherHeader(state, localTime)
                WeatherDetails(data)
            }
        }
    }
}

@Composable
fun WeatherHeader(state: WeatherState, localTime: LocalDateTime) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 8.dp, end = 16.dp, bottom = 32.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        LocationInformation(state = state)
        Text(
            text = "Today ${localTime.format(DateTimeFormatter.ofPattern("HH:mm"))}",
            color = Color.White
        )
    }
}

@Composable
fun WeatherDetails(data: WeatherData) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        WeatherIcon(data)
        TemperatureDisplay(data)
        WeatherDescription(data)
        WeatherMetrics(data)
    }
}

@Composable
fun WeatherIcon(data: WeatherData) {
    Image(
        painter = painterResource(id = data.weatherType.iconRes),
        contentDescription = null,
        modifier = Modifier
            .width(200.dp)
            .height(200.dp)
    )
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
fun TemperatureDisplay(data: WeatherData) {
    Text(
        text = "${data.temperatureCelsius}°",
        fontSize = 50.sp,
        color = Color.White
    )
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
fun WeatherDescription(data: WeatherData) {
    Text(
        text = data.weatherType.weatherDesc,
        fontSize = 20.sp,
        color = Color.White
    )
    Spacer(modifier = Modifier.height(32.dp))
}

@Composable
fun WeatherMetrics(data: WeatherData) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        WeatherMetric(
            value = data.pressure.roundToInt(),
            unit = "hPa",
            iconResId = R.drawable.ic_pressure
        )
        WeatherMetric(
            value = data.humidity.roundToInt(),
            unit = "%",
            iconResId = R.drawable.ic_drop
        )
        WeatherMetric(
            value = data.windSpeed.roundToInt(),
            unit = "km/h",
            iconResId = R.drawable.ic_wind
        )
    }
}

@Composable
fun WeatherMetric(value: Int, unit: String, iconResId: Int) {
    WeatherMetricDisplay(
        metricValue = value,
        metricUnit = unit,
        metricIcon = ImageVector.vectorResource(id = iconResId),
        iconTint = Color.White,
        textStyle = TextStyle(color = Color.White)
    )
}