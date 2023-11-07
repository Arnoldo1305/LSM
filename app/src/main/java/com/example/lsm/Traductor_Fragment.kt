package com.example.lsm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.navigation.fragment.findNavController

class Traductor_Fragment : Fragment() {
    lateinit var txtEntrada: EditText
    lateinit var lblSalida: TextView
    lateinit var dbHelper: DataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dbHelper = DataBase(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_traductor_, container, false)
        val btnBack = root.findViewById<Button>(R.id.btnBack)
        val btnTraducir = root.findViewById<Button>(R.id.btnTraducir)
        val btnEscuchar = root.findViewById<ImageButton>(R.id.btnEscuchar)
        txtEntrada = root.findViewById(R.id.txtEntrada)
        lblSalida = root.findViewById(R.id.lblSalida)

        btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_traductor_Fragment_pop)
        }

        btnTraducir.setOnClickListener {
            val textoIngresado = txtEntrada.text.toString()
            if (textoIngresado.isNotEmpty()) {
                val palabra = Palabra()
                palabra.señaImagen = ""
                palabra.texto = textoIngresado
                val resultado = dbHelper.insertarDatos(palabra)
            }
        }

        btnEscuchar.setOnClickListener {
            val datos = dbHelper.listarDatos()
            if (datos.isNotEmpty()) {
                val stringBuilder = StringBuilder()
                for (dato in datos) {
                    stringBuilder.append("ID: ${dato.id}, Texto: ${dato.texto}\n")
                }
                lblSalida.text = stringBuilder.toString()
            } else {
                lblSalida.text = "No hay datos disponibles en la base de datos."
            }
        }


        return root
    }
}
