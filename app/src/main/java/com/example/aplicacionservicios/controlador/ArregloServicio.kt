package com.example.aplicacionservicios.controlador

import android.database.sqlite.SQLiteDatabase
import android.database.Cursor
import android.widget.Toast
import com.example.aplicacionservicios.entidad.Servicio
import com.example.aplicacionservicios.utils.appConfig
import com.google.firebase.firestore.util.Util

class ArregloServicio {
    fun listado(): ArrayList<Servicio> {
        var data = ArrayList<Servicio>()
        //abrir el acceso a la base de datos en modo lectura
        var cn: SQLiteDatabase=appConfig.BD.readableDatabase
        //sentencia SQL
        var SQL = "select *from tb_servicio"
        //ejecutar sentencia SQL
        //guardar el valor de retorno del método rawQuery
        //en un objeto de tipo Cursor
        var rs = cn.rawQuery(SQL, null)
        //bucle para realizar recorido  sobre el objeto rs
        while (rs.moveToNext()) {
            //crear objeto de la clase Docente
            var bean = Servicio(
                rs.getInt(0),
                rs.getString(1),
                rs.getInt(2),
                rs.getString(3))
            //enviar objeto al arreglo data
            data.add(bean)
        }
        return data
    }

    fun obtenerCodigoServicio(nombreServicio: String): Int {
        var codigoServicio = -1 // Valor por defecto si no se encuentra el servicio

        val cn: SQLiteDatabase = appConfig.BD.readableDatabase
        val SQL = "SELECT cod_servicio FROM tb_servicio WHERE nom = ?"
        val selectionArgs = arrayOf(nombreServicio)
        val cursor: Cursor = cn.rawQuery(SQL, selectionArgs)

        if (cursor.moveToFirst()) {
            val columnIndex = cursor.getColumnIndex("cod_servicio")
            if (columnIndex != -1) {
                codigoServicio = cursor.getInt(columnIndex)
            } else {

            }
        }

        cursor.close()
        return codigoServicio
    }

    fun obtenerNombreTrabajador(nombreTrabajador: String): String {
        var nombre = "" // Valor por defecto si no se encuentra el trabajador

        val cn: SQLiteDatabase = appConfig.BD.readableDatabase
        val SQL = "SELECT nomb FROM tb_trabajador WHERE nomb = ?"
        val selectionArgs = arrayOf(nombreTrabajador)
        val cursor: Cursor = cn.rawQuery(SQL, selectionArgs)

        if (cursor.moveToFirst()) {
            val columnIndex = cursor.getColumnIndex("nomb")
            if (columnIndex != -1) {
                nombre = cursor.getString(columnIndex)
            }
        }

        cursor.close()
        return nombre
    }


    fun obtenerCodigoTrabajador(nombreTrabajador: String): Int {
        var codigoTrabajador = -1 // Valor por defecto si no se encuentra el trabajador

        val cn: SQLiteDatabase = appConfig.BD.readableDatabase
        val SQL = "SELECT cod_trabajador FROM tb_trabajador WHERE nomb = ?"
        val selectionArgs = arrayOf(nombreTrabajador)
        val cursor: Cursor = cn.rawQuery(SQL, selectionArgs)

        if (cursor.moveToFirst()) {
            val columnIndex = cursor.getColumnIndex("cod_trabajador")
            if (columnIndex != -1) {
                codigoTrabajador = cursor.getInt(columnIndex)
            }
        }

        cursor.close()
        return codigoTrabajador
    }



}


   /* fun listado():ArrayList<Servicio>{
        var data=ArrayList<Servicio>()
            data.add(Servicio('Servicio Tecnico','s1'))
            data.add(Servicio('Plomeria','s2'))
            return  data
    }*/
