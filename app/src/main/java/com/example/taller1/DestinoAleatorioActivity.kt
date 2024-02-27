package com.example.taller1

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DestinoAleatorioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destino_aleatorio)

        val favoritos = MainActivity.favoritos

        // Verificar si la lista de favoritos está vacía
        if (favoritos.isEmpty()) {
            // Si la lista de favoritos está vacía, mostrar "N/A"
            mostrarNA()
        } else {
            // Contar la frecuencia de cada categoría
            val categoriasCount = favoritos.groupingBy { it.categoria }.eachCount()

            // Obtener la categoría con el recuento máximo
            val categoriaMasRepetida = categoriasCount.maxByOrNull { it.value }?.key

            // Filtrar los destinos favoritos que pertenecen a la categoría más repetida
            val destinosCategoriaMasRepetida = favoritos.filter { it.categoria == categoriaMasRepetida }

            // Verificar si hay destinos en la categoría más repetida
            if (destinosCategoriaMasRepetida.isEmpty()) {
                // Si no hay destinos en la categoría más repetida, mostrar "N/A"
                mostrarNA()
            } else {
                // Obtener un destino aleatorio de la categoría más repetida
                val destinoAleatorio = destinosCategoriaMasRepetida.random()

                // Mostrar los detalles del destino aleatorio en los TextViews
                findViewById<TextView>(R.id.textViewNombre).text = destinoAleatorio.nombre
                findViewById<TextView>(R.id.textViewPais).text = destinoAleatorio.pais
                findViewById<TextView>(R.id.textViewCategoria).text = destinoAleatorio.categoria
                findViewById<TextView>(R.id.textViewPlan).text = destinoAleatorio.plan
                findViewById<TextView>(R.id.textViewPrecio).text = destinoAleatorio.precio.toString()
            }
        }
    }

    private fun mostrarNA() {
        // Mostrar "N/A" en los TextViews
        findViewById<TextView>(R.id.textViewNombre).text = "N/A"
        findViewById<TextView>(R.id.textViewPais).text = "N/A"
        findViewById<TextView>(R.id.textViewCategoria).text = "N/A"
        findViewById<TextView>(R.id.textViewPlan).text = "N/A"
        findViewById<TextView>(R.id.textViewPrecio).text = "N/A"
    }
}