package com.example.aplicacionservicios

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AutoCompleteTextView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class ServicioTecnicoActivity : AppCompatActivity(),AdapterView.OnItemClickListener {

    private lateinit var txtTecnCliente: TextInputEditText
    private lateinit var txtTecnTelefono: TextInputEditText
    private lateinit var txtTecnFecha: TextInputEditText
    private lateinit var txtTecnDireccion: TextInputEditText
    private lateinit var txtTecnInformacion: TextInputEditText

    private lateinit var atvTecnServicio: AutoCompleteTextView

    private lateinit var btnTecnSiguiente: Button
    private lateinit var btnTecnCancelar: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.servicio_tecnico_main)

        txtTecnCliente = findViewById(R.id.txtTecnCliente)
        txtTecnTelefono = findViewById(R.id.txtTecnTelefono)
        txtTecnFecha = findViewById(R.id.txtTecnFecha)
        txtTecnDireccion = findViewById(R.id.txtTecnDireccion)
        txtTecnInformacion = findViewById(R.id.txtTecnInformacion)
        atvTecnServicio = findViewById(R.id.atvTecnServicio)
        btnTecnSiguiente = findViewById(R.id.btnTecnSiguiente)
        btnTecnCancelar = findViewById(R.id.btnTecnCancelar)
        btnTecnSiguiente.setOnClickListener { siguiente() }
        btnTecnCancelar.setOnClickListener { cancelar() }

    }

    fun siguiente() {
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