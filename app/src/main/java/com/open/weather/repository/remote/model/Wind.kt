package com.open.weather.repository.remote.model

import com.google.gson.annotations.SerializedName

data class Wind (
    val speed: Double,
    @SerializedName("deg")
    val degree: Double
)