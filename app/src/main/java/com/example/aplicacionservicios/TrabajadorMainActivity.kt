package com.example.aplicacionservicios

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aplicacionservicios.adaptador.TrabajadorAdapter
import com.example.aplicacionservicios.controlador.ArregloTrabajador

class TrabajadorMainActivity: AppCompatActivity() {
    private lateinit var rvTrabajador: RecyclerView
    private lateinit var btnTrabNuevo: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.trabajador_main)
        rvTrabajador=findViewById(R.id.rvTrabajador)
        btnTrabNuevo=findViewById(R.id.btnTrabNuevo)
        btnTrabNuevo.setOnClickListener {nuevo()}

        //invocar al método listado
        var datos=ArregloTrabajador().listado()
        //crear objeto de la clase DocenteAdapter
        var adaptador=TrabajadorAdapter(datos)
        //estilo tipo fila para visualizar datos en rvDocentes
        rvTrabajador.layoutManager= LinearLayoutManager(this)
        //rvDocentes.layoutManager=GridLayoutManager(this,3)
        //mostrar el valor del objeto adaptador al atributo rvDocentes
        rvTrabajador.adapter=adaptador
    }
    fun nuevo(){
        val intent= Intent(this,TrabajadorNuevoActivity::class.java)
        startActivity(intent)
    }

}