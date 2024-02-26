package com.example.taller1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class DetallesDestinoActivity : AppCompatActivity() {

    private lateinit var queue: RequestQueue
    private lateinit var weatherApiService: WeatherApiService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_destino)

        queue = Volley.newRequestQueue(this)
        weatherApiService = WeatherApiService(queue)

        // Obtener los detalles del destino del intent
        val destino = intent.getSerializableExtra("destino") as DestinoTuristico

        // Mostrar los detalles del destino en los TextViews
        findViewById<TextView>(R.id.textViewNombre).text = destino.nombre
        findViewById<TextView>(R.id.textViewPais).text = destino.pais
        findViewById<TextView>(R.id.textViewCategoria).text = destino.categoria
        findViewById<TextView>(R.id.textViewPlan).text = destino.plan
        findViewById<TextView>(R.id.textViewPrecio).text = destino.precio.toString()

        val apiKey = "b73d08ff51013a9a968ea0d9bfc60d01\n"

        // Agregar listener al botón para añadir el destino a favoritos
        val botonAgregarFavoritos: Button = findViewById(R.id.botonAgregarFavoritos)
        botonAgregarFavoritos.setOnClickListener {
            // Verificar si el destino ya está en la lista de favoritos
            if (!MainActivity.favoritos.contains(destino)) {
                // Marcar el destino como favorito y añadirlo a la lista de favoritos en MainActivity
                MainActivity.favoritos.add(destino)

                // Desactivar el botón después de agregar a favoritos
                botonAgregarFavoritos.isEnabled = false

                // Mostrar un mensaje indicando que se ha agregado a favoritos
                Toast.makeText(this, "Añadido a favoritos", Toast.LENGTH_SHORT).show()
            } else {
                // Mostrar un mensaje indicando que el destino ya está en favoritos
                Toast.makeText(this, "¡Este destino ya está en tus favoritos!", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        weatherApiService.getWeatherByCityName(destino.nombre, apiKey) { weather ->
            findViewById<TextView>(R.id.textViewClima).text = "Clima: $weather"
        }
    }
}

