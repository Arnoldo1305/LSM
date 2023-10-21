package com.example.lsm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

class Menu_Fragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_menu_, container, false)
        val btnNavigate = root.findViewById<Button>(R.id.btnNavegar)
        val btnMenuToCategorias = root.findViewById<Button>(R.id.btnMenuToCategorias)

        btnNavigate.setOnClickListener{
            findNavController().navigate(R.id.action_menu_Fragment_self)
        }
        btnMenuToCategorias.setOnClickListener{
            findNavController().navigate(R.id.action_menu_Fragment_to_prueba_Fragment)
        }
        return root
    }
}