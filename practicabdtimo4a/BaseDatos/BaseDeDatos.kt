package com.softim.practicabdtimo4a.BaseDatos

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.softim.practicabdtimo4a.Modelos.Usuario

class BaseDeDatos(context: Context): SQLiteOpenHelper(context,
    DATABASE_NAME, null, DATABASE_VER)
{

    //Companion object es un objeto compartido de la clase que nos permite utilizar
    //sus elementos dentro y fuera de la clase
    companion object{
        private val DATABASE_NAME = "ejemplo1" //define el nombre de la BD
        private val DATABASE_VER = 1  //Define la version de la BD
        private val TABLE_NAME = "users"  //Define el nombre de una tabla
        private val COL_ID = "id"  //Define el nombre de una columna de la tabla
        private val COL_NOMBRE = "nombre" //Define el nombre de otra columna
        private val COL_EMAIL = "email"  //Define el nombre de otra columna
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val sentencia = "create table $TABLE_NAME ($COL_ID integer primary key, " +
                "$COL_NOMBRE text, $COL_EMAIL text)"
        p0!!.execSQL(sentencia)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0!!.execSQL("drop table if exists $TABLE_NAME")
        onCreate(p0)
    }

    fun insertarUser(usuario: Usuario){
        val db = this.writableDatabase
        val valores = ContentValues()
        valores.put(COL_ID, usuario.id)
        valores.put(COL_NOMBRE, usuario.nombre)
        valores.put(COL_EMAIL, usuario.email)
        db.insert(TABLE_NAME, null, valores)
        db.close()
    }

    fun eliminarUsuario(usuario: Usuario){
        val db = this.writableDatabase
        db.delete(TABLE_NAME, "$COL_ID=?",
            arrayOf(usuario.id.toString()))
        db.close()
    }

    fun actualizarUsuario(usuario: Usuario){
        val db = this.writableDatabase
        val valores = ContentValues()
        valores.put(COL_ID, usuario.id)
        valores.put(COL_NOMBRE, usuario.nombre)
        valores.put(COL_EMAIL, usuario.email)
        db.update(TABLE_NAME, valores, "$COL_ID=?",
            arrayOf(usuario.id.toString()))
        db.close()
    }

    val consulta: List<Usuario>
        get() {
            val listaUsers = ArrayList<Usuario>()
            val consul = "SELECT * FROM $TABLE_NAME"
            val db = this.writableDatabase
            val cantidad = db.rawQuery(consul, null)
            if (cantidad.moveToFirst()){
                do {
                    val user1 = Usuario()
                    user1.id = cantidad.getInt(cantidad.getColumnIndex(COL_ID))
                    user1.nombre = cantidad.getString(cantidad.getColumnIndex(COL_NOMBRE))
                    user1.email = cantidad.getString(cantidad.getColumnIndex(COL_EMAIL))
                    listaUsers.add(user1)
                }while (cantidad.moveToNext())
            }
            return listaUsers
        }


}








