package com.example.lsm

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


val BD = "baseDatos"
val tabla="Palabra"
val tablaCat= "Categorias"

class DataBase(contexto: Context): SQLiteOpenHelper(contexto,BD,null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTableCat = "CREATE TABLE $tablaCat (id INTEGER PRIMARY KEY AUTOINCREMENT, categoria VARCHAR(250))"
        val createTableSQL = "CREATE TABLE $tabla (id INTEGER PRIMARY KEY AUTOINCREMENT, imagen VARCHAR(250), texto VARCHAR(250), id_categoria INTEGER,descripcion VARCHAR(255), FOREIGN KEY (id_categoria) REFERENCES Categorias(id))"
        db?.execSQL(createTableSQL)
        db?.execSQL(createTableCat)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun insertarDatos(palabra: Palabra): String {
        val db = this.writableDatabase
        val contenedor = ContentValues().apply {
            put("imagen", palabra.imagen)
            put("texto", palabra.texto)
            put("id_categoria", palabra.id_categoria)
            put("descripcion", palabra.descripcion)
        }
        var resultado = db.insert(tabla, null, contenedor)
        if (resultado == -1.toLong()) {
            return "existio una falla en la base de datos"
        } else {
            return "Insert (ok)"
        }

    }
    fun insertarCategorias(categorias: Categorias): String {
        val db = this.writableDatabase
        val contenedor = ContentValues().apply {
            put("categoria", categorias.categoria)
        }
        var resultado = db.insert(tablaCat, null, contenedor)
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
                palabra.imagen = resultado.getString(resultado.getColumnIndex("imagen"))
                palabra.texto = resultado.getString(resultado.getColumnIndex("texto"))
                palabra.id_categoria = resultado.getString(resultado.getColumnIndex("id_categoria")).toInt()
                palabra.descripcion = resultado.getString(resultado.getColumnIndex("descripcion"))
                lista.add(palabra)
            } while (resultado.moveToNext())
            resultado.close()
            //db.close()
            return (lista)
        }
        return lista
    }
    fun listarCategorias(): List<Categorias> {
        var lista: MutableList<Categorias> = ArrayList()
        val db = this.readableDatabase
        val sql = "SELECT * FROM Categorias";
        var resultado = db.rawQuery(sql, null)
        if (resultado.moveToFirst()) {
            do {
                var categorias = Categorias()
                categorias.id = resultado.getString(resultado.getColumnIndex("id")).toInt()
                categorias.categoria = resultado.getString(resultado.getColumnIndex("categoria"))
                lista.add(categorias)
            } while (resultado.moveToNext())
            resultado.close()
            //db.close()
            return (lista)
        }
        return lista
    }

    fun actualizarDatos(id:String,imagen:String,texto:String):String{
        val db=this.writableDatabase
        var contenedor= ContentValues();
        contenedor.put("imagen",imagen)
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

    fun existeRegistro(id: Int): Boolean {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM Palabra WHERE id = ?", arrayOf(id.toString()))
        val existe = cursor.count > 0
        cursor.close()
        //db.close()
        return existe
    }
    fun existeCategoria(id: Int): Boolean {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM Categorias WHERE id = ?", arrayOf(id.toString()))
        val existe = cursor.count > 0
        cursor.close()
        //db.close()
        return existe
    }
}