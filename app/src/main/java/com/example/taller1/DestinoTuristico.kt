package com.example.taller1

import java.io.Serializable


data class DestinoTuristico(
    val id: Int,
    val nombre: String,
    val pais: String,
    val categoria: String,
    val plan: String,
    val precio: Int,
    val clima :String
): Serializable
