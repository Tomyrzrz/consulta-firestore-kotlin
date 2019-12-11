package com.softim.practicabdtimo4a.Adaptadores

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.EditText
import androidx.core.content.getSystemService
import com.softim.practicabdtimo4a.Modelos.Usuario
import com.softim.practicabdtimo4a.R
import kotlinx.android.synthetic.main.row_item.view.*

class AdaptadorUsuarios(internal var actividad: Activity,
                        internal var listUsers: List<Usuario>,
                        internal var edt_id: EditText,
                        internal var edt_name: EditText,
                        internal var edt_email: EditText):BaseAdapter(){

    internal var inflater: LayoutInflater

    init {
        inflater = actividad
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val renglon: View
        renglon = inflater.inflate(R.layout.row_item, null)
        renglon.txt_row_id.text = listUsers[p0].id.toString()
        renglon.txt_row_name.text = listUsers[p0].nombre
        renglon.txt_row_email.text = listUsers[p0].email
        renglon.setOnClickListener {
            edt_id.setText(renglon.txt_row_id.text.toString())
            edt_name.setText(renglon.txt_row_name.text.toString())
            edt_email.setText(renglon.txt_row_email.text.toString())
        }
        return renglon
    }

    override fun getItem(p0: Int): Any {
        return listUsers[p0]
    }

    override fun getItemId(p0: Int): Long {
        return listUsers[p0].id.toLong()
    }

    override fun getCount(): Int {
        return listUsers.size
    }


}