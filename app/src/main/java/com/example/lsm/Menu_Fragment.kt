package com.example.lsm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Menu_Fragment : Fragment() {
    lateinit var dbHelper: DataBase

    val retrofit = Retrofit.Builder()
        .baseUrl("https://apprueba1.000webhostapp.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val apiService = retrofit.create(ApiService::class.java)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dbHelper = DataBase(requireContext())
        val id = 1 // Ajusta el ID según tus necesidades
        obtenerDatos(id)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_menu_, container, false)
        val btnNavigate = root.findViewById<Button>(R.id.btnNavegar)
        val btnMenuToCategorias = root.findViewById<Button>(R.id.btnMenuToCategorias)
        val btnInfo = root.findViewById<Button>(R.id.btnInfo)
        val btnSalir = root.findViewById<Button>(R.id.btnSalir)

        btnNavigate.setOnClickListener{
            findNavController().navigate(R.id.action_menu_Fragment_self)
        }
        btnMenuToCategorias.setOnClickListener{
            findNavController().navigate(R.id.action_menu_Fragment_to_categoriasFragment)
        }
        btnInfo.setOnClickListener{
            findNavController().navigate(R.id.action_menu_Fragment_to_info_Fragment)
        }
        btnSalir.setOnClickListener{
            findNavController().navigate(R.id.action_menu_Fragment_pop)
        }
        return root
    }

    fun obtenerDatos(id: Int) {
        if (!dbHelper.existeRegistro(id)) {
            val call = apiService.obtenerPalabra(id)

            call.enqueue(object : Callback<Palabra> {
                override fun onResponse(call: Call<Palabra>, response: Response<Palabra>) {
                    if (response.isSuccessful) {
                        val palabra = response.body()
                        if (palabra != null) {
                            if (!dbHelper.existeRegistro(id)) {
                                dbHelper.insertarDatos(palabra)
                            }
                            obtenerDatos(id + 1)
                        } else {

                        }
                    } else {
                        // Manejar errores de la respuesta HTTP
                    }
                }
                override fun onFailure(call: Call<Palabra>, t: Throwable) {
                }
            })
        } else {
            obtenerDatos(id + 1)
        }
    }
}