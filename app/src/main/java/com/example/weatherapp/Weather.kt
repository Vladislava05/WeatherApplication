package com.example.weatherapp

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("main") val weatherDetails: WeatherDetails,
    @SerializedName("weather") val weatherMain: List<WeatherMain>,
    @SerializedName("list") val weatherHourly: List<WeatherHourly>
)

data class WeatherDetails(
    @SerializedName("temp") val temperature: Double,
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("feels_like") val feels_like: Double
)

data class WeatherMain(
    @SerializedName("id") val id: Int,
    @SerializedName("main") val main: String,
    @SerializedName("description") val description: String,
    @SerializedName("icon") val icon: String
)

data class WeatherHourly(
    @SerializedName("dt_txt") val dt_txt: String,
    @SerializedName("main") val main: WeatherHourlyMain,
    @SerializedName("weather") val weather: List<WeatherHourlyDetails>
)

data class WeatherHourlyMain(
    @SerializedName("temp") val temperature: Double,
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("feels_like") val feels_like: Double
)

data class WeatherHourlyDetails(
    @SerializedName("id") val id: Int
)




