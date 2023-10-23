package com.example.aplicacionservicios.data

import android.app.Application
import android.content.Context

class appConfig: Application() {

    //Variables globales:
    companion object{
        lateinit var CONTEXT:Context
        lateinit var BDNAME:String
        var VERSION:Int=1
    }

    //Inicializar variables globales:
    override fun onCreate(){
        super.onCreate()
        BDNAME="basededatos"
        CONTEXT=applicationContext
    }




}