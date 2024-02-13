package com.example.lsm

import android.content.Intent
import android.speech.RecognizerIntent
import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import android.app.AlertDialog
import android.content.Context
import android.widget.ImageView

class Traductor_Fragment : Fragment() {
    lateinit var txtEntrada: EditText
    lateinit var lblSalida: TextView
    lateinit var dbHelper: DataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dbHelper = DataBase(requireContext())

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_traductor_, container, false)
        val btnBack = root.findViewById<ImageView>(R.id.btnVolver)
        val btnTraducir = root.findViewById<Button>(R.id.btnTraducir)
        val btnEscuchar = root.findViewById<ImageButton>(R.id.btnEscuchar)
        txtEntrada = root.findViewById(R.id.txtEntrada)
        lblSalida = root.findViewById(R.id.lblSalida)

        btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        btnTraducir.setOnClickListener {
            val palabrasArray = dividirTextoEnArray(txtEntrada.text.toString())
            traducirPalabras(palabrasArray)

        }

        btnEscuchar.setOnClickListener {
            startSpeechToText()
            traerDatos()
        }
        return root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            val result = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            if (result != null && result.isNotEmpty()) {
                val recognizedText = result[0]
                txtEntrada.setText(recognizedText)
                val palabrasArray = dividirTextoEnArray(txtEntrada.text.toString())
                traducirPalabras(palabrasArray)
            }
        }
    }
    private fun startSpeechToText() {
        val speechIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        speechIntent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Habla ahora")

        startActivityForResult(speechIntent, 1)
    }
    fun subirDatos(){
        val textoIngresado = txtEntrada.text.toString()
        if (textoIngresado.isNotEmpty()) {
            val palabra = Palabra()
            palabra.imagen = ""
            palabra.texto = textoIngresado
            val resultado = dbHelper.insertarDatos(palabra)
        }
    }
    private fun traerDatos(){
        val datos = dbHelper.listarDatos()
        if (datos.isNotEmpty()) {
            val stringBuilder = StringBuilder()
            for (dato in datos) {
                stringBuilder.append("id: ${dato.id}, Texto: ${dato.texto}, Imagen: ${dato.imagen}, desc.: ${dato.descripcion}\n ")
            }
            lblSalida.text = stringBuilder.toString()
        } else {
            lblSalida.text = "No hay datos disponibles en la base de datos."
        }
    }
    fun mostrarResultadosEnAlert(context: Context, resultados: Array<String>) {
        val alertDialog = AlertDialog.Builder(context)
        alertDialog.setTitle("Resultados")
        val mensaje = resultados.joinToString("\n") // Unir las palabras con saltos de línea
        alertDialog.setMessage(mensaje)
        alertDialog.setPositiveButton("Aceptar") { dialog, _ ->
            dialog.dismiss()
        }
        val dialog = alertDialog.create()
        dialog.show()
    }
    private fun dividirTextoEnArray(texto: String): Array<String> {
        // Dividir el texto en palabras usando espacios en blanco como delimitador
        val palabras = texto.split(Regex("\\s+")).toTypedArray()
        return palabras.map { convertirAMayusculas(it) }.toTypedArray()
    }
    private fun convertirAMayusculas(palabra: String): String {
        // Convertir la palabra a mayúsculas
        return palabra.uppercase()
    }
    private fun traducirPalabras(texto: Array<String>){
        val rutas = dbHelper.obtenerPalabras(texto)
        mostrarResultadosEnAlert(requireContext(), rutas)

    }
    private fun crearImagenes(){
        
    }

}
