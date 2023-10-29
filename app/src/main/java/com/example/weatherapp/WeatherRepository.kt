package com.example.weatherapp

class WeatherRepository(private val apiService: WeatherApiService) {
    suspend fun getWeather(city: String): WeatherDetails? {
        val response = apiService.getWeather(city, "ea7d29da411bfde436d6ee7effcda969")
        if (response.isSuccessful) {
            return response.body()?.weatherDetails
        }
        return null
    }
    suspend fun getMainWeather(city: String): List<WeatherMain>? {
        val response = apiService.getMainWeather(city, "ea7d29da411bfde436d6ee7effcda969")
        if (response.isSuccessful) {
            return response.body()?.weatherMain
        }
        return null
    }
}