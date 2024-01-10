package com.example.lsm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.SearchView
import androidx.fragment.app.Fragment
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
        val palabras = traerDatosPalabras()
        //val descripcion = traerDatosDescripcion()
        val lvDatos = root.findViewById<ListView>(R.id.lvDatos)
        val btnVolver = root.findViewById<ImageView>(R.id.btnVolver)
        val searchView = root.findViewById<SearchView>(R.id.searchView)

        arrayAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1,palabras)
        lvDatos.adapter = arrayAdapter

        lvDatos.setOnItemClickListener(){parent,view,position,id->
            val palabra = parent.getItemAtPosition(position).toString()
            val descripcion = traerDatosDescripcion(palabra)
            // Crear un Bundle y pone los datos en él, los cuales seran enviados mediante el navController
            val bundle = Bundle().apply {
                putString("palabra", palabra)
                putString("descripcion", descripcion)
            }

            findNavController().navigate(R.id.action_abecedarioFragment_to_infoPalabraFragment, bundle)
        }

        btnVolver.setOnClickListener{
            findNavController().popBackStack()
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                arrayAdapter.filter.filter(newText)
                return true
            }
        })

        return root
    }

    //Funcion para traer los datos de nuestra BD
    private fun traerDatosPalabras():List<String> {
        val datos = dbHelper.listarDatos()
        val palabras: MutableList<String> = arrayListOf()
        if (datos.isNotEmpty()) {
            for (dato in datos) {
                palabras.add(dato.texto)
            }
            return palabras
        } else {
            return listOf("No hay datos disponibles en la base de datos.")
        }
    }

    //Funcion para traer la descripcion de las palabras de nuestra BD
    private fun traerDatosDescripcion(palabra: String): String {
        val datos = dbHelper.listarDatos()
        if (datos.isNotEmpty()) {
            for (dato in datos) {
                if (dato.texto == palabra) {
                    return dato.descripcion
                }
            }
            //return "Descripción no encontrada."
        }
        return "Descripción no encontrada."
    }
}