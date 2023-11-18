package com.example.aplicacionservicios.entidad

import java.io.Serializable
import java.util.Date

class ServicioElectrico(
    var codigoServicioTec:Int,
    var codigoServi: Int,
    var codigoTipo: Int,
    var nombreCliente:String,
    var telefonoCliente:String,
    var fecha: Date?,
    var direccionCliente:String,
    var informacionAdicional:String,
    ):Serializable{
}