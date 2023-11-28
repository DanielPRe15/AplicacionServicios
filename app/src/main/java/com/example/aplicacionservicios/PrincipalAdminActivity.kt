package com.example.aplicacionservicios

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.aplicacionservicios.controlador.ArregloServicioTecnico
import com.example.aplicacionservicios.controlador.ArregloTrabajador
import com.google.firebase.auth.FirebaseAuth

class PrincipalAdminActivity : AppCompatActivity() {

    //private lateinit var  buttonEliminar: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal_admin)
        //buttonEliminar=findViewById(R.id.buttonEliminar)
        //buttonEliminar.setOnClickListener {eliminar()}
    }

    fun cerrarSesion(view: View) {
        FirebaseAuth.getInstance().signOut()
        startActivity(Intent(this,MainActivity ::class.java))
    }
/*
    fun cliente()
    {
        //Aqui pones la clase a la cual el boton va a dirigir
        //startActivity(Intent(this,MainActivity ::class.java)) ** con eso **
    }*/
    fun trabajadores(view: View)
    {
        startActivity(Intent(this,TrabajadorMainActivity ::class.java))
    }
    fun pedidos(view: View){
        startActivity(Intent(this,Pedido1MainActivity ::class.java))

    }
    fun servicios(view: View){
        startActivity(Intent(this,ServicioBaseMainActivity ::class.java))

    }
/*
    fun eliminar(){
                var cod1:Int
                cod1=4
                var estado=ArregloServicioTecnico().eliminar(cod1)
                //validar estado
                if (estado>0)
                    Toast.makeText(this,"Pedido eliminado", Toast.LENGTH_LONG).show()
                else
                    Toast.makeText(this,"Error en la eliminación", Toast.LENGTH_LONG).show()

    }
*/


}