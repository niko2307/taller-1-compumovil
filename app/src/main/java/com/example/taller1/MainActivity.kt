package com.example.taller1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    companion object {
        var favoritos = mutableListOf<DestinoTuristico>()
        const val REQUEST_CODE_DESTINOS = 1
    }

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

            // Iniciar la nueva actividad esperando un resultado
            startActivityForResult(intent, REQUEST_CODE_DESTINOS)
        }

        val buttonFavoritos: Button = findViewById(R.id.button2)
        buttonFavoritos.setOnClickListener {
            val intent = Intent(this, FavoritosActivity::class.java)
            startActivity(intent)
        }

        val buttonRecomendaciones: Button = findViewById(R.id.button3)
        buttonRecomendaciones.setOnClickListener {
            val intent = Intent(this, DestinoAleatorioActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_DESTINOS && resultCode == Activity.RESULT_OK) {
            // Obtener el destino seleccionado de los extras del intent
            val destinoSeleccionado = data?.getSerializableExtra("destino") as DestinoTuristico

            // Marcar el destino como favorito y añadirlo a la lista de favoritos
            favoritos.add(destinoSeleccionado)
        }
    }
}
