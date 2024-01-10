package com.example.lsm

import android.content.Intent.getIntent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.content.Intent
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class InfoPalabraFragment() : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Recibir datos del Bundle
        val bundle = arguments
        val palabra = bundle?.getString("palabra")
        val descripcion = bundle?.getString("descripcion")

        val root = inflater.inflate(R.layout.fragment_info_palabra, container, false)

        val btnVolver = root.findViewById<ImageView>(R.id.btnVolver)
        // Mostrar los datos recibidos en los TextView correspondientes
        val txtTitulo = root.findViewById<TextView>(R.id.txtPalabra)
        val txtDescripcion = root.findViewById<TextView>(R.id.txtDescripcion)

        txtTitulo.text = palabra
        txtDescripcion.text = descripcion

        btnVolver.setOnClickListener{
            findNavController().popBackStack()
        }

        return root
    }

}