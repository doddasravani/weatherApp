package com.open.weather.repository

import com.open.weather.repository.model.Result

interface Repository {

    suspend fun getWeatherResponse(city: String): Result
}