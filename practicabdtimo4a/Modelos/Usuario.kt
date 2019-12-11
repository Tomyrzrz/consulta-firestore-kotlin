package com.softim.practicabdtimo4a.Modelos

class Usuario {

    var id: Int = 0
    var nombre: String = ""
    var email: String = ""

    constructor()

    constructor(idd: Int, name: String, correo: String){
        this.id = idd
        this.nombre = name
        this.email = correo
    }

}