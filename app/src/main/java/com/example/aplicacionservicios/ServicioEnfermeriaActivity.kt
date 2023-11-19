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
import com.example.aplicacionservicios.controlador.ArregloServicioEnfermeria
import com.example.aplicacionservicios.controlador.ArregloServicioEnfermeriaTipo
import com.example.aplicacionservicios.controlador.ArregloServicioTecnicoTipo
import com.example.aplicacionservicios.entidad.ServicioEnfermeria
import com.google.android.material.textfield.TextInputEditText
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
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

    private lateinit var data: List<String>

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

        btnEnferSiguiente.setOnClickListener {
            val phoneNumber = txtEnferTelefono.text.toString()
            if (!validarTelefono(phoneNumber)) {

                txtEnferTelefono.error = "Ingrese un número de teléfono válido"


            } else {

                siguiente()

            }

            btnEnferSiguiente.setOnClickListener {
                val cliente = txtEnferCliente.text.toString().trim()
                val telefono = txtEnferTelefono.text.toString().trim()
                val fecha = txtEnferFecha.text.toString().trim()
                val direccion = txtEnferDireccion.text.toString().trim()
                val informacion = txtEnferInformacion.text.toString().trim()

                if (cliente.isEmpty() || telefono.isEmpty() || fecha.isEmpty() || direccion.isEmpty() || informacion.isEmpty()) {
                    Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
                } else {
                    val phoneNumber = txtEnferTelefono.text.toString()
                    if (!validarTelefono(phoneNumber)) {

                        txtEnferTelefono.error = "Ingrese un número de teléfono válido"
                    } else {

                        siguiente()
                    }
                }
            }


        }



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
            val nombreServicio = "Servicio Tecnico"
            val codigoServicio = ArregloServicio().obtenerCodigoServicio(nombreServicio)

            if (codigoServicio != -1) {
                val precioTipoServicio = if (posTipos != -1) {
                    val precio = ArregloServicioTecnicoTipo().obtenerPrecioPorCodigo(posTipos)
                    precio.toString()
                } else {
                    "No disponible"
                }
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
        val nombreTipoServicio = data[posTipos]

        val precioTipoServicio = if (posTipos != -1) {
            val precio = ArregloServicioTecnicoTipo().obtenerPrecioPorCodigo(posTipos)
            "Precio: $precio"
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
        builder.setTitle("Resumen del Pedido")
        builder.setMessage(reporte)

        builder.setPositiveButton("Confirmar Pedido") { dialog, which ->

            confirmarPedido(servicioEnfermeria, precioTipoServicio, nombreTipoServicio)
        }

        builder.setNegativeButton("Cancelar") { dialog, which ->

            Toast.makeText(this, "Reporte cancelado", Toast.LENGTH_SHORT).show()
        }

        val dialog = builder.create()
        dialog.show()
    }


    fun confirmarPedido(servicioEnfermeria: ServicioEnfermeria, precioServicio: String, nombreTipoServicio: String) {
        val arregloServicioEnfermeria = ArregloServicioEnfermeria()


        val resultado = arregloServicioEnfermeria.adicionar(servicioEnfermeria)

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
        data= ArregloServicioEnfermeriaTipo().listadoTipos()
        //crear un adaptador con los valores de data
        var adaptador= ArrayAdapter(this,android.R.layout.simple_list_item_1,data)
        //enviar el objeto "adaptador" al atributo atvDistrito
        atvEnferServicio.setAdapter(adaptador)
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

    private fun validarTelefono(phone: String): Boolean {
        // Patrón para aceptar solo números y longitud de 9 dígitos
        val regex = Regex("^[0-9]{9}$")
        return regex.matches(phone)
    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        posTipos=p2+1
    }

}