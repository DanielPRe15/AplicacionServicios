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
import com.example.aplicacionservicios.controlador.ArregloServicioEnfermeria
import com.example.aplicacionservicios.controlador.ArregloServicioEnfermeriaTipo
import com.example.aplicacionservicios.controlador.ArregloServicioTecnicoTipo
import com.example.aplicacionservicios.entidad.ServicioEnfermeria
import com.google.android.material.textfield.TextInputEditText
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date

class ServicioEnfermeriaActivity : AppCompatActivity(),AdapterView.OnItemClickListener {

    private lateinit var txtEnferCliente: TextInputEditText
    private lateinit var txtEnferTelefono: TextInputEditText
    private lateinit var txtEnferFecha: TextInputEditText
    private lateinit var txtEnferDireccion: TextInputEditText
    private lateinit var txtEnferInformacion: TextInputEditText

    private lateinit var atvEnferServicio: AutoCompleteTextView

    private lateinit var btnEnferSiguiente: Button
    private lateinit var btnEnferCancelar: Button

    var posTipos=-1
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


        atvEnferServicio.setOnItemClickListener(this)


        cargarTipos()
    }

    fun siguiente() {
        var cliente = txtEnferCliente.text.toString()
        var telefono = txtEnferTelefono.text.toString()
        var fecha = txtEnferFecha.text.toString()
        var direccion = txtEnferDireccion.text.toString()
        var informacion = txtEnferInformacion.text.toString()

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
                var servicioEnfermeria = ServicioEnfermeria(
                    codigoServicioTec = 0,
                    codigoServi = codigoServicio,
                    codigoTipo = posTipos,
                    nombreCliente = cliente,
                    telefonoCliente = telefono,
                    fecha = date,
                    direccionCliente = direccion,
                    informacionAdicional = informacion
                )

                mostrarReporte(servicioEnfermeria)
            } else {
                Toast.makeText(this, "No se encontró el código para el servicio: $nombreServicio", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun mostrarReporte(servicioEnfermeria: ServicioEnfermeria) {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy")
        val fechaFormateada = dateFormat.format(servicioEnfermeria.fecha)

        val precioTipoServicio = if (posTipos != -1) {
            val precio = ArregloServicioTecnicoTipo().obtenerPrecioPorCodigo(posTipos)
            "Precio: $precio" // Mostrar el precio del tipo de servicio en el reporte
        } else {
            "Precio: No disponible"
        }

        val reporte = "Cliente: ${servicioEnfermeria.nombreCliente}\n" +
                "Teléfono: ${servicioEnfermeria.telefonoCliente}\n" +
                "Fecha: $fechaFormateada\n" +
                "Dirección: ${servicioEnfermeria.direccionCliente}\n" +
                "Información Adicional: ${servicioEnfermeria.informacionAdicional}\n" +
                "$precioTipoServicio"


        val builder = AlertDialog.Builder(this, R.style.CustomAlertDialogStyle)
        builder.setTitle("Reporte del Servicio Técnico")
        builder.setMessage(reporte)

        builder.setPositiveButton("Confirmar Pedido") { dialog, which ->
            // Aquí iría la lógica para confirmar el pedido
            confirmarPedido(servicioEnfermeria)
        }

        builder.setNegativeButton("Cancelar") { dialog, which ->
            // Aquí podrías realizar alguna acción si se cancela el reporte
            Toast.makeText(this, "Reporte cancelado", Toast.LENGTH_SHORT).show()
        }

        val dialog = builder.create()
        dialog.show()
    }


    fun confirmarPedido(servicioEnfermeria: ServicioEnfermeria) {
        val arregloServicioEnfermeria = ArregloServicioEnfermeria()

        // Simular la adición del servicio técnico a la lista
        val resultado = arregloServicioEnfermeria.adicionar(servicioEnfermeria)

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
        var data= ArregloServicioEnfermeriaTipo().listadoTipos()
        //crear un adaptador con los valores de data
        var adaptador= ArrayAdapter(this,android.R.layout.simple_list_item_1,data)
        //enviar el objeto "adaptador" al atributo atvDistrito
        atvEnferServicio.setAdapter(adaptador)
    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        posTipos=p2+1
    }

}