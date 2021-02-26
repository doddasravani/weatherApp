package com.open.weather.repository.remote.model

import androidx.annotation.VisibleForTesting
import com.google.gson.annotations.SerializedName

@VisibleForTesting
open class Temperature (
    @SerializedName("dt")
    val date: Long,
    val main: Main,
    val weather: List<Weather>,
    val clouds: Clouds,
    val wind: Wind,
    val visibility: Double,
    val pop:Double,
    @SerializedName("dt_txt")
    val dateText: String
)