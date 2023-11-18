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
import com.example.aplicacionservicios.controlador.ArregloServicioLimpieza
import com.example.aplicacionservicios.controlador.ArregloServicioLimpiezaTipo
import com.example.aplicacionservicios.controlador.ArregloServicioTecnicoTipo
import com.example.aplicacionservicios.entidad.ServicioLimpieza
import com.google.android.material.textfield.TextInputEditText
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

class ServicioHogarActivity : AppCompatActivity(),AdapterView.OnItemClickListener {

    private lateinit var txtHogarCliente: TextInputEditText
    private lateinit var txtHogarTelefono: TextInputEditText
    private lateinit var txtHogarFecha: TextInputEditText
    private lateinit var txtHogarDireccion: TextInputEditText
    private lateinit var txtHogarInformacion: TextInputEditText

    private lateinit var atvHogarServicio: AutoCompleteTextView

    private lateinit var btnHogarSiguiente: Button
    private lateinit var btnHogarCancelar: Button

    var posTipos=-1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.limpieza_hogar_main)

        txtHogarCliente = findViewById(R.id.txtHogarCliente)
        txtHogarTelefono = findViewById(R.id.txtHogarTelefono)
        txtHogarFecha = findViewById(R.id.txtHogarFecha)
        txtHogarDireccion = findViewById(R.id.txtHogarDireccion)
        txtHogarInformacion = findViewById(R.id.txtHogarInformacion)
        atvHogarServicio = findViewById(R.id.atvHogarServicio)
        btnHogarSiguiente = findViewById(R.id.btnHogarSiguiente)
        btnHogarCancelar = findViewById(R.id.btnHogarCancelar)
        btnHogarSiguiente.setOnClickListener { siguiente() }
        btnHogarCancelar.setOnClickListener { cancelar() }

        atvHogarServicio.setOnItemClickListener(this)


        btnHogarSiguiente.setOnClickListener {
            val phoneNumber = txtHogarTelefono.text.toString()
            if (!validarTelefono(phoneNumber)) {
                // Si el teléfono no cumple con las condiciones, mostrar un mensaje de error
                txtHogarTelefono.error = "Ingrese un número de teléfono válido"


            } else {
                // Si el teléfono es válido, continuar con la lógica deseada
                // Ejemplo: Ir a otra actividad o realizar alguna operación
                siguiente()

            }

            btnHogarSiguiente.setOnClickListener {
                val cliente = txtHogarCliente.text.toString().trim()
                val telefono = txtHogarTelefono.text.toString().trim()
                val fecha = txtHogarFecha.text.toString().trim()
                val direccion = txtHogarDireccion.text.toString().trim()
                val informacion = txtHogarInformacion.text.toString().trim()

                if (cliente.isEmpty() || telefono.isEmpty() || fecha.isEmpty() || direccion.isEmpty() || informacion.isEmpty()) {
                    Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
                } else {
                    val phoneNumber = txtHogarTelefono.text.toString()
                    if (!validarTelefono(phoneNumber)) {
                        // Si el teléfono no cumple con las condiciones, mostrar un mensaje de error
                        txtHogarTelefono.error = "Ingrese un número de teléfono válido"
                    } else {
                        // Si todos los campos están completos y el teléfono es válido, continuar con la lógica deseada
                        siguiente()
                    }
                }
            }


        }



        cargarTipos()

    }

    fun siguiente() {
        var cliente = txtHogarCliente.text.toString()
        var telefono = txtHogarTelefono.text.toString()
        var fecha = txtHogarFecha.text.toString()
        var direccion = txtHogarDireccion.text.toString()
        var informacion = txtHogarInformacion.text.toString()

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
                val precioTipoServicio = if (posTipos != -1) {
                    val precio = ArregloServicioTecnicoTipo().obtenerPrecioPorCodigo(posTipos)
                    precio.toString()
                } else {
                    "No disponible"
                }

                var servicioLimpieza = ServicioLimpieza(
                    codigoServicioTec = 0,
                    codigoServi = codigoServicio,
                    codigoTipo = posTipos,
                    nombreCliente = cliente,
                    telefonoCliente = telefono,
                    fecha = date,
                    direccionCliente = direccion,
                    informacionAdicional = informacion
                )

                mostrarReporte(servicioLimpieza)
            } else {
                Toast.makeText(this, "No se encontró el código para el servicio: $nombreServicio", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun mostrarReporte(servicioLimpieza: ServicioLimpieza) {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy")
        val fechaFormateada = dateFormat.format(servicioLimpieza.fecha)

        val precioTipoServicio = if (posTipos != -1) {
            val precio = ArregloServicioLimpiezaTipo().obtenerPrecioPorCodigo(posTipos)
            "Precio: $precio" // Mostrar el precio del tipo de servicio en el reporte
        } else {
            "Precio: No disponible"
        }

        val reporte = "Cliente: ${servicioLimpieza.nombreCliente}\n" +
                "Teléfono: ${servicioLimpieza.telefonoCliente}\n" +
                "Fecha: $fechaFormateada\n" +
                "Dirección: ${servicioLimpieza.direccionCliente}\n" +
                "Información Adicional: ${servicioLimpieza.informacionAdicional}\n" +
                "$precioTipoServicio"


        val builder = AlertDialog.Builder(this, R.style.CustomAlertDialogStyle)
        builder.setTitle("Reporte del Servicio Técnico")
        builder.setMessage(reporte)

        builder.setPositiveButton("Confirmar Pedido") { dialog, which ->
            // Aquí iría la lógica para confirmar el pedido
            confirmarPedido(servicioLimpieza,precioTipoServicio)
        }

        builder.setNegativeButton("Cancelar") { dialog, which ->
            // Aquí podrías realizar alguna acción si se cancela el reporte
            Toast.makeText(this, "Reporte cancelado", Toast.LENGTH_SHORT).show()
        }

        val dialog = builder.create()
        dialog.show()
    }


    fun confirmarPedido(servicioLimpieza: ServicioLimpieza, precioServicio: String) {
        val arregloServicioLimpieza = ArregloServicioLimpieza()

        // Simular la adición del servicio técnico a la lista
        val resultado = arregloServicioLimpieza.adicionar(servicioLimpieza)

        if (resultado > 0) {
            // Acciones posteriores a la confirmación exitosa del pedido
            Toast.makeText(this, "Pedido confirmado exitosamente", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, ConfirmacionActivity::class.java)
            intent.putExtra("precio", precioServicio)
            startActivity(intent)

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
        var data= ArregloServicioLimpiezaTipo().listadoTipos()
        //crear un adaptador con los valores de data
        var adaptador= ArrayAdapter(this,android.R.layout.simple_list_item_1,data)
        //enviar el objeto "adaptador" al atributo atvDistrito
        atvHogarServicio.setAdapter(adaptador)
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