package com.example.aplicacionservicios.base

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
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
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}