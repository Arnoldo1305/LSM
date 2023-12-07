package com.example.lsm

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/android_myslq/register.php")
    fun obtenerPalabra(@Query("id") id: Int): Call<Palabra>
    @GET("/android_myslq/selectCategoria.php")
    fun obtenerCategorias(@Query("id") id: Int): Call<Categorias>
}
