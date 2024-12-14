package com.cursoandroid.smartrecipes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecetasAdapter(private var recetas: List<Comida>, private val onClick: (Comida) -> Unit) :
    RecyclerView.Adapter<RecetasAdapter.RecetaViewHolder>() {

    class RecetaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombre: TextView = view.findViewById(R.id.nombreReceta)
        val dificultad: TextView = view.findViewById(R.id.dificultadReceta)
        val tiempo: TextView = view.findViewById(R.id.tiempoReceta)
        val imagen: ImageView = view.findViewById(R.id.imagenReceta)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecetaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_receta, parent, false)
        return RecetaViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecetaViewHolder, position: Int) {
        val receta = recetas[position]
        holder.nombre.text = receta.nombre
        holder.dificultad.text = receta.dificultad
        holder.tiempo.text = receta.tiempo
        Glide.with(holder.imagen.context).load(receta.imagenUrl).into(holder.imagen)
        holder.itemView.setOnClickListener { onClick(receta) }
    }

    override fun getItemCount() = recetas.size

    fun updateRecetas(newRecetas: List<Comida>) {
        recetas = newRecetas
        notifyDataSetChanged()
    }
}
