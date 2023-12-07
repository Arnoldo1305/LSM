package com.example.lsm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.util.Log


class Menu_Fragment : Fragment() {
    lateinit var dbHelper: DataBase
    lateinit var txtPrueba: TextView

    val retrofit = Retrofit.Builder()
        .baseUrl("https://apprueba1.000webhostapp.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient())
        .build()
    val apiService = retrofit.create(ApiService::class.java)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dbHelper = DataBase(requireContext())
        val id2 = 1
        obtenerCategorias(id2)
        val id = 1 
        obtenerDatos(id)
        val id3=1
        

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
        txtPrueba = root.findViewById(R.id.txtPrueba)

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
        mostrarRutas()
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
                                obtenerRutas(palabra.imagen)

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
    val listRutas :MutableList<String> = mutableListOf()
    fun obtenerRutas(imagen: String) {
        listRutas.add(imagen)
        Log.d("obtenerrutas", listRutas.toString())
    }
    fun mostrarRutas(){
        Log.d("pruebarutas", listRutas.toString())
        if (listRutas.isNotEmpty()) {
            txtPrueba.text = listRutas.toString()
        } else {
            txtPrueba.text = "La lista de rutas está vacía."
        }
    }

    fun obtenerCategorias(id2: Int) {
        if (!dbHelper.existeCategoria(id2)) {
            val call = apiService.obtenerCategorias(id2)

            call.enqueue(object : Callback<Categorias> {
                override fun onResponse(call: Call<Categorias>, response: Response<Categorias>) {
                    if (response.isSuccessful) {
                        val categorias = response.body()
                        if (categorias != null) {
                            if (!dbHelper.existeCategoria(id2)) {
                                dbHelper.insertarCategorias(categorias)
                            }
                            obtenerCategorias(id2 + 1)
                        } else {

                        }
                    } else {
                        // Manejar errores de la respuesta HTTP
                    }
                }
                override fun onFailure(call: Call<Categorias>, t: Throwable) {
                }
            })
        } else {
            obtenerCategorias(id2 + 1)
        }
    }

}