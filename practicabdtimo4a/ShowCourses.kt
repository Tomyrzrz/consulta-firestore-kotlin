package com.softim.practicabdtimo4a

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.softim.practicabdtimo4a.Modelos.Courses
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.activity_show_courses.*
import kotlinx.android.synthetic.main.item_rcv_course.view.*

class ShowCourses : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_courses)

        this.setTitle("Examen del 4to")


        rcv_cursos.setHasFixedSize(true)
        rcv_cursos.layoutManager = LinearLayoutManager(this)
        cargarDatos()
    }

    private fun cargarDatos(){
        val reference = FirebaseFirestore.getInstance().collection("Cursos")
            reference.get().addOnSuccessListener {
                val adaptador = GroupAdapter<GroupieViewHolder>()
                for (document in it!!.documents) {
                    val dato = document.toObject(Courses::class.java)
                    if (dato != null)
                        adaptador.add(CursoItem(dato))
                }
                rcv_cursos.adapter = adaptador
            }
    }

    class CursoItem(val curso:Courses): Item<GroupieViewHolder>(){
        override fun getLayout(): Int {
            return R.layout.item_rcv_course
        }
        override fun bind(viewHolder: GroupieViewHolder, position: Int) {
            viewHolder.itemView.txt_carrera_text.text = curso.getCareer()
            viewHolder.itemView.txt_grupo_text.text = curso.getGroup()
            viewHolder.itemView.txt_materias_text.text = curso.getSubjects()
            viewHolder.itemView.txt_ncurso_texto.text = curso.getNameCurso()
            viewHolder.itemView.txt_teacher_text.text = curso.getTeacher()
        }

    }


}
