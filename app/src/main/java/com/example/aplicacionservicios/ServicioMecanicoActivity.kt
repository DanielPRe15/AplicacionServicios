package com.example.aplicacionservicios

import android.app.DatePickerDialog
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
import com.example.aplicacionservicios.controlador.ArregloServicio
import com.example.aplicacionservicios.controlador.ArregloServicioMecanico
import com.example.aplicacionservicios.controlador.ArregloServicioMecanicoTipo
import com.example.aplicacionservicios.controlador.ArregloServicioTecnicoTipo
import com.example.aplicacionservicios.entidad.ServicioMecanico
import com.google.android.material.textfield.TextInputEditText
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
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

    private lateinit var data: List<String>

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


        btnSiguiente.setOnClickListener {
            val phoneNumber = txtTelefono.text.toString()
            if (!validarTelefono(phoneNumber)) {

                txtTelefono.error = "Ingrese un número de teléfono válido"


            } else {

                siguiente()

            }

            btnSiguiente.setOnClickListener {
                val cliente = txtCliente.text.toString().trim()
                val telefono = txtTelefono.text.toString().trim()
                val fecha = txtFecha.text.toString().trim()
                val direccion = txtDireccion.text.toString().trim()
                val informacion = txtInformacion.text.toString().trim()

                if (cliente.isEmpty() || telefono.isEmpty() || fecha.isEmpty() || direccion.isEmpty() || informacion.isEmpty()) {
                    Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
                } else {
                    val phoneNumber = txtTelefono.text.toString()
                    if (!validarTelefono(phoneNumber)) {

                        txtTelefono.error = "Ingrese un número de teléfono válido"
                    } else {

                        siguiente()
                    }
                }
            }


        }



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
            val nombreServicio = "Servicio Tecnico"
            val codigoServicio = ArregloServicio().obtenerCodigoServicio(nombreServicio)

            if (codigoServicio != -1) {
                val precioTipoServicio = if (posTipos != -1) {
                    val precio = ArregloServicioTecnicoTipo().obtenerPrecioPorCodigo(posTipos)
                    precio.toString()
                } else {
                    "No disponible"
                }
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
        val nombreTipoServicio = data[posTipos]

        val precioTipoServicio = if (posTipos != -1) {
            val precio = ArregloServicioMecanicoTipo().obtenerPrecioPorCodigo(posTipos)
            "Precio: $precio"
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
        builder.setTitle("Resumen del Pedido")
        builder.setMessage(reporte)

        builder.setPositiveButton("Confirmar Pedido") { dialog, which ->

            confirmarPedido(servicioMecanico, precioTipoServicio, nombreTipoServicio)
        }

        builder.setNegativeButton("Cancelar") { dialog, which ->

            Toast.makeText(this, "Reporte cancelado", Toast.LENGTH_SHORT).show()
        }

        val dialog = builder.create()
        dialog.show()
    }


    fun confirmarPedido(servicioMecanico: ServicioMecanico,precioServicio: String, nombreTipoServicio: String) {
        val arregloServicioMecanico = ArregloServicioMecanico()


        val resultado = arregloServicioMecanico.adicionar(servicioMecanico)

        if (resultado > 0) {

            Toast.makeText(this, "Pedido confirmado exitosamente", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, ConfirmacionActivity::class.java)
            intent.putExtra("precio", precioServicio)
            intent.putExtra("nombreTipoServicio", nombreTipoServicio)
            startActivity(intent)



        } else {

            Toast.makeText(this, "Error al confirmar el pedido", Toast.LENGTH_SHORT).show()
        }
    }




    fun cancelar() {
        var intent = Intent(this, MenuPrincipalActivity::class.java)
        startActivity(intent)
    }

    fun cargarTipos(){

        data= ArregloServicioMecanicoTipo().listadoTipos()

        var adaptador= ArrayAdapter(this,android.R.layout.simple_list_item_1,data)

        atvServicio.setAdapter(adaptador)
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
                val selectedCalendar = Calendar.getInstance()
                selectedCalendar.set(yearSelected, monthOfYear, dayOfMonth)

                val currentCalendar = Calendar.getInstance()

                if (selectedCalendar < currentCalendar) {
                    // Fecha seleccionada es menor a la fecha actual
                    Toast.makeText(this, "Seleccione una fecha válida", Toast.LENGTH_SHORT).show()
                } else {
                    // Establecer la fecha seleccionada en el EditText
                    val selectedDate = "$dayOfMonth/${monthOfYear + 1}/$yearSelected"
                    editText.setText(selectedDate)
                }
            },
            year,
            month,
            day
        )

        datePickerDialog.datePicker.minDate = System.currentTimeMillis() - 1000 // Restringe fechas anteriores al día de hoy
        datePickerDialog.show()
    }

    private fun validarTelefono(phone: String): Boolean {

        val regex = Regex("^[0-9]{9}$")
        return regex.matches(phone)
    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        posTipos=p2+1
    }

}