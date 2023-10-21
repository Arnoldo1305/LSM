package com.example.lsm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.cardview.widget.CardView

class Abecedario : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_abecedario)

        val btnVolver = findViewById<ImageView>(R.id.back_pressed)
        val btnBuscar = findViewById<ImageView>(R.id.search_pressed)

        btnVolver.setOnClickListener{
            val intent = Intent(this, ListaCategorias::class.java)
            startActivity(intent)
        }
    }
}