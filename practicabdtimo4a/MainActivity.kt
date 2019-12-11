package com.softim.practicabdtimo4a

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.softim.practicabdtimo4a.Adaptadores.AdaptadorUsuarios
import com.softim.practicabdtimo4a.BaseDatos.BaseDeDatos
import com.softim.practicabdtimo4a.Modelos.Usuario
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    internal lateinit var db: BaseDeDatos
    internal var listaUsuarios: List<Usuario> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = BaseDeDatos(this)
        consultarDatos()
        credencial = FirebaseAuth.getInstance()

        btn_insertar.setOnClickListener {
            val user = Usuario(
                id_usuario.text.toString().toInt(),
                nombre_usuario.text.toString(),
                email_usuario.text.toString()
            )
            db.insertarUser(user)
            consultarDatos()
            clean()
        }

        btn_actualizar.setOnClickListener {
            val user = Usuario(
                id_usuario.text.toString().toInt(),
                nombre_usuario.text.toString(),
                email_usuario.text.toString()
            )
            db.actualizarUsuario(user)
            consultarDatos()
            clean()
        }

        btn_eliminar.setOnClickListener {
            val user = Usuario(
                id_usuario.text.toString().toInt(),
                nombre_usuario.text.toString(),
                email_usuario.text.toString()
            )
            db.eliminarUsuario(user)
            consultarDatos()
            clean()
        }

    }

    private fun clean() {
        id_usuario.setText("")
        nombre_usuario.setText("")
        email_usuario.setText("")
    }

    private fun consultarDatos() {
        listaUsuarios = db.consulta
        val adapter = AdaptadorUsuarios(this, listaUsuarios,
            id_usuario, nombre_usuario, email_usuario)
        lista_usuarios.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_principal, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.Salir){
            logoutUser()
        }else if (id == R.id.menu_dos){
            startActivity(Intent(this, CreateCourse::class.java))
            finish()
        }else if (id == R.id.show_cursos){
            startActivity(Intent(this, ShowCourses::class.java))
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private var credencial : FirebaseAuth ?= null

    private fun logoutUser(){
        credencial!!.signOut() //Finaliza la sesion del user
        startActivity(Intent(this, LoginRegister::class.java))
        finish()
    }

}















