package com.softim.practicabdtimo4a

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.softim.practicabdtimo4a.Modelos.Courses
import kotlinx.android.synthetic.main.activity_create_course.*

class CreateCourse : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_course)

        this.setTitle("Crear Curso")

        val teachers = arrayOf("Carlos Alberto", "Ana Karen", "Edlin Lizbeth", "Timo")
        val careers = arrayOf("Biotecnologia", "Gastronomia", "Tecnologias de Informacion")
        val groups = arrayOf("1A TI", "1B TI", "4A TI", "7A TI", "7B TI", "10A TI")

        val adaptador_profes = ArrayAdapter<String>(this,
            android.R.layout.simple_spinner_dropdown_item, teachers)
        spn_teachers_cc.adapter = adaptador_profes

        val adaptador_careers = ArrayAdapter<String>(this,
            android.R.layout.simple_spinner_item, careers)
        spn_carrers_cc.adapter = adaptador_careers

        val adaptador_grupos = ArrayAdapter<String>(this,
            android.R.layout.simple_spinner_dropdown_item, groups)
        spn_groups_cc.adapter = adaptador_grupos

        btn_create_course.setOnClickListener {
            createCoursesFun()
        }

    }

    private fun createCoursesFun(){
        val teacher_val = spn_teachers_cc.selectedItem.toString()
        val subjects_val = cheacarValores()
        val group_val = spn_groups_cc.selectedItem.toString()
        val career_cal = spn_carrers_cc.selectedItem.toString()
        val name_course_val = edt_name_course.text.toString()

        when{
            TextUtils.isEmpty(teacher_val) ->
                Toast.makeText(this, "El profe no ha sido seleccionado",
                    Toast.LENGTH_LONG).show()
            TextUtils.isEmpty(group_val) ->
                Toast.makeText(this, "El grupo no ha sido seleccionado",
                    Toast.LENGTH_LONG).show()
            TextUtils.isEmpty(career_cal) ->
                Toast.makeText(this, "La carrera no ha sido seleccionado",
                    Toast.LENGTH_LONG).show()
            TextUtils.isEmpty(name_course_val) ->
                Toast.makeText(this, "Define un nombre no ha sido seleccionado",
                    Toast.LENGTH_LONG).show()
            TextUtils.isEmpty(subjects_val) ->
                Toast.makeText(this, "No has seleccionado materias",
                    Toast.LENGTH_LONG).show()
            else ->{
                val progression =  ProgressDialog(this)
                progression.setTitle("Create Course")
                progression.setMessage("Creating ....... Wait me")
                progression.setCanceledOnTouchOutside(false)
                progression.show()

                val reference = FirebaseFirestore.getInstance()
                reference.collection("Cursos").document()

                val id = reference.collection("Cursos").document().id

                val course = Courses()
                course.setUid(id)
                course.setCareer(career_cal)
                course.setGroup(group_val)
                course.setNombreCurso(name_course_val)
                course.setSubjects(subjects_val)
                course.setTeacher(teacher_val)

                reference.collection("Cursos").document(id).set(course)
                    .addOnCompleteListener {
                        if (it.isSuccessful){
                            Toast.makeText(this, "Courso Guardado",
                                Toast.LENGTH_LONG).show()
                            progression.dismiss()
                            limpiarCampos()
                        }
                    }
                    .addOnCanceledListener {
                        Toast.makeText(this, "Algo paso, no pudimos crear el curso",
                            Toast.LENGTH_LONG).show()
                    }

            }
        }

    }

    private fun limpiarCampos() {
        edt_name_course.setText("")
        chb_admon.isChecked = false
        chb_bds.isChecked = false
        chb_design.isChecked = false
        chb_english.isChecked = false
        chb_expression.isChecked = false
        chb_modeling.isChecked = false
        chb_network.isChecked = false
        chb_progra.isChecked = false
    }

    private fun cheacarValores(): String {
        var valores_seleccionados = ""

        if (chb_admon.isChecked)
            valores_seleccionados += chb_admon.text.toString() + ","
        if (chb_bds.isChecked)
            valores_seleccionados += chb_bds.text.toString() + ","
        if (chb_design.isChecked)
            valores_seleccionados += chb_design.text.toString() + ","
        if (chb_english.isChecked)
            valores_seleccionados += chb_english.text.toString() + ","
        if (chb_expression.isChecked)
            valores_seleccionados += chb_expression.text.toString() + ","
        if (chb_modeling.isChecked)
            valores_seleccionados += chb_modeling.text.toString() + ","
        if (chb_network.isChecked)
            valores_seleccionados += chb_network.text.toString() + ","
        if (chb_progra.isChecked)
            valores_seleccionados += chb_progra.text.toString() + ","

        return valores_seleccionados
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_principal, menu)
        return super.onCreateOptionsMenu(menu)
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
    private var credencial : FirebaseAuth?= null

    private fun logoutUser(){
        credencial = FirebaseAuth.getInstance()
        credencial!!.signOut() //Finaliza la sesion del user
        startActivity(Intent(this, LoginRegister::class.java))
        finish()
    }

}




