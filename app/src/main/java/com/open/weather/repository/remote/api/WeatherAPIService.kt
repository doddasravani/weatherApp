package com.open.weather.repository.remote.api

import com.open.weather.repository.remote.model.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPIService {

    @GET("data/2.5/forecast")
    suspend fun fetchWeatherResponse(
        @Query("q") city: String,
        @Query("appid") apiKey: String
    ): Response<WeatherResponse>
}