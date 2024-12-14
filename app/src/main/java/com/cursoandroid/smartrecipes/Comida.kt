package com.cursoandroid.smartrecipes

import java.io.Serializable

data class Comida(
    val nombre: String,
    val descripcion: String,
    val dificultad: String,
    val tiempo: String,
    val imagenUrl: String,
    val ingredientes: List<String>,
    val pasos: List<String>  // Cambiar "preparacion" a "pasos" para coincidir con el JSON
) : Serializable
