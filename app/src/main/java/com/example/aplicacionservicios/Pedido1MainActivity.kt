package com.example.aplicacionservicios

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aplicacionservicios.adaptador.Pedido1Adapter
import com.example.aplicacionservicios.adaptador.TrabajadorAdapter
import com.example.aplicacionservicios.controlador.ArregloServicioTecnico
import com.example.aplicacionservicios.controlador.ArregloTrabajador

class Pedido1MainActivity: AppCompatActivity() {
    private lateinit var rvPedido1: RecyclerView
    private lateinit var btnPed1Nuevo: Button
    private lateinit var btnPed1MainSalir: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pedido1_main)
        rvPedido1=findViewById(R.id.rvPedido1)
        btnPed1Nuevo=findViewById(R.id.btnPed1Nuevo)
        btnPed1MainSalir=findViewById(R.id.btnPed1MainSalir)
        btnPed1MainSalir.setOnClickListener {salir()}
        btnPed1Nuevo.setOnClickListener {nuevo()}

        //invocar al método listado
        var datos=ArregloServicioTecnico().listado()
        //crear objeto de la clase DocenteAdapter
        var adaptador= Pedido1Adapter(datos)
        //estilo tipo fila para visualizar datos en rvDocentes
        rvPedido1.layoutManager= LinearLayoutManager(this)
        //rvDocentes.layoutManager=GridLayoutManager(this,3)
        //mostrar el valor del objeto adaptador al atributo rvDocentes
        rvPedido1.adapter=adaptador
    }
    fun nuevo(){
        val intent= Intent(this,Pedido1NuevoActivity::class.java)
        startActivity(intent)
    }

    fun salir(){
        val intent= Intent(this,PrincipalAdminActivity::class.java)
        startActivity(intent)
    }
}