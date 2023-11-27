package com.example.aplicacionservicios.controlador

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.database.Cursor
import android.widget.Toast
import com.example.aplicacionservicios.base.InitBD
import com.example.aplicacionservicios.entidad.Servicio
import com.example.aplicacionservicios.utils.appConfig

class ArregloServicio {
    fun listado(): ArrayList<Servicio> {
        var data = ArrayList<Servicio>()
        //abrir el acceso a la base de datos en modo lectura
        var cn: SQLiteDatabase=appConfig.BD.readableDatabase
        //sentencia SQL
        var SQL = "select * from tb_servicio"
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



    fun adicionar(bean:Servicio):Int{
        var salida=-1
        //abrir acceso a la base de datos en modo escritura
        var cn=appConfig.BD.writableDatabase
        //crear objeto de la clase ContentValues
        var row= ContentValues()
        //claves
        row.put("nomb",bean.nombre)
        row.put("foto",bean.foto)
        row.put("codTrab",bean.codigoTrabajador)
        //invocar al método insert
        salida=cn.insert("tb_servicio","cod",row).toInt()
        return salida
    }
    fun actualizar(bean:Servicio):Int{
        var salida=-1
        //abrir acceso a la base de datos en modo escritura
        var cn=appConfig.BD.writableDatabase
        //crear objeto de la clase ContentValues
        var row= ContentValues()
        //claves
        row.put("cod",bean.codigo)
        row.put("nomb",bean.nombre)
        row.put("foto",bean.foto)
        row.put("codTrab",bean.codigoTrabajador)
        //invocar al método update
        salida=cn.update("tb_servicio",row,"cod=?",
            arrayOf(bean.codigo.toString()))
        return salida
    }
    fun eliminar(cod:Int):Int{
        var salida=-1
        //abrir acceso a la base de datos en modo escritura
        var cn=appConfig.BD.writableDatabase
        //invocar al método update
        salida=cn.delete("tb_servicio","cod=?",
            arrayOf(cod.toString()))
        return salida
    }










}


   /* fun listado():ArrayList<Servicio>{
        var data=ArrayList<Servicio>()
            data.add(Servicio('Servicio Tecnico','s1'))
            data.add(Servicio('Plomeria','s2'))
            return  data
    }*/
