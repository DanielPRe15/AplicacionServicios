package com.example.aplicacionservicios

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aplicacionservicios.adaptador.Pedido1Adapter
import com.example.aplicacionservicios.adaptador.ServicioBaseAdapter
import com.example.aplicacionservicios.adaptador.TrabajadorAdapter
import com.example.aplicacionservicios.controlador.ArregloServicio
import com.example.aplicacionservicios.controlador.ArregloServicioTecnico
import com.example.aplicacionservicios.controlador.ArregloTrabajador

class ServicioBaseMainActivity: AppCompatActivity() {
    private lateinit var rvServBase: RecyclerView
    private lateinit var btnServBaseNuevo: Button
    private lateinit var btnServBaseMainSalir: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.servicio_main)
        rvServBase=findViewById(R.id.rvServBase)
        btnServBaseNuevo=findViewById(R.id.btnServBaseNuevo)
        btnServBaseMainSalir=findViewById(R.id.btnServBaseMainSalir)
        btnServBaseMainSalir.setOnClickListener {salir()}
        btnServBaseNuevo.setOnClickListener {nuevo()}

        //invocar al método listado
        var datos=ArregloServicio().listado()
        //crear objeto de la clase DocenteAdapter
        var adaptador= ServicioBaseAdapter(datos)
        //estilo tipo fila para visualizar datos en rvDocentes
        rvServBase.layoutManager= LinearLayoutManager(this)
        //rvDocentes.layoutManager=GridLayoutManager(this,3)
        //mostrar el valor del objeto adaptador al atributo rvDocentes
        rvServBase.adapter=adaptador
    }
    fun nuevo(){
        val intent= Intent(this,ServicioBaseNuevoActivity::class.java)
        startActivity(intent)
    }

    fun salir(){
        val intent= Intent(this,PrincipalAdminActivity::class.java)
        startActivity(intent)
    }
}