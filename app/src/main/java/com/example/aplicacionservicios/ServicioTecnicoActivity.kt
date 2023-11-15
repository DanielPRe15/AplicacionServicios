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
import com.example.aplicacionservicios.controlador.ArregloServicio
import com.example.aplicacionservicios.controlador.ArregloServicioTecnico
import com.example.aplicacionservicios.controlador.ArregloServicioTecnicoTipo
import com.example.aplicacionservicios.entidad.ServicioTecnico
import com.google.android.material.textfield.TextInputEditText
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date

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


        //cargar sexo

        //adaptador
        val adaptador = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1
        )
        //mostrar adaptador
        atvTecnServicio.setAdapter(adaptador)
        //
    cargarTipos()

    }

    fun siguiente() {
        val cliente = txtTecnCliente.text.toString()
        val telefono = txtTecnTelefono.text.toString()
        val fecha = txtTecnFecha.text.toString() // Obtener la fecha como String directamente
        val direccion = txtTecnDireccion.text.toString()
        val informacion = txtTecnInformacion.text.toString()


        // Intentar convertir la fecha a un objeto Date
        val dateFormat = SimpleDateFormat("dd/MM/yyyy") // Formato esperado de la fecha
        var date: Date? = null
        try {
            date = dateFormat.parse(fecha)
        } catch (e: ParseException) {
            e.printStackTrace()
            Toast.makeText(this, "Error en el formato de fecha", Toast.LENGTH_LONG).show()
        }

        if (date != null) {
            val servicioTecnico = ServicioTecnico(
                codigoServicioTec = 0, // Código del servicio técnico
                codigoServi = 0, // Código del servicio
                codigoTipo = 0, // Código del tipo de servicio
                nombreCliente = cliente,
                telefonoCliente = telefono,
                fecha = date, // Utilizar el objeto Date convertido
                direccionCliente = direccion,
                informacionAdicional = informacion
            )

            val arregloServicioTecnico = ArregloServicioTecnico()
            val resultado = arregloServicioTecnico.adicionar(servicioTecnico)

            if (resultado > 0) {
                Toast.makeText(this, "Servicio técnico guardado exitosamente", Toast.LENGTH_SHORT).show()
                // Realizar acciones adicionales si la grabación fue exitosa
            } else {
                Toast.makeText(this, "Error al guardar el servicio técnico", Toast.LENGTH_SHORT).show()
            }
        }
    }




    fun cancelar() {
            var intent = Intent(this, MenuPrincipalActivity::class.java)
            startActivity(intent)
        }

    fun cargarTipos(){
        //invocar al método listadoDistritos
        var data=ArregloServicioTecnicoTipo().listadoTipos()
        //crear un adaptador con los valores de data
        var adaptador=ArrayAdapter(this,android.R.layout.simple_list_item_1,data)
        //enviar el objeto "adaptador" al atributo atvDistrito
        atvTecnServicio.setAdapter(adaptador)
    }
        override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

        }
    }