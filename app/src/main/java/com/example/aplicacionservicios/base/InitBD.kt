package com.example.aplicacionservicios.base

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.aplicacionservicios.utils.appConfig




class InitBD:SQLiteOpenHelper(appConfig.CONTEXT,
    appConfig.BDNAME,
    null,
    appConfig.VERSION) {
    override fun onCreate(bd: SQLiteDatabase) {

        bd.execSQL("create table tb_serviciotec" +
                "(" +
                "cod_servicotec integer primary key autoincrement," +
                "cod_servicio int references tb_servicio," +
                "cod_tiposervicotec int references tb_tiposerviciotec," +
                "nom_cliente varchar(50)," +
                "telef_cliente varchar(20),"+
                "fecha TEXT," +
                "direc_cliente varchar(100)," +
                "informacioadi varchar(100))")

        bd.execSQL("create table tb_servicioplome" +
                "(" +
                "cod_servicioplome integer primary key autoincrement," +
                "cod_servicio int references tb_servicio," +
                "cod_tiposervicioplome int references tb_tiposervicioplome," +
                "nom_cliente varchar(50)," +
                "telef_cliente varchar(20),"+
                "fecha TEXT," +
                "direc_cliente varchar(100)," +
                "informacioadi varchar(100))")

        bd.execSQL("create table tb_servicioelec" +
                "(" +
                "cod_servicioelec integer primary key autoincrement," +
                "cod_servicio int references tb_servicio," +
                "cod_tiposervicioelec int references tb_tiposervicioelec," +
                "nom_cliente varchar(50)," +
                "telef_cliente varchar(20),"+
                "fecha TEXT," +
                "direc_cliente varchar(100)," +
                "informacioadi varchar(100))")

        bd.execSQL("create table tb_serviciolimp" +
                "(" +
                "cod_serviciolimpi integer primary key autoincrement," +
                "cod_servicio int references tb_servicio," +
                "cod_tiposerviciolimpi int references tb_tiposerviciolimp," +
                "nom_cliente varchar(50)," +
                "telef_cliente varchar(20),"+
                "fecha TEXT," +
                "direc_cliente varchar(100)," +
                "informacioadi varchar(100))")

        bd.execSQL("create table tb_serviciomeca" +
                "(" +
                "cod_serviciomeca integer primary key autoincrement," +
                "cod_servicio int references tb_servicio," +
                "cod_tiposerviciomeca int references tb_tiposerviciomeca," +
                "nom_cliente varchar(50)," +
                "telef_cliente varchar(20),"+
                "fecha TEXT," +
                "direc_cliente varchar(100)," +
                "informacioadi varchar(100))")

        bd.execSQL("create table tb_servicioenfe" +
                "(" +
                "cod_servicioenfe integer primary key autoincrement," +
                "cod_servicio int references tb_servicio," +
                "cod_tiposervicioenfe int references tb_tiposervicioenfe," +
                "nom_cliente varchar(50)," +
                "telef_cliente varchar(20),"+
                "fecha TEXT," +
                "direc_cliente varchar(100)," +
                "informacioadi varchar(100))")

// Tablas de los tipos de servicios

        bd.execSQL("create table tb_tiposerviciotec" +
                "(" +
                "cod_tiposervicotec integer primary key autoincrement," +
                "nombre varchar(100)," +
                "precio double )")

        //Ingresar registro de tipo de servicio tecnico
        bd.execSQL("insert into tb_tiposerviciotec values(null,'Reparacion de cableado','40.00')")
        bd.execSQL("insert into tb_tiposerviciotec values(null,'Mantenimiento y limpieza','20.00')")
        bd.execSQL("insert into tb_tiposerviciotec values(null,'Instalacion de equipo','80.00')")



        bd.execSQL("create table tb_tiposervicioplome" +
                "(" +
                "cod_tiposervicioplome integer primary key autoincrement," +
                "nombre varchar(50)," +
                "precio double)")

        //Ingresar registro de tipo de servicio de Plomeria
        bd.execSQL("insert into tb_tiposervicioplome values(null,'Desatoracion','20.00')")
        bd.execSQL("insert into tb_tiposervicioplome values(null,'Reparacion completa','150.50')")
        bd.execSQL("insert into tb_tiposervicioplome values(null,'Instalacion','60.00')")


        bd.execSQL("create table tb_tiposervicioelec" +
                "(" +
                "cod_tiposervicioelec integer primary key autoincrement," +
                "nombre varchar(50)," +
                "precio double)")

        //Ingresar registro de tipo de servicio Electrico
        bd.execSQL("insert into tb_tiposervicioelec values(null,'Reparacion de cableado','70.00')")
        bd.execSQL("insert into tb_tiposervicioelec values(null,'Inspeccion','40.50')")
        bd.execSQL("insert into tb_tiposervicioelec values(null,'Instalacion','60.00')")


        bd.execSQL("create table tb_tiposerviciolimp" +
                "(" +
                "cod_tiposerviciolimpi integer primary key autoincrement," +
                "nombre varchar(50)," +
                "precio double)")

        //Ingresar registro de tipo de servicio de limpieza
        bd.execSQL("insert into tb_tiposerviciolimp values(null,'Limpieza de alfombras y telas','50.00')")
        bd.execSQL("insert into tb_tiposerviciolimp values(null,'Limpieza completa del hogar','80.00')")


        bd.execSQL("create table tb_tiposerviciomeca" +
                "(" +
                "cod_tiposerviciomeca integer primary key autoincrement," +
                "nombre varchar(50)," +
                "precio double)")

        //Ingresar registro de tipo de servicio Mecanico
        bd.execSQL("insert into tb_tiposerviciomeca values(null,'Reparacion completa','70.00')")
        bd.execSQL("insert into tb_tiposerviciomeca values(null,'Inspeccion','50.00')")
        bd.execSQL("insert into tb_tiposerviciomeca values(null,'Remolque','60.00')")



        bd.execSQL("create table tb_tiposervicioenfe" +
                "(" +
                "cod_tiposervicioenfe integer primary key autoincrement," +
                "nombre varchar(50)," +
                "precio double)")


        //Ingresar registro de tipo de servicio enfermeria
        bd.execSQL("insert into tb_tiposervicioenfe values(null,'Primeros auxilios','70.00')")
        bd.execSQL("insert into tb_tiposervicioenfe values(null,'Cuidado Adulto Mayor','100.00')")
        bd.execSQL("insert into tb_tiposervicioenfe values(null,'Inyectables','30.00')")


        //crear tabla
        bd.execSQL(
            "create table tb_servicio" +
                    "(" +

                    "cod_servicio integer primary key autoincrement," +
                    "nom varchar(50)," +
                    "foto varchar(255))")






        //ingresar 6 servicios
        bd.execSQL("insert into tb_servicio values(null,'Servicio Tecnico','s1')")
        bd.execSQL("insert into tb_servicio values(null,'Plomeria','s2')")
        bd.execSQL("insert into tb_servicio values(null,'Electricista','s3')")
        bd.execSQL("insert into tb_servicio values(null,'Limpieza del hogar','s4')")
        bd.execSQL("insert into tb_servicio values(null,'Mecanico','s5')")
        bd.execSQL("insert into tb_servicio values(null,'Enfermeria','s6')")


        bd.execSQL(
            "create table tb_trabajador" +
                    "(" +
                    "cod_trabajador integer primary key autoincrement," +
                    "cod_servicio int references tb_servicio," +
                    "nomb varchar(50)," +
                    "apell varchar(50)," +
                    "telefono varchar(12)," +
                    "edad int," +
                    "foto varchar(255))")

        //Ingresar registros a la tabla:

        /*
        *         bd.execSQL("insert into tb_Trabajador values(null, 'Juan', 'Vasques', '999111555','43','s1')")
        bd.execSQL("insert into tb_Trabajador values(null, 'Jose', 'Perez', '933558710','34','s2')")
        bd.execSQL("insert into tb_Trabajador values(null, 'Ricardo', 'Quispe', '935487629','25','s3')")
        bd.execSQL("insert into tb_Trabajador values(null, 'Daniel', 'Mamani', '957486157','59','s4')")
        bd.execSQL("insert into tb_Trabajador values(null, 'Jorge', 'Vasques', '925468739','48','s5')")
        bd.execSQL("insert into tb_Trabajador values(null, 'Brandon', 'Cortez', '958763541','38','s6')")
        * */



    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

}