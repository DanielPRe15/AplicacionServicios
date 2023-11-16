package com.example.aplicacionservicios.controlador

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.example.aplicacionservicios.entidad.ServicioTecnico
import com.example.aplicacionservicios.utils.appConfig
import java.text.SimpleDateFormat

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


}


