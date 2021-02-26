package com.open.weather.repository.remote

import com.open.weather.repository.remote.api.WeatherAPIService
import com.open.weather.repository.remote.model.WeatherResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherRemoteSourceImpl(private val apiService: WeatherAPIService) : RemoteSource {

    companion object {
        fun create(baseURL: String): RemoteSource {
            // Initialization of Retrofit instance
            val retrofit = Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service = retrofit.create(WeatherAPIService::class.java)
            return WeatherRemoteSourceImpl(service)
        }
    }

    override suspend fun fetchWeatherResponse(city: String, apiKey: String): Response<WeatherResponse> =
        apiService.fetchWeatherResponse(city, apiKey)

}