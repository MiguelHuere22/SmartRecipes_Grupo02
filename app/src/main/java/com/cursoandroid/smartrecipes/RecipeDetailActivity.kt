package com.cursoandroid.smartrecipes

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class RecipeDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_receta)

        val receta = intent.getSerializableExtra("receta") as Comida

        val buttonBack: Button = findViewById(R.id.buttonBack)
        buttonBack.setOnClickListener {
            finish()  // Esto cierra la actividad actual y regresa a la anterior
        }

        val nombre: TextView = findViewById(R.id.nombreDetalleReceta)
        val descripcion: TextView = findViewById(R.id.descripcionDetalleReceta)
        val imagen: ImageView = findViewById(R.id.imagenDetalleReceta)
        val ingredientes: TextView = findViewById(R.id.listaIngredientesDetalleReceta)
        val pasos: TextView = findViewById(R.id.listaPasosDetalleReceta)

        nombre.text = receta.nombre
        descripcion.text = receta.descripcion
        Glide.with(this).load(receta.imagenUrl).into(imagen)
        ingredientes.text = receta.ingredientes.joinToString("\n")
        pasos.text = receta.pasos.joinToString("\n")
    }
}
