package com.example.lsm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.cardview.widget.CardView
import androidx.navigation.fragment.findNavController

class CategoriasFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_categorias, container, false)
        val btnCategoriasToAbecedario = root.findViewById<CardView>(R.id.abecedarioCard)
        val btnCategoriasToAlimentos = root.findViewById<CardView>(R.id.alimentosCard)

        btnCategoriasToAbecedario.setOnClickListener{
            findNavController().navigate(R.id.action_categoriasFragment_to_abecedarioFragment)
        }
        btnCategoriasToAlimentos.setOnClickListener{
            findNavController().navigate(R.id.action_categoriasFragment_to_alimentosFragment2)
        }
        return root
    }

}