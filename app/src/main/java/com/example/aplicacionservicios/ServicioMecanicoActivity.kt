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
import com.example.aplicacionservicios.controlador.ArregloServicioMecanico
import com.example.aplicacionservicios.controlador.ArregloServicioMecanicoTipo
import com.example.aplicacionservicios.entidad.ServicioMecanico
import com.google.android.material.textfield.TextInputEditText
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date

class ServicioMecanicoActivity : AppCompatActivity(),AdapterView.OnItemClickListener{

    private lateinit var txtCliente : TextInputEditText
    private lateinit var txtTelefono : TextInputEditText
    private lateinit var txtFecha : TextInputEditText
    private lateinit var txtDireccion : TextInputEditText
    private lateinit var txtInformacion : TextInputEditText

    private lateinit var atvServicio : AutoCompleteTextView

    private lateinit var btnSiguiente : Button
    private lateinit var btnCancelar : Button

    var posTipos=-1
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


        atvServicio.setOnItemClickListener(this)


        cargarTipos()

    }

    fun siguiente() {
        var cliente = txtCliente.text.toString()
        var telefono = txtTelefono.text.toString()
        var fecha = txtFecha.text.toString()
        var direccion = txtDireccion.text.toString()
        var informacion = txtInformacion.text.toString()

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
                var servicioMecanico = ServicioMecanico(
                    codigoServicioTec = 0,
                    codigoServi = codigoServicio,
                    codigoTipo = posTipos,
                    nombreCliente = cliente,
                    telefonoCliente = telefono,
                    fecha = date,
                    direccionCliente = direccion,
                    informacionAdicional = informacion
                )

                mostrarReporte(servicioMecanico)
            } else {
                Toast.makeText(this, "No se encontró el código para el servicio: $nombreServicio", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun mostrarReporte(servicioMecanico: ServicioMecanico) {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy")
        val fechaFormateada = dateFormat.format(servicioMecanico.fecha)

        val precioTipoServicio = if (posTipos != -1) {
            val precio = ArregloServicioMecanicoTipo().obtenerPrecioPorCodigo(posTipos)
            "Precio: $precio" // Mostrar el precio del tipo de servicio en el reporte
        } else {
            "Precio: No disponible"
        }

        val reporte = "Cliente: ${servicioMecanico.nombreCliente}\n" +
                "Teléfono: ${servicioMecanico.telefonoCliente}\n" +
                "Fecha: $fechaFormateada\n" +
                "Dirección: ${servicioMecanico.direccionCliente}\n" +
                "Información Adicional: ${servicioMecanico.informacionAdicional}\n" +
                "$precioTipoServicio"


        val builder = AlertDialog.Builder(this, R.style.CustomAlertDialogStyle)
        builder.setTitle("Reporte del Servicio Técnico")
        builder.setMessage(reporte)

        builder.setPositiveButton("Confirmar Pedido") { dialog, which ->
            // Aquí iría la lógica para confirmar el pedido
            confirmarPedido(servicioMecanico)
        }

        builder.setNegativeButton("Cancelar") { dialog, which ->
            // Aquí podrías realizar alguna acción si se cancela el reporte
            Toast.makeText(this, "Reporte cancelado", Toast.LENGTH_SHORT).show()
        }

        val dialog = builder.create()
        dialog.show()
    }


    fun confirmarPedido(servicioMecanico: ServicioMecanico) {
        val arregloServicioMecanico = ArregloServicioMecanico()

        // Simular la adición del servicio técnico a la lista
        val resultado = arregloServicioMecanico.adicionar(servicioMecanico)

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
        var data= ArregloServicioMecanicoTipo().listadoTipos()
        //crear un adaptador con los valores de data
        var adaptador= ArrayAdapter(this,android.R.layout.simple_list_item_1,data)
        //enviar el objeto "adaptador" al atributo atvDistrito
        atvServicio.setAdapter(adaptador)
    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        posTipos=p2+1
    }

}