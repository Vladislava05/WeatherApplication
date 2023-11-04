package com.example.weatherapp

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.ItemWeatherBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.round

class WeatherAdapter(private var weatherList: List<WeatherHourly>): RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>(){
    class WeatherViewHolder( ItemWeatherBinding: ItemWeatherBinding): RecyclerView.ViewHolder(ItemWeatherBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val ItemWeatherBinding = ItemWeatherBinding.inflate(layoutInflater, parent, false)

        return WeatherViewHolder(
            return WeatherViewHolder(ItemWeatherBinding)
        )
    }
    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val curWeather = weatherList[position]
        Log.d("rv", weatherList.toString())
        holder.itemView.apply {
            val firstApiFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            val weatherDate = findViewById(R.id.weatherDate) as TextView
            val resDate = LocalDateTime.parse("${curWeather.dt_txt}",firstApiFormat)
            weatherDate.text = "${resDate.dayOfMonth},${resDate.month},${resDate.hour}:00"
            val temperature = findViewById(R.id.weatherTemperature) as TextView
            temperature.text = "${round(curWeather.main.temperature-273.15).toInt()}°C"
            val weatherHourIcon = findViewById(R.id.weatherHourIcon) as ImageView
            if(curWeather.weather[0].id in (200..233)) {
                weatherHourIcon.setImageResource(R.drawable.storm)
            }
            if(curWeather.weather[0].id in (300..333)) {
                weatherHourIcon.setImageResource(R.drawable.drizzle)
            }
            if(curWeather.weather[0].id in (500..533)) {
                weatherHourIcon.setImageResource(R.drawable.rain)
            }
            if(curWeather.weather[0].id in (600..623)) {
                weatherHourIcon.setImageResource(R.drawable.snow)
            }
            if(curWeather.weather[0].id in (801..805)) {
                weatherHourIcon.setImageResource(R.drawable.cloud)
            }
            if(curWeather.weather[0].id==800) {
                weatherHourIcon.setImageResource(R.drawable.sun)
            }
            if(curWeather.weather[0].id==741 || curWeather.weather[0].id==701){
                weatherHourIcon.setImageResource(R.drawable.mist)
            }
        }

    }
    override fun getItemCount(): Int {
        return weatherList.size
    }
}