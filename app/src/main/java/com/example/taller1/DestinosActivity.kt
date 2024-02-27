package com.example.taller1

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import java.io.IOException
import java.io.InputStream

class DestinosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destinos)

        // Obtener la categoría seleccionada del intent
        val categoriaSeleccionada = intent.getStringExtra("categoria")

        // Cargar los destinos turísticos desde el archivo JSON
        val destinos = cargarDestinosDesdeJSON()

        // Filtrar los destinos por la categoría seleccionada
        val destinosFiltrados = if (categoriaSeleccionada == "Todos") {
            destinos
        } else {
            destinos.filter { it.categoria == categoriaSeleccionada }
        }

        // Obtener los nombres de los destinos filtrados
        val nombresDestinos = destinosFiltrados.map { it.nombre }

        // Inicializar el adaptador y la lista
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, nombresDestinos)
        val listView = findViewById<ListView>(R.id.listViewDestinos)
        listView.adapter = adapter

        // Agregar el listener a la lista
        listView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                // Obtener el destino seleccionado
                val destinoSeleccionado = destinosFiltrados[position]



                // Crear un Intent para abrir la actividad de detalles del destino
                val intent = Intent(this, DetallesDestinoActivity::class.java)


                // Pasar los detalles del destino al intent
                intent.putExtra("destino", destinoSeleccionado)

                // Iniciar la actividad de detalles del destino
                startActivity(intent)
            }
    }

    // Función para cargar los destinos turísticos desde un archivo JSON en la carpeta "assets"
    private fun cargarDestinosDesdeJSON(): List<DestinoTuristico> {
        // Cargar el JSON desde el archivo
        val json = loadJSONFromAsset()

        // Parsear el JSON a una lista de objetos de tipo DestinoTuristico utilizando Gson
        val gson = Gson()
        val listType = object : com.google.gson.reflect.TypeToken<List<DestinoTuristico>>() {}.type
        return gson.fromJson(json, listType)
    }

    // Función para cargar el contenido de un archivo JSON en formato String
    private fun loadJSONFromAsset(): String {
        val json: String
        try {
            // Abrir un flujo de entrada para el archivo "destinos.json" ubicado en la carpeta "assets"
            val inputStream: InputStream = applicationContext.assets.open("destinos.json")

            // Obtener el tamaño del archivo
            val size: Int = inputStream.available()

            // Crear un buffer para almacenar los datos del archivo
            val buffer = ByteArray(size)

            // Leer los datos del archivo en el buffer
            inputStream.read(buffer)

            // Cerrar el flujo de entrada
            inputStream.close()

            // Convertir los datos del buffer a un String utilizando UTF-8
            json = String(buffer, Charsets.UTF_8)
        } catch (ex: IOException) {
            // Manejar excepción en caso de que ocurra un error al leer el archivo
            ex.printStackTrace()
            return "" // Retornar una cadena vacía si ocurre un error
        }
        return json // Retornar el contenido del archivo como un String
    }




}










