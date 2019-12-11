package com.softim.practicabdtimo4a

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main_menu.*

class MainMenu : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        btn_crear.setOnClickListener {
            startActivity(Intent(this, CreateCourse::class.java))
        }

        btn_salir.setOnClickListener {
            logoutUser()
        }

        btn_ver.setOnClickListener {
            startActivity(Intent(this, ShowCourses::class.java))
        }
    }

    private var credencial : FirebaseAuth?= null

    private fun logoutUser(){
        credencial = FirebaseAuth.getInstance()
        credencial!!.signOut() //Finaliza la sesion del user
        startActivity(Intent(this, LoginRegister::class.java))
        finish()
    }
}
