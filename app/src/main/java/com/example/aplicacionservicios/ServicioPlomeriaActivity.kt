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

class ServicioPlomeriaActivity : AppCompatActivity(), AdapterView.OnItemClickListener{

    private lateinit var txtPlomCliente : TextInputEditText
    private lateinit var txtPlomTelefono : TextInputEditText
    private lateinit var txtPlomFecha : TextInputEditText
    private lateinit var txtPlomDireccion : TextInputEditText
    private lateinit var txtPlomInformacion : TextInputEditText

    private lateinit var atvPlomServicio : AutoCompleteTextView

    private lateinit var btnPlomSiguiente : Button
    private lateinit var btnPlomCancelar : Button

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
                // Si el teléfono no cumple con las condiciones, mostrar un mensaje de error
                txtPlomTelefono.error = "Ingrese un número de teléfono válido"


            } else {
                // Si el teléfono es válido, continuar con la lógica deseada
                // Ejemplo: Ir a otra actividad o realizar alguna operación
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
                        // Si el teléfono no cumple con las condiciones, mostrar un mensaje de error
                        txtPlomTelefono.error = "Ingrese un número de teléfono válido"
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
            val nombreServicio = "Servicio Tecnico" // Reemplazar con el nombre real del servicio que estás registrando
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

        val precioTipoServicio = if (posTipos != -1) {
            val precio = ArregloServicioTecnicoTipo().obtenerPrecioPorCodigo(posTipos)
            "Precio: $precio" // Mostrar el precio del tipo de servicio en el reporte
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
        builder.setTitle("Reporte del Servicio Técnico")
        builder.setMessage(reporte)

        builder.setPositiveButton("Confirmar Pedido") { dialog, which ->
            // Aquí iría la lógica para confirmar el pedido
            confirmarPedido(servicioPlomeria, precioTipoServicio)
        }

        builder.setNegativeButton("Cancelar") { dialog, which ->
            // Aquí podrías realizar alguna acción si se cancela el reporte
            Toast.makeText(this, "Reporte cancelado", Toast.LENGTH_SHORT).show()
        }

        val dialog = builder.create()
        dialog.show()
    }


    fun confirmarPedido(servicioPlomeria: ServicioPlomeria, precioServicio: String) {
        val arregloServicioPlomeria = ArregloServicioPlomeria()

        // Simular la adición del servicio técnico a la lista
        val resultado = arregloServicioPlomeria.adicionar(servicioPlomeria)

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
        var data= ArregloServicioPlomeriaTipo().listadoTipos()
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