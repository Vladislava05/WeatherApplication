package com.example.weatherapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherViewModel : ViewModel() {
    private val apiService = Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org/data/2.5/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(WeatherApiService::class.java)

    private val repository = WeatherRepository(apiService)

    private val _weatherDetails = MutableLiveData<WeatherDetails>()
    val weatherDetails: LiveData<WeatherDetails> = _weatherDetails

    private val _weatherMain = MutableLiveData<List<WeatherMain>>()
    val weatherMain: LiveData<List<WeatherMain>> = _weatherMain


    fun getWeather(city: String) {
        viewModelScope.launch {
            val details = repository.getWeather(city)
            _weatherDetails.value = details
        }
    }

    fun getMainWeather(city: String){
        viewModelScope.launch{
            val main_details = repository.getMainWeather(city)
            _weatherMain.value = main_details
        }
    }

}