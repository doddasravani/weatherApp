package com.open.weather.repository.remote.model

import com.google.gson.annotations.SerializedName

data class WeatherResponse (
    @SerializedName("cod")
    val statusCode: Int,
    val message: String,
    @SerializedName("cnt")
    val count: Int,
    @SerializedName("list")
    val temperatureList: List<Temperature>,
    val city: City
)