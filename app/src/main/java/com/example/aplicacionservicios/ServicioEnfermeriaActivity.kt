package com.example.aplicacionservicios

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AutoCompleteTextView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class ServicioEnfermeriaActivity : AppCompatActivity(),AdapterView.OnItemClickListener {

    private lateinit var txtEnferCliente: TextInputEditText
    private lateinit var txtEnferTelefono: TextInputEditText
    private lateinit var txtEnferFecha: TextInputEditText
    private lateinit var txtEnferDireccion: TextInputEditText
    private lateinit var txtEnferInformacion: TextInputEditText

    private lateinit var atvEnferServicio: AutoCompleteTextView

    private lateinit var btnEnferSiguiente: Button
    private lateinit var btnEnferCancelar: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.enfermeria_main)

        txtEnferCliente = findViewById(R.id.txtEnferCliente)
        txtEnferTelefono = findViewById(R.id.txtEnferTelefono)
        txtEnferFecha = findViewById(R.id.txtEnferFecha)
        txtEnferDireccion = findViewById(R.id.txtEnferDireccion)
        txtEnferInformacion = findViewById(R.id.txtEnferInformacion)
        atvEnferServicio = findViewById(R.id.atvEnferServicio)
        btnEnferSiguiente = findViewById(R.id.btnEnferSiguiente)
        btnEnferCancelar = findViewById(R.id.btnEnferCancelar)
        btnEnferSiguiente.setOnClickListener { siguiente() }
        btnEnferCancelar.setOnClickListener { cancelar() }

    }

    fun siguiente()
    {
        // metodo para pasar de interfaz
    }

    fun cancelar()
    {
        var intent= Intent(this,MenuPrincipalActivity::class.java)
        startActivity(intent)
    }
    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        TODO("Not yet implemented")
    }


}