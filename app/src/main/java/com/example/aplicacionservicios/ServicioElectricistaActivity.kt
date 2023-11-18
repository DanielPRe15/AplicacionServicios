package com.example.aplicacionservicios

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.aplicacionservicios.controlador.ArregloServicio
import com.example.aplicacionservicios.controlador.ArregloServicioElectrico
import com.example.aplicacionservicios.controlador.ArregloServicioElectricoTipo
import com.example.aplicacionservicios.entidad.ServicioElectrico
import com.google.android.material.textfield.TextInputEditText
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date

class ServicioElectricistaActivity : AppCompatActivity(),AdapterView.OnItemClickListener {

    private lateinit var txtElecCliente: TextInputEditText
    private lateinit var txtElecTelefono: TextInputEditText
    private lateinit var txtElecFecha: TextInputEditText
    private lateinit var txtElecDireccion: TextInputEditText
    private lateinit var txtElecInformacion: TextInputEditText
    private lateinit var atvElecServicio: AutoCompleteTextView

    private lateinit var btnElecSiguiente: Button
    private lateinit var btnElecCancelar: Button

    var posTipos=-1

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

        atvElecServicio.setOnItemClickListener(this)


        cargarTipos()
    }
    fun siguiente() {
        var cliente = txtElecCliente.text.toString()
        var telefono = txtElecTelefono.text.toString()
        var fecha = txtElecFecha.text.toString()
        var direccion = txtElecDireccion.text.toString()
        var informacion = txtElecInformacion.text.toString()

        var dateFormat = SimpleDateFormat("dd/MM/yyyy")
        var date: Date? = null
        try {
            date = dateFormat.parse(fecha)
        } catch (e: ParseException) {
            e.printStackTrace()
            Toast.makeText(this, "Error en el formato de fecha", Toast.LENGTH_LONG).show()
        }

        if (date != null) {
            val nombreServicio = "Servicio Tecnico" // Reemplazar con el nombre real del servicio que estás registrando
            val codigoServicio = ArregloServicio().obtenerCodigoServicio(nombreServicio)

            if (codigoServicio != -1) {
                var servicioElectrico = ServicioElectrico(
                    codigoServicioTec = 0,
                    codigoServi = codigoServicio,
                    codigoTipo = posTipos,
                    nombreCliente = cliente,
                    telefonoCliente = telefono,
                    fecha = date,
                    direccionCliente = direccion,
                    informacionAdicional = informacion
                )

                mostrarReporte(servicioElectrico)
            } else {
                Toast.makeText(this, "No se encontró el código para el servicio: $nombreServicio", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun mostrarReporte(servicioElectrico: ServicioElectrico) {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy")
        val fechaFormateada = dateFormat.format(servicioElectrico.fecha)

        val precioTipoServicio = if (posTipos != -1) {
            val precio = ArregloServicioElectricoTipo().obtenerPrecioPorCodigo(posTipos)
            "Precio: $precio" // Mostrar el precio del tipo de servicio en el reporte
        } else {
            "Precio: No disponible"
        }

        val reporte = "Cliente: ${servicioElectrico.nombreCliente}\n" +
                "Teléfono: ${servicioElectrico.telefonoCliente}\n" +
                "Fecha: $fechaFormateada\n" +
                "Dirección: ${servicioElectrico.direccionCliente}\n" +
                "Información Adicional: ${servicioElectrico.informacionAdicional}\n" +
                "$precioTipoServicio"


        val builder = AlertDialog.Builder(this, R.style.CustomAlertDialogStyle)
        builder.setTitle("Reporte del Servicio Técnico")
        builder.setMessage(reporte)

        builder.setPositiveButton("Confirmar Pedido") { dialog, which ->
            // Aquí iría la lógica para confirmar el pedido
            confirmarPedido(servicioElectrico)
        }

        builder.setNegativeButton("Cancelar") { dialog, which ->
            // Aquí podrías realizar alguna acción si se cancela el reporte
            Toast.makeText(this, "Reporte cancelado", Toast.LENGTH_SHORT).show()
        }

        val dialog = builder.create()
        dialog.show()
    }


    fun confirmarPedido(servicioElectrico: ServicioElectrico) {
        val arregloServicioElectrico = ArregloServicioElectrico()

        // Simular la adición del servicio técnico a la lista
        val resultado = arregloServicioElectrico.adicionar(servicioElectrico)

        if (resultado > 0) {
            // Acciones posteriores a la confirmación exitosa del pedido
            Toast.makeText(this, "Pedido confirmado exitosamente", Toast.LENGTH_SHORT).show()

            // Aquí podrías redirigir a otra actividad, limpiar los campos, etc.
        } else {
            // Si falla la confirmación del pedido
            Toast.makeText(this, "Error al confirmar el pedido", Toast.LENGTH_SHORT).show()
        }
    }




    fun cancelar() {
        var intent = Intent(this, MenuPrincipalActivity::class.java)
        startActivity(intent)
    }

    fun cargarTipos(){
        //invocar al método listadoDistritos
        var data= ArregloServicioElectricoTipo().listadoTipos()
        //crear un adaptador con los valores de data
        var adaptador= ArrayAdapter(this,android.R.layout.simple_list_item_1,data)
        //enviar el objeto "adaptador" al atributo atvDistrito
        atvElecServicio.setAdapter(adaptador)
    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        posTipos=p2+1
    }

}