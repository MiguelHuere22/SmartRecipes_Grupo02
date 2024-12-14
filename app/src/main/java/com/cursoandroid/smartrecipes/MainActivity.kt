package com.cursoandroid.smartrecipes

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader
import java.io.Serializable

class MainActivity : AppCompatActivity() {

    private lateinit var listaDeRecetas: List<Comida>
    private lateinit var recetaAdapter: RecetasAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listaDeRecetas = leerRecetasDesdeJson(this)

        val recyclerView: RecyclerView = findViewById(R.id.recetaRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recetaAdapter = RecetasAdapter(listaDeRecetas) { receta ->
            val intent = Intent(this, RecipeDetailActivity::class.java)
            intent.putExtra("receta", receta as Serializable)
            startActivity(intent)
        }
        recyclerView.adapter = recetaAdapter

        val difficultySpinner: Spinner = findViewById(R.id.difficultyFilter)
        difficultySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedDificultad = parent?.getItemAtPosition(position) as String
                val recetasFiltradas = filtrarPorDificultad(selectedDificultad)
                recetaAdapter.updateRecetas(recetasFiltradas)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        val timeSpinner: Spinner = findViewById(R.id.timeFilter)
        timeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedTime = parent?.getItemAtPosition(position) as String
                val recetasFiltradas = filtrarPorTiempo(selectedTime)
                recetaAdapter.updateRecetas(recetasFiltradas)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun filtrarPorDificultad(dificultad: String): List<Comida> {
        return if (dificultad == "Todas") {
            listaDeRecetas
        } else {
            listaDeRecetas.filter { it.dificultad.equals(dificultad, true) }
        }
    }

    private fun convertirTiempoAMinutos(tiempo: String): Int {
        val parts = tiempo.split(" ")
        var minutosTotales = 0

        for (part in parts) {
            if (part.endsWith("h")) {
                val horas = part.removeSuffix("h").toInt()
                minutosTotales += horas * 60
            } else if (part.endsWith("m")) {
                val minutos = part.removeSuffix("m").toInt()
                minutosTotales += minutos
            }
        }
        return minutosTotales
    }

    private fun filtrarPorTiempo(tiempo: String): List<Comida> {
        return when (tiempo) {
            "Menos de 30 minutos" -> listaDeRecetas.filter { convertirTiempoAMinutos(it.tiempo) < 30 }
            "30-60 minutos" -> listaDeRecetas.filter { convertirTiempoAMinutos(it.tiempo) in 30..60 }
            "Más de 60 minutos" -> listaDeRecetas.filter { convertirTiempoAMinutos(it.tiempo) > 60 }
            else -> listaDeRecetas
        }
    }

    private fun leerRecetasDesdeJson(context: Context): List<Comida> {
        val inputStream = context.assets.open("Recetas.json")
        val reader = InputStreamReader(inputStream)
        val recetasType = object : TypeToken<List<Comida>>() {}.type
        return Gson().fromJson(reader, recetasType)
    }
}
