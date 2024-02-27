package com.example.taller1

import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest

class WeatherApiService(private val queue: RequestQueue) {

    fun getWeatherByCityName(cityName: String, apiKey: String, callback: (String) -> Unit) {
        val url = "https://api.openweathermap.org/data/2.5/weather?q=$cityName&appid=$apiKey"

        val request = JsonObjectRequest(Request.Method.GET, url, null,
            { response ->
                val weatherDescription = response.getJSONArray("weather")
                    .getJSONObject(0)
                    .getString("description")

                callback(weatherDescription)
            },
            { error ->
                callback("Error al obtener el clima: ${error.message}")
            }
        )

        queue.add(request)
    }
}