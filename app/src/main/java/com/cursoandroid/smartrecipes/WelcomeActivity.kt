package com.cursoandroid.smartrecipes

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cursoandroid.smartrecipes.databinding.ActivityWelcomeBinding
import com.squareup.picasso.Picasso  // Importar Picasso

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializar ViewBinding
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Usar Picasso para cargar la imagen de fondo desde una URL
        Picasso.get()
            .load("https://i.pinimg.com/236x/64/fe/43/64fe435ccdcea440c6a7a9fe9a9ce26c.jpg")
            .into(binding.backgroundImage)  // Cargar la imagen en el ImageView

        Picasso.get()
            .load("https://img.freepik.com/vector-premium/diseno-logotipo-empresa-restaurantes-alimentos_1253202-63873.jpg")  // Reemplaza esta URL con la del logo
            .into(binding.appLogo)  // Cargar la imagen en el ImageView del logo

        // Acción al hacer clic en el botón "Iniciar"
        binding.startButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()  // Cierra la actividad de bienvenida
        }

        // Acción al hacer clic en el botón "Salir"
        binding.exitButton.setOnClickListener {
            finish()  // Cierra la aplicación
        }

        // Evento cuando el mouse pasa sobre el botón "Iniciar"
        binding.startButton.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    // Mostrar un mensaje cuando se toca el botón Iniciar
                    Toast.makeText(this, "¡Vas a iniciar!", Toast.LENGTH_SHORT).show()
                }
                MotionEvent.ACTION_UP -> {
                    // Mostrar un mensaje cuando el dedo se levanta del botón
                    Toast.makeText(this, "¡Estás listo para empezar!", Toast.LENGTH_SHORT).show()
                }
            }
            false
        }

        // Evento cuando el mouse pasa sobre el botón "Salir"
        binding.exitButton.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    // Mostrar un mensaje cuando se toca el botón Salir
                    Toast.makeText(this, "¡Vas a salir!", Toast.LENGTH_SHORT).show()
                }
                MotionEvent.ACTION_UP -> {
                    // Mostrar un mensaje cuando el dedo se levanta del botón
                    Toast.makeText(this, "¡Aplicación cerrada!", Toast.LENGTH_SHORT).show()
                }
            }
            false
        }
    }
}
