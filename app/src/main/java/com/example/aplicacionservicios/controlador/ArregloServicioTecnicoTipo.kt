package com.example.aplicacionservicios.controlador

import android.database.sqlite.SQLiteDatabase
import com.example.aplicacionservicios.utils.appConfig

class ArregloServicioTecnicoTipo {
    fun listadoTipos():ArrayList<String>{
        var data=ArrayList<String>()
        var cn: SQLiteDatabase = appConfig.BD.readableDatabase
        var SQL="select *from tb_tiposerviciotec"
        var rs=cn.rawQuery(SQL,null)
        while(rs.moveToNext()) {
            var bean = rs.getString(1)
            data.add(bean)
        }
        return data
    }
    fun buscarNombreTipo(cod_tiposervicotec:Int):String{
        var data=""
        var cn: SQLiteDatabase = appConfig.BD.readableDatabase
        var SQL="select nombre from tb_tiposerviciotec where cod_tiposervicotec=?"
        var rs=cn.rawQuery(SQL, arrayOf(cod_tiposervicotec.toString()))
        if(rs.moveToNext()) {
            data= rs.getString(0)
        }
        return data
    }
}