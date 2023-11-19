package com.example.aplicacionservicios.controlador

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.example.aplicacionservicios.entidad.Trabajador
import com.example.aplicacionservicios.utils.appConfig

class ArregloTrabajador {
    fun listado():ArrayList<Trabajador>{
        var data=ArrayList<Trabajador>()
        //abrir el acceso a la base de datos en modo lectura
        var cn: SQLiteDatabase = appConfig.BD.readableDatabase
        //sentencia SQL
        var SQL="select *from tb_trabajador"
        //ejecutar sentencia SQL
        //guardar el valor de retorno del método rawQuery
        //en un objeto de tipo Cursor
        var rs=cn.rawQuery(SQL,null)
        //bucle para realizar recorido  sobre el objeto rs
        while(rs.moveToNext()) {
            //crear objeto de la clase Docente
            var bean = Trabajador(
                rs.getInt(0),
                rs.getString(1),
                rs.getString(2),
                rs.getString(3),
                rs.getInt(4),
                rs.getString(5))
            //enviar objeto al arreglo data
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
        row.put("apel",bean.apellido)
        row.put("tele",bean.telefono)
        row.put("edad",bean.edad)
        row.put("foto",bean.foto)
        //invocar al método insert
        salida=cn.insert("tb_trabajador","cod",row).toInt()
        return salida
    }


    fun actualizar(bean:Trabajador):Int{
        var salida=-1
        //abrir acceso a la base de datos en modo escritura
        var cn=appConfig.BD.writableDatabase
        //crear objeto de la clase ContentValues
        var row= ContentValues()
        //claves
        row.put("cod",bean.codigo)
        row.put("nomb",bean.nombre)
        row.put("apel",bean.apellido)
        row.put("tele",bean.telefono)
        row.put("edad",bean.edad)
        row.put("foto",bean.foto)
        //invocar al método update
        salida=cn.update("tb_trabajador",row,"cod=?",
            arrayOf(bean.codigo.toString()))
        return salida
    }
    fun eliminar(cod:Int):Int{
        var salida=-1
        //abrir acceso a la base de datos en modo escritura
        var cn=appConfig.BD.writableDatabase
        //invocar al método update
        salida=cn.delete("tb_trabajador","cod=?",
            arrayOf(cod.toString()))
        return salida
    }

}