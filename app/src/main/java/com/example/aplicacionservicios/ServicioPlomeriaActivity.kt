package com.example.aplicacionservicios

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AutoCompleteTextView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class ServicioPlomeriaActivity : AppCompatActivity(), AdapterView.OnItemClickListener{

    private lateinit var txtPlomCliente : TextInputEditText
    private lateinit var txtPlomTelefono : TextInputEditText
    private lateinit var txtPlomFecha : TextInputEditText
    private lateinit var txtPlomDireccion : TextInputEditText
    private lateinit var txtPlomInformacion : TextInputEditText

    private lateinit var atvPlomServicio : AutoCompleteTextView

    private lateinit var btnPlomSiguiente : Button
    private lateinit var btnPlomCancelar : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.plomeria_main)

        txtPlomCliente = findViewById(R.id.txtPlomCliente)
        txtPlomTelefono = findViewById(R.id.txtPlomTelefono)
        txtPlomFecha = findViewById(R.id.txtPlomFecha)
        txtPlomDireccion = findViewById(R.id.txtPlomDireccion)
        txtPlomInformacion = findViewById(R.id.txtPlomInformacion)
        atvPlomServicio = findViewById(R.id.atvPlomServicio)
        btnPlomSiguiente = findViewById(R.id.btnPlomSiguiente)
        btnPlomCancelar = findViewById(R.id.btnPlomCancelar)
        btnPlomSiguiente.setOnClickListener{siguiente()}
        btnPlomCancelar.setOnClickListener{cancelar()}


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