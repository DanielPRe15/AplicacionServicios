package com.example.aplicacionservicios

import android.app.DatePickerDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.aplicacionservicios.controlador.ArregloServicioTecnico
import com.example.aplicacionservicios.controlador.ArregloTrabajador
import com.example.aplicacionservicios.entidad.ServicioTecnico
import com.example.aplicacionservicios.entidad.Trabajador
import com.google.android.material.textfield.TextInputEditText
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.Calendar
import java.util.Date
import java.util.Locale


class Pedido1EditarActivity: AppCompatActivity(), AdapterView.OnItemClickListener  {
    private lateinit var  txtPed1Codigo: TextInputEditText
    private lateinit var  txtPed1CodigoServicio: TextInputEditText
    private lateinit var  txtPed1CodigoTipo: TextInputEditText
    private lateinit var  txtPed1Cliente: TextInputEditText
    private lateinit var  txtPed1TelefCliente: TextInputEditText
    private lateinit var  txtPed1Fecha: TextInputEditText
    private lateinit var  txtPed1Direccion: TextInputEditText
    private lateinit var  txtPed1Info: TextInputEditText
    private lateinit var  btnPed1Actualizar: Button
    private lateinit var  btnPed1Eliminar: Button
    private lateinit var  btnPed1Salir: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pedido1_editar)
        txtPed1Codigo=findViewById(R.id.txtPed1Codigo)
        txtPed1CodigoServicio=findViewById(R.id.txtPed1CodigoServicio)
        txtPed1CodigoTipo=findViewById(R.id.txtPed1CodigoTipo)
        txtPed1Cliente=findViewById(R.id.txtPed1Cliente)
        txtPed1TelefCliente=findViewById(R.id.txtPed1TelefCliente)
        txtPed1Fecha=findViewById(R.id.txtPed1Fecha)
        txtPed1Direccion=findViewById(R.id.txtPed1Direccion)
        txtPed1Info=findViewById(R.id.txtPed1Info)
        btnPed1Actualizar=findViewById(R.id.btnPed1Actualizar)
        btnPed1Eliminar=findViewById(R.id.btnPed1Eliminar)
        btnPed1Salir=findViewById(R.id.btnPed1Salir)

        btnPed1Actualizar.setOnClickListener {actualizar()}
        btnPed1Eliminar.setOnClickListener {eliminar()}
        btnPed1Salir.setOnClickListener {salir()}
        obtenerDatos()
    }
    fun actualizar(){
        //variables
        var cli="";var telef="";var fech="";var dire="";var info="";var cod1:Int;
        var cod2:Int ;var cod3:Int;
        //leer cajas
        cod1=txtPed1Codigo.text.toString().toInt()
        cod2=txtPed1CodigoServicio.text.toString().toInt()
        cod3=txtPed1CodigoTipo.text.toString().toInt()
        cli=txtPed1Cliente.text.toString()
        telef=txtPed1TelefCliente.text.toString()
        fech=txtPed1Fecha.text.toString()
        dire= txtPed1Direccion.text.toString()
        info=txtPed1Info.text.toString()


        var dateFormat = SimpleDateFormat("dd/MM/yyyy")
        var date: Date? = null


        try {
            date = dateFormat.parse(fech)
        } catch (e: ParseException) {
            e.printStackTrace()
            Toast.makeText(this, "Error en el formato de fecha", Toast.LENGTH_LONG).show()
        }


        var ped1=ServicioTecnico(cod1,cod2,cod3,cli,telef,date,dire,info)
        //invocar al método actualizar
        var estado=ArregloServicioTecnico().actualizar(ped1)
        //validar estado
        if (estado>0)
            Toast.makeText(this,"Pedido actualizado", Toast.LENGTH_LONG).show()
        else
            Toast.makeText(this,"Error en la actualización", Toast.LENGTH_LONG).show()
    }
    fun eliminar(){
        val dialogo1: AlertDialog.Builder = AlertDialog.Builder(this)
        dialogo1.setTitle("Sistema")
        dialogo1.setMessage("¿ Seguro de calcular?")
        dialogo1.setCancelable(false)
        dialogo1.setPositiveButton("Aceptar",
            DialogInterface.OnClickListener { dialogInterface: DialogInterface, i: Int ->
                var cod1:Int
                cod1=txtPed1Codigo.text.toString().toInt()
                var estado=ArregloServicioTecnico().eliminar(cod1)
                //validar estado
                if (estado>0)
                    Toast.makeText(this,"Pedido eliminado", Toast.LENGTH_LONG).show()
                else
                    Toast.makeText(this,"Error en la eliminación", Toast.LENGTH_LONG).show()
            })
        dialogo1.setNegativeButton("Cancelar",
            DialogInterface.OnClickListener { dialogo1, id -> })
        dialogo1.setIcon(android.R.drawable.ic_dialog_alert)
        dialogo1.show()
    }
    fun salir(){
        var intent= Intent(this,Pedido1MainActivity::class.java)
        startActivity(intent)
    }
    fun obtenerDatos(){
        var bundle=intent.getSerializableExtra("servicioTecnico") as ServicioTecnico
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss") // Formato de fecha deseado
        val fechaString = sdf.format(bundle.fecha)

        //mostrar en los controles el valor de bundle
        txtPed1Codigo.setText(bundle.codigoServicioTec.toString())
        txtPed1CodigoServicio.setText(bundle.codigoServi.toString())
        txtPed1CodigoTipo.setText(bundle.codigoTipo.toString())
        txtPed1Cliente.setText(bundle.nombreCliente)
        txtPed1TelefCliente.setText(bundle.telefonoCliente)
        txtPed1Fecha.setText(fechaString)
        txtPed1Direccion.setText(bundle.direccionCliente)
        txtPed1Info.setText(bundle.informacionAdicional)
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