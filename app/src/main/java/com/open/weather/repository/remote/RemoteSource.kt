package com.open.weather.repository.remote

import com.open.weather.repository.remote.model.WeatherResponse
import retrofit2.Response

interface RemoteSource {

    suspend fun fetchWeatherResponse(city: String, apiKey: String): Response<WeatherResponse>
}