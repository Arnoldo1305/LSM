package com.example.lsm

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.navigation.fragment.findNavController

class AlimentosFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_alimentos, container, false)
        val btnVolver = root.findViewById<Button>(R.id.back_pressed)

        btnVolver.setOnClickListener{
            findNavController().navigate(R.id.action_menu_Fragment_self)
        }
        return root
    }
}