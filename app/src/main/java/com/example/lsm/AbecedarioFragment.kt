package com.example.lsm

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.navigation.fragment.findNavController

class AbecedarioFragment : Fragment() {
    lateinit var dbHelper: DataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dbHelper = DataBase(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_abecedario, container, false)
        val arrayAdapter:ArrayAdapter<*>
        val palabras = traerDatos()
        val lvDatos = root.findViewById<ListView>(R.id.lvDatos)

        arrayAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1,palabras)
        lvDatos.adapter = arrayAdapter

        lvDatos.setOnItemClickListener(){parent,view,position,id->
            //findNavController().navigate(R.id.action_categoriasFragment_to_abecedarioFragment)
        }
        return root
    }

    private fun traerDatos():List<String> {
        val datos = dbHelper.listarDatos()
        val palabras: MutableList<String> = arrayListOf();
        if (datos.isNotEmpty()) {
            for (dato in datos) {
                palabras.add(dato.texto)
            }
            return palabras
        } else {
            val resp: List<String> = listOf<String>() + "No hay datos disponibles en la base de datos."
            return resp
        }
    }

}