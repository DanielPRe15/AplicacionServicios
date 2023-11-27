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
import com.example.aplicacionservicios.controlador.ArregloServicioElectrico
import com.example.aplicacionservicios.controlador.ArregloServicioElectricoTipo
import com.example.aplicacionservicios.controlador.ArregloServicioTecnicoTipo
import com.example.aplicacionservicios.entidad.ServicioElectrico
import com.google.android.material.textfield.TextInputEditText
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
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
    private lateinit var data: List<String>

    var posTipos=-1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.electricista_main)

        txtElecCliente = findViewById(R.id.txtElecCliente)
        txtElecTelefono = findViewById(R.id.txtElecTelefono)
        txtElecFecha = findViewById(R.id.txtEleFecha)
        txtElecDireccion = findViewById(R.id.txtElecDireccion)
        txtElecInformacion = findViewById(R.id.txtElecInformacion)
        atvElecServicio = findViewById(R.id.atvElecServicio)
        btnElecSiguiente = findViewById(R.id.btnElecSiguiente)
        btnElecCancelar = findViewById(R.id.btnElecCancelar)
        btnElecSiguiente.setOnClickListener { siguiente() }
        btnElecCancelar.setOnClickListener{ cancelar() }

        atvElecServicio.setOnItemClickListener(this)
        btnElecSiguiente.setOnClickListener {
            val phoneNumber = txtElecTelefono.text.toString()
            if (!validarTelefono(phoneNumber)) {

                txtElecTelefono.error = "Ingrese un número de teléfono válido"


            } else {

                siguiente()

            }

            btnElecSiguiente.setOnClickListener {
                val cliente = txtElecCliente.text.toString().trim()
                val telefono = txtElecTelefono.text.toString().trim()
                val fecha = txtElecFecha.text.toString().trim()
                val direccion = txtElecDireccion.text.toString().trim()
                val informacion = txtElecInformacion.text.toString().trim()

                if (cliente.isEmpty() || telefono.isEmpty() || fecha.isEmpty() || direccion.isEmpty() || informacion.isEmpty()) {
                    Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
                } else {
                    val phoneNumber = txtElecTelefono.text.toString()
                    if (!validarTelefono(phoneNumber)) {

                        txtElecTelefono.error = "Ingrese un número de teléfono válido"
                    } else {

                        siguiente()
                    }
                }
            }


        }



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
            val nombreServicio = "Servicio Tecnico"
            val codigoServicio = ArregloServicio().obtenerCodigoServicio(nombreServicio)

            if (codigoServicio != -1) {
                val precioTipoServicio = if (posTipos != -1) {
                    val precio = ArregloServicioTecnicoTipo().obtenerPrecioPorCodigo(posTipos)
                    precio.toString()
                } else {
                    "No disponible"
                }

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
        val nombreTipoServicio = data[posTipos]

        val precioTipoServicio = if (posTipos != -1) {
            val precio = ArregloServicioElectricoTipo().obtenerPrecioPorCodigo(posTipos)
            "Precio: $precio"
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
        builder.setTitle("Resumen del Pedido")
        builder.setMessage(reporte)

        builder.setPositiveButton("Confirmar Pedido") { dialog, which ->

            confirmarPedido(servicioElectrico, precioTipoServicio, nombreTipoServicio)
        }

        builder.setNegativeButton("Cancelar") { dialog, which ->

            Toast.makeText(this, "Reporte cancelado", Toast.LENGTH_SHORT).show()
        }

        val dialog = builder.create()
        dialog.show()
    }


    fun confirmarPedido(servicioElectrico: ServicioElectrico,precioServicio: String, nombreTipoServicio: String ) {
        val arregloServicioElectrico = ArregloServicioElectrico()


        val resultado = arregloServicioElectrico.adicionar(servicioElectrico)

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
        //invocar al método listadoDistritos
         data= ArregloServicioElectricoTipo().listadoTipos()
        //crear un adaptador con los valores de data
        var adaptador= ArrayAdapter(this,android.R.layout.simple_list_item_1,data)
        //enviar el objeto "adaptador" al atributo atvDistrito
        atvElecServicio.setAdapter(adaptador)
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
        // Patrón para aceptar solo números y longitud de 9 dígitos
        val regex = Regex("^[0-9]{9}$")
        return regex.matches(phone)
    }
    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        posTipos=p2+1
    }

}