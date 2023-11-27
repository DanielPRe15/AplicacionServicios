package com.example.aplicacionservicios.controlador

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.example.aplicacionservicios.entidad.ServicioTecnico
import com.example.aplicacionservicios.utils.appConfig
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ArregloServicioTecnico {


    fun adicionar(bean: ServicioTecnico): Int {
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
        row.put("cod_tiposervicotec", bean.codigoTipo)
        row.put("nom_cliente", bean.nombreCliente)
        row.put("telef_cliente", bean.telefonoCliente)
        row.put("fecha", fechaFormateada)
        row.put("direc_cliente", bean.direccionCliente)
        row.put("informacioadi", bean.informacionAdicional)

        //invocar al método insert
        salida = cn.insert("tb_serviciotec", "cod_servicotec", row).toInt()
        return salida
    }


    fun listado():ArrayList<ServicioTecnico>{
        var data=ArrayList<ServicioTecnico>()
        //abrir el acceso a la base de datos en modo lectura
        var cn: SQLiteDatabase = appConfig.BD.readableDatabase
        //sentencia SQL
        var SQL="select * from tb_serviciotec"




        val dateString = ""
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
        var fech_form : java.util.Date = Date()


        try {
            // Convierte la cadena de fecha a un objeto Date
            val date: Date = dateFormat.parse(dateString)

            // Ahora 'date' es un objeto de tipo Date
            println("Fecha convertida: $date")
            fech_form = date
        } catch (e: Exception) {
            println("Error al convertir la fecha: ${e.message}")
        }
        //ejecutar sentencia SQL
        //guardar el valor de retorno del método rawQuery
        //en un objeto de tipo Cursor
        var rs=cn.rawQuery(SQL,null)
        //bucle para realizar recorido  sobre el objeto rs
        while(rs.moveToNext()) {
            //crear objeto de la clase Docente

            val codigoServicioTec = rs.getInt(0)
            val codigoServi = rs.getInt(1)
            val codigoTipo = rs.getInt(2)
            val nombreCliente = rs.getString(3)
            val telefonoCliente = rs.getString(4)
            val fechaString = rs.getString(5)
            val direccionCliente = rs.getString(6)
            val informacionAdicional = rs.getString(7)

            // Convertir la cadena de fecha a un objeto Date
            val fecha = convertirStringADate(fechaString)

            val bean = ServicioTecnico(codigoServicioTec, codigoServi, codigoTipo,
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

    fun actualizar(bean:ServicioTecnico):Int{
        var salida=-1
        var cn=appConfig.BD.writableDatabase
        var row= ContentValues()
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss") // Formato de fecha deseado
        val fechaFormateada = sdf.format(bean.fecha)

        row.put("cod_servicotec",bean.codigoServicioTec)
        row.put("cod_servicio",bean.codigoServi)
        row.put("cod_tiposervicotec",bean.codigoTipo)
        row.put("nom_cliente",bean.nombreCliente)
        row.put("telef_cliente",bean.telefonoCliente)
        row.put("fecha",fechaFormateada)
        row.put("direc_cliente",bean.direccionCliente)
        row.put("informacioadi",bean.informacionAdicional)

        salida=cn.update("tb_serviciotec",row,"cod_servicotec=?",
            arrayOf(bean.codigoServicioTec.toString()))
        return salida
    }
    fun eliminar(cod1:Int):Int{
        var salida=-1
        //abrir acceso a la base de datos en modo escritura
        var cn=appConfig.BD.writableDatabase
        //invocar al método update
        salida=cn.delete("tb_serviciotec","cod_servicotec=?",
            arrayOf(cod1.toString()))
        return salida
    }




}


