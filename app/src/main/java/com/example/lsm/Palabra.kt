package com.example.lsm

class Palabra {
    var id:Int=0
    var imagen:String=""
    var texto:String=""
    var id_categoria:Int=0
    var descripcion:String=""

    constructor(imagen:String,texto:String,id_categoria:Int,descripcion:String){
        this.imagen=imagen
        this.texto=texto
        this.id_categoria=id_categoria
        this.descripcion=descripcion
    }
    constructor(){

    }
}