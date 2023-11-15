package com.example.aplicacionservicios.controlador

import android.database.sqlite.SQLiteDatabase
import android.database.Cursor
import com.example.aplicacionservicios.entidad.Servicio
import com.example.aplicacionservicios.utils.appConfig

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

   /* fun listado():ArrayList<Servicio>{
        var data=ArrayList<Servicio>()
            data.add(Servicio('Servicio Tecnico','s1'))
            data.add(Servicio('Plomeria','s2'))
            return  data
    }*/
}