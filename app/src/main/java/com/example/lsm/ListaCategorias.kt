package com.example.lsm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.cardview.widget.CardView

class ListaCategorias : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_categorias)

        val volverBtn = findViewById<ImageView>(R.id.back_pressed)
        val buscarBtn = findViewById<ImageView>(R.id.search_pressed)
        val abecedarioCard = findViewById<CardView>(R.id.abecedarioCard)
        val animalesCard = findViewById<CardView>(R.id.animalesCard)
        val calendarioCard = findViewById<CardView>(R.id.calendarioCard)
        val coloresCard = findViewById<CardView>(R.id.coloresCard)
        val alimentosCard = findViewById<CardView>(R.id.alimentosCard)
        val numerosCard = findViewById<CardView>(R.id.numerosCard)

        abecedarioCard.setOnClickListener{
            val intent = Intent(this, Abecedario::class.java)
            startActivity(intent)
        }

    }
}