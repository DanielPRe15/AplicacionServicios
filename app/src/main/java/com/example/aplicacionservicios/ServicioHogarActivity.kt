package com.example.aplicacionservicios

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AutoCompleteTextView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class ServicioHogarActivity : AppCompatActivity(),AdapterView.OnItemClickListener {

    private lateinit var txtHogarCliente: TextInputEditText
    private lateinit var txtHogarTelefono: TextInputEditText
    private lateinit var txtHogarFecha: TextInputEditText
    private lateinit var txtHogarDireccion: TextInputEditText
    private lateinit var txtHogarInformacion: TextInputEditText

    private lateinit var atvHogarServicio: AutoCompleteTextView

    private lateinit var btnHogarSiguiente: Button
    private lateinit var btnHogarCancelar: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.limpieza_hogar_main)

        txtHogarCliente = findViewById(R.id.txtHogarCliente)
        txtHogarTelefono = findViewById(R.id.txtHogarTelefono)
        txtHogarFecha = findViewById(R.id.txtHogarFecha)
        txtHogarDireccion = findViewById(R.id.txtHogarDireccion)
        txtHogarInformacion = findViewById(R.id.txtHogarInformacion)
        atvHogarServicio = findViewById(R.id.atvHogarServicio)
        btnHogarSiguiente = findViewById(R.id.btnHogarSiguiente)
        btnHogarCancelar = findViewById(R.id.btnHogarCancelar)
        btnHogarSiguiente.setOnClickListener { siguiente() }
        btnHogarCancelar.setOnClickListener { cancelar() }

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