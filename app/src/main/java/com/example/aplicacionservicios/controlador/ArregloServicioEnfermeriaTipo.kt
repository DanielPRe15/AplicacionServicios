package com.example.aplicacionservicios.controlador

import android.database.sqlite.SQLiteDatabase
import com.example.aplicacionservicios.utils.appConfig

class ArregloServicioEnfermeriaTipo {
    fun listadoTipos(): ArrayList<String> {
        var data = ArrayList<String>()
        var cn: SQLiteDatabase = appConfig.BD.readableDatabase
        var SQL = "select *from tb_tiposervicioenfe"
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
        val SQL = "SELECT precio FROM tb_tiposervicioenfe WHERE cod_tiposervicioenfe = $codigo"
        val rs = cn.rawQuery(SQL, null)
        if (rs.moveToFirst()) {
            precio = rs.getDouble(0)
        }
        rs.close()
        return precio
    }
}
