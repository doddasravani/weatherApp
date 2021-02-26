package com.open.weather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.open.weather.repository.Repository
import com.open.weather.repository.model.Result

class WeatherViewModel(val repository: Repository) : ViewModel() {

    fun getWeatherResponse(city: String): LiveData<Result> = liveData {
        val data = repository.getWeatherResponse(city)
        emit(data)
    }
}

class WeatherViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            return WeatherViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
