package com.example.aplicacionservicios

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aplicacionservicios.adaptador.ServicioAdapter
import com.example.aplicacionservicios.controlador.ArregloServicio
import com.google.firebase.auth.FirebaseAuth

class MenuPrincipalActivity : AppCompatActivity() {

    private lateinit var rvServicio: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_principal)
        rvServicio=findViewById(R.id.rvServicio)



        var datos = ArregloServicio().listado()

        //crear objeto de la clase DocenteAdapter
        var adaptador = ServicioAdapter(datos)
        //estilo tipo fila para visualizar datos en rvDocentes
        rvServicio.layoutManager= LinearLayoutManager(this)
        //rvServicio.layoutManager=GridLayoutManager(this,3)
        //mostrar el valor del objeto adaptador al atributo rvDocentes
        rvServicio.adapter=adaptador
    }

    private fun abrirServicioTecnico(codigoServicio: Int) {
        val intent = Intent(this, ServicioTecnicoActivity::class.java)
        intent.putExtra("CODIGO_SERVICIO", codigoServicio)
        startActivity(intent)
    }

    fun cerrarSesion(view: View) {
        FirebaseAuth.getInstance().signOut()
        startActivity(Intent(this,MainActivity ::class.java))
    }

}
