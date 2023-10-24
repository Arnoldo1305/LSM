package com.example.lsm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

class info_Fragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_info_, container, false)
        val btnBack = root.findViewById<Button>(R.id.btnBack)

        btnBack.setOnClickListener{
            findNavController().navigate(R.id.action_info_Fragment_pop)
        }
        return root
    }
}