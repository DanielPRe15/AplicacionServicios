package com.example.aplicacionservicios

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.aplicacionservicios.controlador.ArregloTrabajador
import com.example.aplicacionservicios.entidad.Trabajador
import com.google.android.material.textfield.TextInputEditText

class TrabajadorNuevoActivity: AppCompatActivity(), AdapterView.OnItemClickListener {
    private lateinit var  txtTrabNombreNew: TextInputEditText
    private lateinit var  txtTrabApellidoNew: TextInputEditText
    private lateinit var  txtTrabTelefonoNew: TextInputEditText
    private lateinit var  txtTrabEdadNew: TextInputEditText
    private lateinit var  txtTrabFotoNew: TextInputEditText
    private lateinit var  btnTrabGrabar: Button
    private lateinit var  btnTrabSalirNew: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.trabajador_nuevo)
        txtTrabNombreNew=findViewById(R.id.txtTrabNombreNew)
        txtTrabApellidoNew=findViewById(R.id.txtTrabApellidoNew)
        txtTrabTelefonoNew=findViewById(R.id.txtTrabTelefonoNew)
        txtTrabEdadNew=findViewById(R.id.txtTrabEdadNew)
        txtTrabFotoNew=findViewById(R.id.txtTrabFotoNew)
        btnTrabGrabar=findViewById(R.id.btnTrabGrabar)
        btnTrabGrabar=findViewById(R.id.btnTrabGrabar)
        btnTrabSalirNew=findViewById(R.id.btnTrabSalirNew)
        //
        btnTrabGrabar.setOnClickListener {grabar()}
        btnTrabSalirNew.setOnClickListener {salir()}
    }
    fun grabar(){
        //variables
        var nomb="";var apel="";var tele="";
        var edad=0;var foto=""
        //leer cajas
        nomb=txtTrabNombreNew.text.toString()
        apel=txtTrabApellidoNew.text.toString()
        tele=txtTrabTelefonoNew.text.toString()
        edad=txtTrabEdadNew.text.toString().toInt()
        foto=txtTrabFotoNew.text.toString()

        var trab=Trabajador(0,nomb,apel,tele,edad,foto)
        var estado=ArregloTrabajador().adicionar(trab)
        //validar estado
        if (estado>0)
            Toast.makeText(this,"Trabajador registrado", Toast.LENGTH_LONG).show()
        else
            Toast.makeText(this,"Error en el registro", Toast.LENGTH_LONG).show()

        salir()
    }
    fun salir(){
        var intent= Intent(this,TrabajadorMainActivity::class.java)
        startActivity(intent)
    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        TODO("Not yet implemented")
    }

}