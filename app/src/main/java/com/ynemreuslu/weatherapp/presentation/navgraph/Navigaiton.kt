package com.ynemreuslu.weatherapp.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ynemreuslu.weatherapp.presentation.details.DetailsScreen
import com.ynemreuslu.weatherapp.presentation.navgraph.Destinations.WEATHER_DETAILS_SCREEN
import com.ynemreuslu.weatherapp.presentation.navgraph.Destinations.WEATHER_SCREEN
import com.ynemreuslu.weatherapp.presentation.weather.WeatherScreen


object Destinations {
    const val WEATHER_SCREEN = "weather"
    const val WEATHER_DETAILS_SCREEN = "details/{lat}/{lng}"
}

@Composable
fun WeatherNavGraph(navController: NavHostController = rememberNavController()) {

    NavHost(navController = navController, startDestination = WEATHER_SCREEN) {
        composable(WEATHER_SCREEN) {
            WeatherScreen(onWeekScreenClick = { lat, lng ->
                navController.navigate("details/$lat/$lng")
            })
        }
        composable(WEATHER_DETAILS_SCREEN) { backStackEntry ->
            val lat = backStackEntry.arguments?.getString("lat")
            val lng = backStackEntry.arguments?.getString("lng")
            DetailsScreen(lat = lat, lng = lng)
        }
    }

}