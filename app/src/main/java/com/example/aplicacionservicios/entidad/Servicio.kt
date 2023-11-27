package com.example.aplicacionservicios.entidad

import java.io.Serializable

class Servicio(var codigo:Int,
               var nombre:String,
               var codigoTrabajador:Int,
               var foto:String
                ): Serializable {
}