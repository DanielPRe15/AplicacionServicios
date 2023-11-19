package com.example.aplicacionservicios

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ConfirmacionActivity : AppCompatActivity() {
    private lateinit var txtFechasRepo: TextView
    private lateinit var txtPrecio: TextView
    private lateinit var txtTipoServicioRepo : TextView


    private lateinit var btnAceptar:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmacion)
        txtFechasRepo=findViewById(R.id.txtFechasRepo)
        txtTipoServicioRepo=findViewById(R.id.txtTipoServicioRepo)

        txtPrecio=findViewById(R.id.txtPrecio)
        btnAceptar= findViewById(R.id.btnAceptar)
        btnAceptar.setOnClickListener {aceptar()}



        // Obtener la fecha actual
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val currentDate = dateFormat.format(calendar.time)

        // Mostrar la fecha en un TextView (o donde desees en tu reporte)
        txtFechasRepo.text = currentDate

        val precioServicio = intent.getStringExtra("precio")
        txtPrecio.text =  precioServicio


        val TipoServicio = intent.getStringExtra("nombreTipoServicio")
        txtTipoServicioRepo.text = TipoServicio

    }
    fun aceptar(){
        var intent = Intent(this, MenuPrincipalActivity::class.java)
        startActivity(intent)
    }
}