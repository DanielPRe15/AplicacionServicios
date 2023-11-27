package com.example.aplicacionservicios

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.aplicacionservicios.controlador.ArregloServicio
import com.example.aplicacionservicios.controlador.ArregloServicioTecnico
import com.example.aplicacionservicios.controlador.ArregloTrabajador
import com.example.aplicacionservicios.entidad.Servicio
import com.example.aplicacionservicios.entidad.ServicioTecnico
import com.example.aplicacionservicios.entidad.Trabajador
import com.google.android.material.textfield.TextInputEditText
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

class ServicioBaseNuevoActivity: AppCompatActivity(), AdapterView.OnItemClickListener {
    private lateinit var  txtServBaseNombreNew: TextInputEditText
    private lateinit var  txtServBaseCodigoTrabajadorNew: TextInputEditText
    private lateinit var  txtServBaseFotoNew: TextInputEditText
    private lateinit var  btnServBaseGrabar: Button
    private lateinit var  btnServBaseSalirNew: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.servicio_nuevo)
        txtServBaseNombreNew=findViewById(R.id.txtServBaseNombreNew)
        txtServBaseCodigoTrabajadorNew=findViewById(R.id.txtServBaseCodigoTrabajadorNew)
        txtServBaseFotoNew=findViewById(R.id.txtServBaseFotoNew)
        btnServBaseGrabar=findViewById(R.id.btnServBaseGrabar)
        btnServBaseSalirNew=findViewById(R.id.btnServBaseSalirNew)
        //
        btnServBaseGrabar.setOnClickListener {grabar()}
        btnServBaseSalirNew.setOnClickListener {salir()}
    }
    fun grabar(){
        var nomb="";var foto="";var trab:Int

        nomb=txtServBaseNombreNew.text.toString()
        trab=txtServBaseCodigoTrabajadorNew.text.toString().toInt()
        foto=txtServBaseFotoNew.text.toString()

        var serv=Servicio(0,nomb,trab,foto)
        var estado=ArregloServicio().adicionar(serv)
        //validar estado
        if (estado>0)
            Toast.makeText(this,"Servicio registrado", Toast.LENGTH_LONG).show()
        else
            Toast.makeText(this,"Error en el registro", Toast.LENGTH_LONG).show()

        salir()
    }
    fun salir(){
        var intent= Intent(this,ServicioBaseMainActivity::class.java)
        startActivity(intent)
    }


    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        TODO("Not yet implemented")
    }

}