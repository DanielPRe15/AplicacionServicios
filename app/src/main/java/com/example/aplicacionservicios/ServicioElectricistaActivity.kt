package com.example.aplicacionservicios

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AutoCompleteTextView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class ServicioElectricistaActivity : AppCompatActivity(),AdapterView.OnItemClickListener {

    private lateinit var txtElecCliente: TextInputEditText
    private lateinit var txtElecTelefono: TextInputEditText
    private lateinit var txtElecFecha: TextInputEditText
    private lateinit var txtElecDireccion: TextInputEditText
    private lateinit var txtElecInformacion: TextInputEditText
    private lateinit var atvElecServicio: AutoCompleteTextView

    private lateinit var btnElecSiguiente: Button
    private lateinit var btnElecCancelar: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.electricista_main)

        txtElecCliente = findViewById(R.id.txtElecCliente)
        txtElecTelefono = findViewById(R.id.txtElecTelefono)
        txtElecFecha = findViewById(R.id.txtElecFecha)
        txtElecDireccion = findViewById(R.id.txtElecDireccion)
        txtElecInformacion = findViewById(R.id.txtElecInformacion)
        atvElecServicio = findViewById(R.id.atvElecServicio)
        btnElecSiguiente = findViewById(R.id.btnElecSiguiente)
        btnElecCancelar = findViewById(R.id.btnElecCancelar)
        btnElecSiguiente.setOnClickListener { siguiente() }
        btnElecCancelar.setOnClickListener{ cancelar() }

    }
    fun siguiente()
    {
        // metodo para pasar de interfaz
    }

    fun cancelar()
    {
        var intent= Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        TODO("Not yet implemented")
    }

}