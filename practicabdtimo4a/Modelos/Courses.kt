package com.softim.practicabdtimo4a.Modelos

class Courses {

    private var uid: String = ""
    private var teacher: String = ""
    private var career: String = ""
    private var group: String = ""
    private var subjects: String = ""
    private var nameCurso: String = ""

    constructor()

    constructor(id: String, profe: String, carrera: String, materias:String,
                nombre_curso: String, grupo: String){
        this.uid = id
        this.career = carrera
        this.group = grupo
        this.subjects = materias
        this.teacher = profe
        this.nameCurso = nombre_curso
    }

    fun getNameCurso():String{
        return nameCurso
    }

    fun setNombreCurso(pN_course: String){
        this.nameCurso = pN_course
    }

    fun getUid():String{
        return uid
    }

    fun setUid(pUid: String){
        this.uid = pUid
    }

    fun getGroup():String{
        return group
    }

    fun setGroup(pGropu: String){
        this.group = pGropu
    }


    fun getTeacher():String{
        return teacher
    }

    fun setTeacher(pTeacher: String){
        this.teacher = pTeacher
    }


    fun getSubjects():String{
        return subjects
    }

    fun setSubjects(pSubjects: String){
        this.subjects = pSubjects
    }


    fun getCareer():String{
        return career
    }

    fun setCareer(pCareer: String){
        this.career = pCareer
    }




}