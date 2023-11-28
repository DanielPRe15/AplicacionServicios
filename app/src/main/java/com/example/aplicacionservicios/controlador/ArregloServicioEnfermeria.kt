package com.example.aplicacionservicios.controlador

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.example.aplicacionservicios.entidad.ServicioEnfermeria
import com.example.aplicacionservicios.entidad.ServicioPlomeria
import com.example.aplicacionservicios.utils.appConfig
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ArregloServicioEnfermeria {

    fun adicionar(bean: ServicioEnfermeria): Int {
        var salida = -1
        //abrir acceso a la base de datos en modo escritura
        var cn = appConfig.BD.writableDatabase
        //crear objeto de la clase ContentValues
        var row = ContentValues()

        // Convertir la fecha a una cadena de texto formateada
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss") // Formato de fecha deseado
        val fechaFormateada = sdf.format(bean.fecha)

        //claves
        row.put("cod_servicio", bean.codigoServi)
        row.put("cod_tiposervicioenfe", bean.codigoTipo)
        row.put("nom_cliente", bean.nombreCliente)
        row.put("telef_cliente", bean.telefonoCliente)
        row.put("fecha", fechaFormateada)
        row.put("direc_cliente", bean.direccionCliente)
        row.put("informacioadi", bean.informacionAdicional)

        //invocar al método insert
        salida = cn.insert("tb_servicioenfe", "cod_servicioenfe", row).toInt()
        return salida
    }

    fun listado():ArrayList<ServicioEnfermeria>{
        var data=ArrayList<ServicioEnfermeria>()
        //abrir el acceso a la base de datos en modo lectura
        var cn: SQLiteDatabase = appConfig.BD.readableDatabase
        //sentencia SQL
        var SQL="select * from tb_servicioenfe"


        //ejecutar sentencia SQL
        //guardar el valor de retorno del método rawQuery
        //en un objeto de tipo Cursor
        var rs=cn.rawQuery(SQL,null)
        //bucle para realizar recorido  sobre el objeto rs
        while(rs.moveToNext()) {
            //crear objeto de la clase Docente

            val codigoServicio = rs.getInt(0)
            val codigoServi = rs.getInt(1)
            val codigoTipo = rs.getInt(2)
            val nombreCliente = rs.getString(3)
            val telefonoCliente = rs.getString(4)
            val fechaString = rs.getString(5)
            val direccionCliente = rs.getString(6)
            val informacionAdicional = rs.getString(7)

            // Convertir la cadena de fecha a un objeto Date
            val fecha = convertirStringADate(fechaString)

            val bean = ServicioEnfermeria(codigoServicio, codigoServi, codigoTipo,
                nombreCliente, telefonoCliente, fecha, direccionCliente, informacionAdicional)

            data.add(bean)
        }
        return data
    }

    private fun convertirStringADate(fechaString: String): Date {
        // Aquí debes implementar la lógica para convertir la cadena a un objeto Date
        // Puedes utilizar SimpleDateFormat u otras clases de java.time si estás usando Java 8 o superior.
        // Ejemplo con SimpleDateFormat:
        val formato = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
        return formato.parse(fechaString) ?: Date()
    }
}