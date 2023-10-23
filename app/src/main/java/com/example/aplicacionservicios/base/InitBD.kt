package com.example.aplicacionservicios.base

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
<<<<<<< HEAD
import com.example.aplicacionservicios.data.appConfig

class InitBD:SQLiteOpenHelper(appConfig.CONTEXT,
    appConfig.BDNAME, null, appConfig.VERSION) {
    override fun onCreate(bd: SQLiteDatabase) {
        //Crear tabla:
        bd.execSQL("create table tb_Trabajador" +
                "(" +
                "codi integer primary key autoincrement," +
                "nomb varchar(50)," +
                "apell varchar(50)," +
                "celu varchar(12)," +
                "foto varchar(3)," +
                //Tipo de trabajador (electricista, fontanero, etc)
                "codTipo int)")

        //Ingresar registros a la tabla:
        bd.execSQL("insert into tb_Trabajador values(null, 'Juan', 'Vasques', '999111555','fot','1')")
=======
import com.example.aplicacionservicios.utils.appConfig

class InitBD:SQLiteOpenHelper(appConfig.CONTEXT,
    appConfig.BDNAME,
    null,
    appConfig.VERSION) {
    override fun onCreate(bd: SQLiteDatabase) {

        //crear tabla
        bd.execSQL(
            "create table tb_servicio" +
                    "(" +

                    "nom varchar(35)," +
                    "foto varchar(3))"

        )
        //ingresar 6 servicios
        bd.execSQL("insert into tb_servicio values('Servicio Tecnico','s1')")
        bd.execSQL("insert into tb_servicio values('Plomeria','s2')")
        bd.execSQL("insert into tb_servicio values('Electricista','s3')")
        bd.execSQL("insert into tb_servicio values('Limpieza del hogar','s4')")
        bd.execSQL("insert into tb_servicio values('Mecanico','s5')")
        bd.execSQL("insert into tb_servicio values('Enfermeria','s6')")


>>>>>>> 8f54515 (Menu Principal Listado)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
<<<<<<< HEAD
=======

>>>>>>> 8f54515 (Menu Principal Listado)
}