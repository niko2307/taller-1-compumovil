package com.example.taller1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.io.InputStream

class DestinosActivity : AppCompatActivity() {
    private lateinit var listView: ListView
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var destinos: List<DestinoTuristico>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destinos)

        // Obtener la categoría seleccionada del intent
        val categoriaSeleccionada = intent.getStringExtra("categoria")

        // Cargar los destinos turísticos desde el archivo JSON
        destinos = cargarDestinosDesdeJSON()

        // Filtrar los destinos por la categoría seleccionada
        val destinosFiltrados = if (categoriaSeleccionada == "Todos") {
            destinos
        } else {
            destinos.filter { it.categoria == categoriaSeleccionada }
        }

        // Obtener los nombres de los destinos filtrados
        val nombresDestinos = destinosFiltrados.map { it.nombre }

        // Inicializar el adaptador y la lista
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, nombresDestinos)
        listView = findViewById<ListView>(R.id.listViewDestinos)
        listView.adapter = adapter
    }


    // Función para cargar los destinos turísticos desde un archivo JSON en la carpeta "assets"
    private fun cargarDestinosDesdeJSON(): List<DestinoTuristico> {
        // Cargar el JSON desde el archivo
        val json = loadJSONFromAsset()

        // Parsear el JSON a una lista de objetos de tipo DestinoTuristico utilizando Gson
        val gson = Gson()
        val listType = object : TypeToken<List<DestinoTuristico>>() {}.type //Lista en actividad_destino.XML
        return gson.fromJson(json, listType)
    }

    // Función para cargar el contenido de un archivo JSON en formato String
    private fun loadJSONFromAsset(): String {
        val json: String
        try {
            // Abrir un flujo de entrada para el archivo "destinos.json" ubicado en la carpeta "assets"
            val inputStream: InputStream = assets.open("destinos.json")

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
