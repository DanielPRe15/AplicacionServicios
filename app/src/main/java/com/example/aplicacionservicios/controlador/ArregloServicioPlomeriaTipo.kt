package com.example.aplicacionservicios.controlador

import android.database.sqlite.SQLiteDatabase
import com.example.aplicacionservicios.utils.appConfig

class ArregloServicioPlomeriaTipo {
    fun listadoTipos(): ArrayList<String> {
        var data = ArrayList<String>()
        var cn: SQLiteDatabase = appConfig.BD.readableDatabase
        var SQL = "select *from tb_tiposervicioplome"
        var rs = cn.rawQuery(SQL, null)
        while (rs.moveToNext()) {
            var bean = rs.getString(1)

            data.add(bean)
        }
        return data
    }

    fun obtenerPrecioPorCodigo(codigo: Int): Double {
        var precio = 0.0
        val cn: SQLiteDatabase = appConfig.BD.readableDatabase
        val SQL = "SELECT precio FROM tb_tiposervicioplome WHERE cod_tiposervicioplome = $codigo"
        val rs = cn.rawQuery(SQL, null)
        if (rs.moveToFirst()) {
            precio = rs.getDouble(0)
        }
        rs.close()
        return precio
    }
}
