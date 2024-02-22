package com.example.taller1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FavoritosActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DestinosAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favoritos)

        // Configurar RecyclerView
        recyclerView = findViewById(R.id.recyclerViewFavoritos)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Obtener nombres de destinos favoritos
        val nombresDestinosFavoritos = MainActivity.favoritos.map { it.nombre }

        // Inicializar y configurar el adaptador con los nombres de los destinos favoritos
        adapter = DestinosAdapter(nombresDestinosFavoritos)
        recyclerView.adapter = adapter
    }
}
