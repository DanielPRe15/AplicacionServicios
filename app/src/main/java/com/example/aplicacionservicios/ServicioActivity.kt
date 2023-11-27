package com.example.aplicacionservicios

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aplicacionservicios.adaptador.ServicioAdapter
import com.example.aplicacionservicios.controlador.ArregloServicio

class ServicioActivity : AppCompatActivity() {


    private lateinit var rvServicio2: RecyclerView
    private lateinit var btnServNuevo:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lista_servicio)
        rvServicio2=findViewById(R.id.rvServicio2)
        btnServNuevo=findViewById(R.id.btnServNuevo)

        //invocar al método listado
        var datos=ArregloServicio().listado()
        //crear objeto de la clase DocenteAdapter
        var adaptador= ServicioAdapter(datos)
        //estilo tipo fila para visualizar datos en rvDocentes
        rvServicio2.layoutManager= LinearLayoutManager(this)
        //rvDocentes.layoutManager=GridLayoutManager(this,3)
        //mostrar el valor del objeto adaptador al atributo rvDocentes
        rvServicio2.adapter=adaptador
    }



}