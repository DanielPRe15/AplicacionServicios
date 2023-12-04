package com.example.aplicacionservicios.controlador

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.example.aplicacionservicios.entidad.Trabajador
import com.example.aplicacionservicios.utils.appConfig

class ArregloTrabajador {
    fun listado():ArrayList<Trabajador>{
        var data=ArrayList<Trabajador>()
        var cn: SQLiteDatabase = appConfig.BD.readableDatabase
        var SQL="select * from tb_trabajador"
        var rs=cn.rawQuery(SQL,null)
        while(rs.moveToNext()) {
            var bean = Trabajador(
                rs.getInt(0),
                rs.getString(1),
                rs.getString(2),
                rs.getString(3),
                rs.getInt(4),
                rs.getString(5))

            data.add(bean)
        }
        return data
    }


    fun listadoTrabajador(): ArrayList<String>{
        var data = ArrayList<String>()
        var cn: SQLiteDatabase = appConfig.BD.readableDatabase
        var SQL = "select *from tb_trabajador"
        var rs = cn.rawQuery(SQL, null)
        while (rs.moveToNext()) {
            var bean = rs.getString(1)

            data.add(bean)
        }
        return data
    }
    fun adicionar(bean:Trabajador):Int{
        var salida=-1
        //abrir acceso a la base de datos en modo escritura
        var cn=appConfig.BD.writableDatabase
        //crear objeto de la clase ContentValues
        var row= ContentValues()
        //claves
        row.put("nomb",bean.nombre)
        row.put("apell",bean.apellido)
        row.put("telefono",bean.telefono)
        row.put("edad",bean.edad)
        row.put("foto",bean.foto)
        //invocar al método insert
        salida=cn.insert("tb_trabajador","cod_trabajador",row).toInt()
        return salida
    }


    fun actualizar(bean:Trabajador):Int{
        var salida=-1
        //abrir acceso a la base de datos en modo escritura
        var cn=appConfig.BD.writableDatabase
        //crear objeto de la clase ContentValues
        var row= ContentValues()
        //claves
        row.put("nomb",bean.nombre)
        row.put("apell",bean.apellido)
        row.put("telefono",bean.telefono)
        row.put("edad",bean.edad)
        row.put("foto",bean.foto)
        //invocar al método update
        salida=cn.update("tb_trabajador",row,"cod_trabajador=?",
            arrayOf(bean.codigo.toString()))
        return salida
    }
    fun eliminar(cod:Int):Int{
        var salida=-1
        //abrir acceso a la base de datos en modo escritura
        var cn=appConfig.BD.writableDatabase
        //invocar al método update
        salida=cn.delete("tb_trabajador","cod_trabajador=?",
            arrayOf(cod.toString()))
        return salida
    }

}