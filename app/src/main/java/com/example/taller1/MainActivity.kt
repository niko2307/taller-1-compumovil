package com.example.taller1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Spinner

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonExplorar: Button = findViewById(R.id.button)
        val spinner: Spinner = findViewById(R.id.spinner)

        buttonExplorar.setOnClickListener {
            // Obtener la categoría seleccionada del spinner
            val categoriaSeleccionada = spinner.selectedItem.toString()

            // Crear un Intent para abrir la nueva actividad (DestinosActivity)
            val intent = Intent(this, DestinosActivity::class.java)

            // Pasar la categoría seleccionada al intent
            if (categoriaSeleccionada == "Todos") {
                intent.putExtra("categoria", "Todos")
            } else {
                intent.putExtra("categoria", categoriaSeleccionada)
            }

            // Iniciar la nueva actividad
            startActivity(intent)
        }

    }
}
