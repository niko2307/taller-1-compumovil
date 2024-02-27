package com.example.taller1

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DestinoAleatorioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destino_aleatorio)

        // Obtener un destino aleatorio de la lista de favoritos
        val destinoAleatorio = MainActivity.favoritos.random()

        // Mostrar los detalles del destino aleatorio en los TextViews
        findViewById<TextView>(R.id.textViewNombre).text = destinoAleatorio.nombre
        findViewById<TextView>(R.id.textViewPais).text = destinoAleatorio.pais
        findViewById<TextView>(R.id.textViewCategoria).text = destinoAleatorio.categoria
        findViewById<TextView>(R.id.textViewPlan).text = destinoAleatorio.plan
        findViewById<TextView>(R.id.textViewPrecio).text = destinoAleatorio.precio.toString()
    }
}