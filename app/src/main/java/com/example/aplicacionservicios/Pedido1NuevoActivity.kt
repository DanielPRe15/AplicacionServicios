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
import com.example.aplicacionservicios.controlador.ArregloServicioTecnico
import com.example.aplicacionservicios.controlador.ArregloTrabajador
import com.example.aplicacionservicios.entidad.ServicioTecnico
import com.example.aplicacionservicios.entidad.Trabajador
import com.google.android.material.textfield.TextInputEditText
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

class Pedido1NuevoActivity: AppCompatActivity(), AdapterView.OnItemClickListener {
    private lateinit var  txtPed1CodigoServicioNew: TextInputEditText
    private lateinit var  txtPed1CodigoTipoNew: TextInputEditText
    private lateinit var  txtPed1ClienteNew: TextInputEditText
    private lateinit var  txtPed1TelefClienteNew: TextInputEditText
    private lateinit var  txtPed1FechaNew: TextInputEditText
    private lateinit var  txtPed1DireccionNew: TextInputEditText
    private lateinit var  txtPed1InfoNew: TextInputEditText
    private lateinit var  btnPed1Grabar: Button
    private lateinit var  btnPed1SalirNew: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pedido1_nuevo)
        txtPed1CodigoServicioNew=findViewById(R.id.txtPed1CodigoServicioNew)
        txtPed1CodigoTipoNew=findViewById(R.id.txtPed1CodigoTipoNew)
        txtPed1ClienteNew=findViewById(R.id.txtPed1ClienteNew)
        txtPed1TelefClienteNew=findViewById(R.id.txtPed1TelefClienteNew)
        txtPed1FechaNew=findViewById(R.id.txtPed1FechaNew)
        txtPed1DireccionNew=findViewById(R.id.txtPed1DireccionNew)
        txtPed1InfoNew=findViewById(R.id.txtPed1InfoNew)
        btnPed1Grabar=findViewById(R.id.btnPed1Grabar)
        btnPed1SalirNew=findViewById(R.id.btnPed1SalirNew)
        //
        btnPed1Grabar.setOnClickListener {grabar()}
        btnPed1SalirNew.setOnClickListener {salir()}
    }
    fun grabar(){
        var cod2=0; var cod3=0;
        var cli="";var tele="";var fecha="";
        var direc=""; var info="";

        cod2=txtPed1CodigoServicioNew.text.toString().toInt()
        cod3=txtPed1CodigoTipoNew.text.toString().toInt()
        cli=txtPed1ClienteNew.text.toString()
        tele=txtPed1TelefClienteNew.text.toString()
        fecha=txtPed1FechaNew.text.toString()
        direc=txtPed1DireccionNew.text.toString()
        info=txtPed1InfoNew.text.toString()

        var dateFormat = SimpleDateFormat("dd/MM/yyyy")
        var date: Date? = null

        try {
            date = dateFormat.parse(fecha)
        } catch (e: ParseException) {
            e.printStackTrace()
            Toast.makeText(this, "Error en el formato de fecha", Toast.LENGTH_LONG).show()
        }


        var ped1=ServicioTecnico(0,cod2,cod3,cli,tele,date,direc,info)
        var estado=ArregloServicioTecnico().adicionar(ped1)
        //validar estado
        if (estado>0)
            Toast.makeText(this,"Pedido registrado", Toast.LENGTH_LONG).show()
        else
            Toast.makeText(this,"Error en el registro", Toast.LENGTH_LONG).show()

        salir()
    }
    fun salir(){
        var intent= Intent(this,Pedido1MainActivity::class.java)
        startActivity(intent)
    }

    fun showDatePickerDialog(view: View) {
        val editText = view as EditText

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, yearSelected, monthOfYear, dayOfMonth ->
                val selectedDate = "$dayOfMonth/${monthOfYear + 1}/$yearSelected"
                editText.setText(selectedDate)
            },
            year,
            month,
            day
        )

        datePickerDialog.show()
    }




    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        TODO("Not yet implemented")
    }

}