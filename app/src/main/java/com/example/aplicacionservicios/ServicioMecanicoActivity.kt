package com.example.aplicacionservicios

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AutoCompleteTextView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class ServicioMecanicoActivity : AppCompatActivity(),AdapterView.OnItemClickListener{

    private lateinit var txtCliente : TextInputEditText
    private lateinit var txtTelefono : TextInputEditText
    private lateinit var txtFecha : TextInputEditText
    private lateinit var txtDireccion : TextInputEditText
    private lateinit var txtInformacion : TextInputEditText

    private lateinit var atvServicio : AutoCompleteTextView

    private lateinit var btnSiguiente : Button
    private lateinit var btnCancelar : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mecanico_main)

        txtCliente = findViewById(R.id.txtCliente)
         txtTelefono = findViewById(R.id.txtTelefono)
        txtFecha = findViewById(R.id.txtFecha)
        txtDireccion = findViewById(R.id.txtDireccion)
        txtInformacion = findViewById(R.id.txtInformacion)
        atvServicio = findViewById(R.id.atvServicio)
        btnSiguiente = findViewById(R.id.btnSiguiente)
        btnCancelar = findViewById(R.id.btnCancelar)
        btnSiguiente.setOnClickListener{siguiente()}
        btnCancelar.setOnClickListener{cancelar()}




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