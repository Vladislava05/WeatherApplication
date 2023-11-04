package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.databinding.ItemWeatherBinding
import java.util.Date
import java.util.stream.IntStream.range

import kotlin.math.round


class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: WeatherViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var weatherAdapter: WeatherAdapter
    private var weatherList: MutableList<WeatherHourly> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        viewModel= WeatherViewModel()
        val date = Date()
        weatherAdapter = WeatherAdapter(weatherList)
        binding.rvWeatherItems.adapter = weatherAdapter
        binding.rvWeatherItems.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        //var url:String="http://api.openweathermap.org/data/2.5/weather?g=$city&appid=$key&units=metric&lang=ru"

        binding.button.setOnClickListener {
            val city:String=binding.location.text.toString()
            viewModel.getWeather(city)
            viewModel.getMainWeather(city)
            viewModel.getHourWeather(city)
            binding.date.text=date.toString()
            viewModel.weatherDetails.observe(this, { weatherDetails ->
                binding.temperature.text = "${round(weatherDetails.temperature-273.15).toInt()}°C"
                binding.feelsLike.text="Feels like:  ${(weatherDetails.feels_like-273.15).toInt()}°C"
            })

            viewModel.weatherMain.observe(this, { weatherMain ->
                binding.description.text=weatherMain[0].description
                if(weatherMain[0].id in (200..233)) {
                    binding.weatherIcon.setImageResource(R.drawable.storm)
                }
                if(weatherMain[0].id in (300..333)) {
                    binding.weatherIcon.setImageResource(R.drawable.drizzle)
                }
                if(weatherMain[0].id in (500..533)) {
                    binding.weatherIcon.setImageResource(R.drawable.rain)
                }
                if(weatherMain[0].id in (600..623)) {
                    binding.weatherIcon.setImageResource(R.drawable.snow)
                }
                if(weatherMain[0].id in (801..805)) {
                    binding.weatherIcon.setImageResource(R.drawable.cloud)
                }
                if(weatherMain[0].id==800) {
                    binding.weatherIcon.setImageResource(R.drawable.sun)
                }
                if(weatherMain[0].id==741 || weatherMain[0].id==701){
                    binding.weatherIcon.setImageResource(R.drawable.mist)
                }
                })
            viewModel.weatherHourly.observe(this,{weatherHourly ->
                weatherList.addAll(weatherHourly)
                weatherAdapter.notifyDataSetChanged()

                Log.d("myapp", weatherList.toString())
            })

    }
  }
}