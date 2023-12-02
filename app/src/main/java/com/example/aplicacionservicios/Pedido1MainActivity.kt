package com.example.aplicacionservicios

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aplicacionservicios.adaptador.Pedido1Adapter
import com.example.aplicacionservicios.adaptador.Pedido2Adapter
import com.example.aplicacionservicios.adaptador.Pedido3Adapter
import com.example.aplicacionservicios.adaptador.Pedido4Adapter
import com.example.aplicacionservicios.adaptador.Pedido5Adapter
import com.example.aplicacionservicios.adaptador.Pedido6Adapter
import com.example.aplicacionservicios.controlador.ArregloServicioElectrico
import com.example.aplicacionservicios.controlador.ArregloServicioEnfermeria
import com.example.aplicacionservicios.controlador.ArregloServicioLimpieza
import com.example.aplicacionservicios.controlador.ArregloServicioMecanico
import com.example.aplicacionservicios.controlador.ArregloServicioPlomeria
import com.example.aplicacionservicios.controlador.ArregloServicioTecnico

class Pedido1MainActivity: AppCompatActivity() {
    private lateinit var rvPedido1: RecyclerView
    private lateinit var rvPedido2: RecyclerView
    private lateinit var rvPedido3: RecyclerView
    private lateinit var rvPedido4: RecyclerView
    private lateinit var rvPedido5: RecyclerView
    private lateinit var rvPedido6: RecyclerView
    //private lateinit var btnPed1Nuevo: Button
    private lateinit var btnPed1MainSalir: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pedido1_main)
        rvPedido1=findViewById(R.id.rvPedido1)
        rvPedido2=findViewById(R.id.rvPedido2)
        rvPedido3=findViewById(R.id.rvPedido3)
        rvPedido4=findViewById(R.id.rvPedido4)
        rvPedido5=findViewById(R.id.rvPedido5)
        rvPedido6=findViewById(R.id.rvPedido6)
        //btnPed1Nuevo=findViewById(R.id.btnPed1Nuevo)
        btnPed1MainSalir=findViewById(R.id.btnPed1MainSalir)
        btnPed1MainSalir.setOnClickListener {salir()}
        //btnPed1Nuevo.setOnClickListener {nuevo()}

        //invocar al método listado
        var datos1=ArregloServicioTecnico().listado()
        var datos2=ArregloServicioPlomeria().listado()
        var datos3=ArregloServicioMecanico().listado()
        var datos4=ArregloServicioLimpieza().listado()
        var datos5=ArregloServicioEnfermeria().listado()
        var datos6= ArregloServicioElectrico().listado()
        //crear objeto de la clase DocenteAdapter
        var adaptador1= Pedido1Adapter(datos1)
        var adaptador2= Pedido2Adapter(datos2)
        var adaptador3= Pedido3Adapter(datos3)
        var adaptador4= Pedido4Adapter(datos4)
        var adaptador5= Pedido5Adapter(datos5)
        var adaptador6= Pedido6Adapter(datos6)
        //estilo tipo fila para visualizar datos en rvDocentes
        rvPedido1.layoutManager= LinearLayoutManager(this)
        rvPedido2.layoutManager= LinearLayoutManager(this)
        rvPedido3.layoutManager= LinearLayoutManager(this)
        rvPedido4.layoutManager= LinearLayoutManager(this)
        rvPedido5.layoutManager= LinearLayoutManager(this)
        rvPedido6.layoutManager= LinearLayoutManager(this)
        //rvDocentes.layoutManager=GridLayoutManager(this,3)
        //mostrar el valor del objeto adaptador al atributo rvDocentes
        rvPedido1.adapter=adaptador1
        rvPedido2.adapter=adaptador2
        rvPedido3.adapter=adaptador3
        rvPedido4.adapter=adaptador4
        rvPedido5.adapter=adaptador5
        rvPedido6.adapter=adaptador6
    }

    /*
    fun nuevo(){
         val intent= Intent(this,Pedido1NuevoActivity::class.java)
         startActivity(intent)
     }
 */
    fun salir(){
        val intent= Intent(this,PrincipalAdminActivity::class.java)
        startActivity(intent)
    }
}