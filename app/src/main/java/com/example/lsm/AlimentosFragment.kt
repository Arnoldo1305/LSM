package com.example.lsm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SearchView
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
        val btnVolver = root.findViewById<ImageView>(R.id.btnVolver)
        //val searchView = root.findViewById<SearchView>(R.id.searchView)

        btnVolver.setOnClickListener{
            findNavController().popBackStack()
        }

        return root
    }

}