package com.open.weather.repository

import com.open.weather.repository.model.Result
import com.open.weather.repository.model.WeatherException
import com.open.weather.repository.remote.Constants
import com.open.weather.repository.remote.RemoteSource

class WeatherRepositoryImpl(private val remoteSource: RemoteSource): Repository {

    override suspend fun getWeatherResponse(city: String): Result {
        val response = remoteSource.fetchWeatherResponse(city, Constants.API_KEY)
        return if (response.isSuccessful && response.body() != null) {
            Result.Success(response.body()?.temperatureList)
        } else {
            Result.Error(WeatherException(response.errorBody()?.string()))
        }
    }
}