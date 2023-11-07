package com.example.lsm

import android.annotation.SuppressLint
import android.content.ContentUris
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

val BD = "baseDatos"
val tabla="Palabra"

class DataBase(contexto: Context): SQLiteOpenHelper(contexto,BD,null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTableSQL = "CREATE TABLE $tabla (id INTEGER PRIMARY KEY AUTOINCREMENT, señaImagen VARCHAR(250), texto VARCHAR(250))"
        db?.execSQL(createTableSQL)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun insertarDatos(palabra: Palabra): String {
        val db = this.writableDatabase
        val contenedor = ContentValues().apply {
            put("señaImagen", palabra.señaImagen)
            put("texto", palabra.texto)
        }
        var resultado = db.insert(tabla, null, contenedor)
        if (resultado == -1.toLong()) {
            return "existio una falla en la base de datos"
        } else {
            return "Insert (ok)"
        }
    }



    fun listarDatos(): List<Palabra> {
        var lista: MutableList<Palabra> = ArrayList()
        val db = this.readableDatabase
        val sql = "SELECT * FROM Palabra";
        var resultado = db.rawQuery(sql, null)
        if (resultado.moveToFirst()) {
            do {
                var palabra = Palabra()
                palabra.id = resultado.getString(resultado.getColumnIndex("id")).toInt()
                palabra.señaImagen = resultado.getString(resultado.getColumnIndex("señaImagen"))
                palabra.texto = resultado.getString(resultado.getColumnIndex("texto"))
                lista.add(palabra)
            } while (resultado.moveToNext())
            resultado.close()
            db.close()
            return (lista)
        }
        return lista
    }

    fun actualizarDatos(id:String,señaImagen:String,texto:String):String{
        val db=this.writableDatabase
        var contenedor= ContentValues();
        contenedor.put("señaImagen",señaImagen)
        contenedor.put("texto",texto)
        var resultado= db.update(tabla,contenedor,"id=?", arrayOf(id))
        if (resultado>0){
            return "Actualizacion Completa"
        }else{
            return  "no se pudo actualizar"
        }
    }
    fun borrarDatos(id:String):String{
        val db = this.writableDatabase
        var resultborrar= 0
        if (id.length>0){
             resultborrar= db.delete(tabla,"id=?", arrayOf(id))
        }
        if (resultborrar>0){
            return "Borrado Exitoso"
        }else{
            return "No se pudo Ejecutar"
        }
    }
}