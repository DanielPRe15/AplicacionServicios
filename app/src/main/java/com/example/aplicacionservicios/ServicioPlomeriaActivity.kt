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
import com.example.aplicacionservicios.controlador.ArregloServicioPlomeria
import com.example.aplicacionservicios.controlador.ArregloServicioPlomeriaTipo
import com.example.aplicacionservicios.controlador.ArregloServicioTecnicoTipo
import com.example.aplicacionservicios.entidad.ServicioPlomeria
import com.google.android.material.textfield.TextInputEditText
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

class ServicioPlomeriaActivity : AppCompatActivity(), AdapterView.OnItemClickListener {

    private lateinit var txtPlomCliente: TextInputEditText
    private lateinit var txtPlomTelefono: TextInputEditText
    private lateinit var txtPlomFecha: TextInputEditText
    private lateinit var txtPlomDireccion: TextInputEditText
    private lateinit var txtPlomInformacion: TextInputEditText

    private lateinit var atvPlomServicio: AutoCompleteTextView

    private lateinit var btnPlomSiguiente: Button
    private lateinit var btnPlomCancelar: Button

    private lateinit var data: List<String>

    var posTipos=-1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.plomeria_main)

        txtPlomCliente = findViewById(R.id.txtPlomCliente)
        txtPlomTelefono = findViewById(R.id.txtPlomTelefono)
        txtPlomFecha = findViewById(R.id.txtPlomFecha)
        txtPlomDireccion = findViewById(R.id.txtPlomDireccion)
        txtPlomInformacion = findViewById(R.id.txtPlomInformacion)
        atvPlomServicio = findViewById(R.id.atvPlomServicio)
        btnPlomSiguiente = findViewById(R.id.btnPlomSiguiente)
        btnPlomCancelar = findViewById(R.id.btnPlomCancelar)
        btnPlomSiguiente.setOnClickListener{siguiente()}
        btnPlomCancelar.setOnClickListener{cancelar()}

        atvPlomServicio.setOnItemClickListener(this)


        btnPlomSiguiente.setOnClickListener {
            val phoneNumber = txtPlomTelefono.text.toString()
            if (!validarTelefono(phoneNumber)) {

                txtPlomTelefono.error = "Ingrese un número de teléfono válido"


            } else {

                siguiente()

            }

            btnPlomSiguiente.setOnClickListener {
                val cliente = txtPlomCliente.text.toString().trim()
                val telefono = txtPlomTelefono.text.toString().trim()
                val fecha = txtPlomFecha.text.toString().trim()
                val direccion = txtPlomDireccion.text.toString().trim()
                val informacion = txtPlomInformacion.text.toString().trim()

                if (cliente.isEmpty() || telefono.isEmpty() || fecha.isEmpty() || direccion.isEmpty() || informacion.isEmpty()) {
                    Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
                } else {
                    val phoneNumber = txtPlomTelefono.text.toString()
                    if (!validarTelefono(phoneNumber)) {

                        txtPlomTelefono.error = "Ingrese un número de teléfono válido"
                    } else {

                        siguiente()
                    }
                }
            }


        }



        cargarTipos()

    }

    fun siguiente() {
        var cliente = txtPlomCliente.text.toString()
        var telefono = txtPlomTelefono.text.toString()
        var fecha = txtPlomFecha.text.toString()
        var direccion = txtPlomDireccion.text.toString()
        var informacion = txtPlomInformacion.text.toString()

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
            val nombreTipoServicio = data[posTipos]
            val codigoServicio = ArregloServicio().obtenerCodigoServicio(nombreServicio)


            if (codigoServicio != -1) {
                val precioTipoServicio = if (posTipos != -1) {
                    val precio = ArregloServicioTecnicoTipo().obtenerPrecioPorCodigo(posTipos)
                    precio.toString()
                } else {
                    "No disponible"
                }
                var servicioPlomeria = ServicioPlomeria(
                    codigoServicioTec = 0,
                    codigoServi = codigoServicio,
                    codigoTipo = posTipos,
                    nombreCliente = cliente,
                    telefonoCliente = telefono,
                    fecha = date,
                    direccionCliente = direccion,
                    informacionAdicional = informacion
                )

                mostrarReporte(servicioPlomeria)
            } else {
                Toast.makeText(this, "No se encontró el código para el servicio: $nombreServicio", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun mostrarReporte(servicioPlomeria: ServicioPlomeria) {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy")
        val fechaFormateada = dateFormat.format(servicioPlomeria.fecha)
        val nombreTipoServicio = data[posTipos]

        val precioTipoServicio = if (posTipos != -1) {
            val precio = ArregloServicioTecnicoTipo().obtenerPrecioPorCodigo(posTipos)
            "Precio: $precio"
        } else {
            "Precio: No disponible"
        }

        val reporte = "Cliente: ${servicioPlomeria.nombreCliente}\n" +
                "Teléfono: ${servicioPlomeria.telefonoCliente}\n" +
                "Fecha: $fechaFormateada\n" +
                "Dirección: ${servicioPlomeria.direccionCliente}\n" +
                "Información Adicional: ${servicioPlomeria.informacionAdicional}\n" +
                "$precioTipoServicio"


        val builder = AlertDialog.Builder(this, R.style.CustomAlertDialogStyle)
        builder.setTitle("Resumen del Pedido")
        builder.setMessage(reporte)

        builder.setPositiveButton("Confirmar Pedido") { dialog, which ->

            confirmarPedido(servicioPlomeria, precioTipoServicio, nombreTipoServicio)
        }

        builder.setNegativeButton("Cancelar") { dialog, which ->

            Toast.makeText(this, "Reporte cancelado", Toast.LENGTH_SHORT).show()
        }

        val dialog = builder.create()
        dialog.show()
    }


    fun confirmarPedido(servicioPlomeria: ServicioPlomeria, precioServicio: String, nombreTipoServicio: String) {
        val arregloServicioPlomeria = ArregloServicioPlomeria()


        val resultado = arregloServicioPlomeria.adicionar(servicioPlomeria)

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
         data = ArregloServicioPlomeriaTipo().listadoTipos()
        //crear un adaptador con los valores de data
        var adaptador= ArrayAdapter(this,android.R.layout.simple_list_item_1,data)
        //enviar el objeto "adaptador" al atributo atvDistrito
        atvPlomServicio.setAdapter(adaptador)
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