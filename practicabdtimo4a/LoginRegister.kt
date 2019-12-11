package com.softim.practicabdtimo4a

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import kotlinx.android.synthetic.main.activity_login_register.*
import java.util.*


class LoginRegister : AppCompatActivity() {

    //El signo de ? en Kotlin significa que puede o no tener un valor
    private var credenciales : FirebaseAuth ?= null
    private var bd: FirebaseFirestore ?= null
    private var disco: FirebaseStorage ?= null

    //Esta variable para la imagen selected
    private var selectedImage: Uri ?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_register)

        //Inicializa la variable de Autenticacion
        credenciales = FirebaseAuth.getInstance()
        bd = FirebaseFirestore.getInstance()
        disco = FirebaseStorage.getInstance()

        this.setTitle("Login or Register")

        //Comprueba si ya se inicio una sesion
        //Los !! significa que si tienes un valor
        if (credenciales!!.currentUser != null){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        btn_login.setOnClickListener {
            loginUser()
        }

        sign_up_button.setOnClickListener {
            registerUser()
        }

        btn_reset_password.setOnClickListener {
            resetPws()
        }
    }

    private fun resetPws() {

        val correo = edt_email.text.toString()

        if (TextUtils.isEmpty(correo))
            Toast.makeText(this, "Debes poner tu correo",
                Toast.LENGTH_LONG).show()
        else{
            progressBar.visibility = View.VISIBLE
            credenciales!!.sendPasswordResetEmail(correo)
                .addOnCompleteListener {

                    if (it.isSuccessful)
                        Toast.makeText(this, "Send password into your email",
                            Toast.LENGTH_LONG).show()
                    else
                        Toast.makeText(this, "We have a problem dont reset password",
                            Toast.LENGTH_LONG).show()
                    progressBar.visibility = View.GONE
                }
        }

    }

    private fun registerUser() {
        val correo = edt_email.text.toString()
        val contra = edt_password.text.toString()
        when{
            TextUtils.isEmpty(correo) ->
                Toast.makeText(this, "Debes poner un correo",
                    Toast.LENGTH_SHORT).show()
            TextUtils.isEmpty(contra) ->
                Toast.makeText(this, "Debes poner una password",
                    Toast.LENGTH_SHORT).show()
            contra.length < 6 ->
                Toast.makeText(this, "Password too short, enter minimun 6 characters",
                    Toast.LENGTH_SHORT).show()
            else -> {
                progressBar.visibility = View.VISIBLE
                credenciales!!.createUserWithEmailAndPassword(correo, contra)
                    .addOnCompleteListener {
                        Toast.makeText(this,"User Created",
                            Toast.LENGTH_SHORT).show()
                        progressBar.visibility = View.GONE
                        limpiar()
                        if (!it.isSuccessful)
                            Toast.makeText(this, "Problem, User NO created",
                                Toast.LENGTH_SHORT).show()
                        else
                        {
                            startActivity(Intent(this, MainMenu::class.java))
                            finish() //Funcion para terminar la ventana actual
                        }
                    }
            }

        }
    }

    private fun limpiar() {

    }

    private fun loginUser() {
        val correo = edt_email.text.toString()
        val pass = edt_password.text.toString()

        when{
            TextUtils.isEmpty(correo) ->
                Toast.makeText(this, "Email is empty",
                    Toast.LENGTH_SHORT).show()
            TextUtils.isEmpty(pass) ->
                Toast.makeText(this, "Password is empty",
                    Toast.LENGTH_SHORT).show()
            else -> {
                progressBar.visibility = View.VISIBLE
                credenciales!!.signInWithEmailAndPassword(correo, pass)
                    .addOnCompleteListener {
                        progressBar.visibility = View.GONE
                        if (it.isSuccessful){
                            startActivity(Intent(this, MainMenu::class.java))
                            finish()
                        }else{
                            if (pass.length < 6)
                                Toast.makeText(this,"Password is short",
                                    Toast.LENGTH_SHORT).show()
                            else
                                Toast.makeText(this,
                                    "Email incorrect " + it.exception?.message.toString(),
                                    Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }

    }


}














