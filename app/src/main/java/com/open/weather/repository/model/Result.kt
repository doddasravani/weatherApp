package com.open.weather.repository.model

import com.open.weather.repository.remote.model.Temperature
import java.lang.Exception

sealed class Result {
    data class Success(val tempList: List<Temperature>?): Result()
    data class Error(val exception: WeatherException): Result()
}

data class WeatherException(
    val errorMessage: String?
) : Exception()