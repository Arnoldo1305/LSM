package com.example.lsm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

class menu_Fragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_menu_, container, false)
        val btnNavigate = root.findViewById<Button>(R.id.btnNavegar)

        btnNavigate.setOnClickListener{
            findNavController().navigate(R.id.action_menu_Fragment_self)
        }
        return root
    }
}