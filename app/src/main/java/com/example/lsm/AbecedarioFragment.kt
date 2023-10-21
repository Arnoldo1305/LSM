package com.example.lsm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

class AbecedarioFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_abecedario, container, false)
        val btnNavigate = root.findViewById<Button>(R.id.back_pressed)

        btnNavigate.setOnClickListener{
            findNavController().navigate(R.id.action_menu_Fragment_self)
        }
        return root
    }
}