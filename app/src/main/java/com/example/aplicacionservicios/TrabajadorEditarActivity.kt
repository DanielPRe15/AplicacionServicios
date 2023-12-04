package com.example.aplicacionservicios

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.aplicacionservicios.controlador.ArregloServicio
import com.example.aplicacionservicios.controlador.ArregloTrabajador
import com.example.aplicacionservicios.entidad.Trabajador
import com.google.android.material.textfield.TextInputEditText
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso


class TrabajadorEditarActivity: AppCompatActivity(), AdapterView.OnItemClickListener  {

    private var bundle: Trabajador? = null
    companion object {
        private const val REQUEST_SELECT_IMAGE = 100 // Puedes usar cualquier número entero
    }

    private lateinit var imageView: ImageView
    private var imageUri: Uri? = null


    private lateinit var  txtTrabCodigo: TextInputEditText
    private lateinit var  txtTrabNombre: TextInputEditText
    private lateinit var  txtTrabApellido: TextInputEditText
    private lateinit var atvServicio: AutoCompleteTextView
    private lateinit var  txtTrabTelefono: TextInputEditText
    private lateinit var  txtTrabEdad: TextInputEditText


    // private lateinit var  txtTrabFoto: TextInputEditText
    private lateinit var  btnTrabActualizar: Button
    private lateinit var  btnTrabEliminar: Button
    private lateinit var  btnTrabSalir: Button
    private lateinit var  btnSeleccionarImagen: Button
    private lateinit var data: List<String>
    var posServicio=-1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.trabajador_editar)
        txtTrabCodigo=findViewById(R.id.txtTrabCodigo)
        txtTrabNombre=findViewById(R.id.txtTrabNombre)
        txtTrabApellido=findViewById(R.id.txtTrabApellido)
        atvServicio=findViewById(R.id.atvServicio)
        txtTrabTelefono=findViewById(R.id.txtTrabTelefono)
        txtTrabEdad=findViewById(R.id.txtTrabEdad)
        //txtTrabFoto=findViewById(R.id.txtTrabFoto)
        btnTrabActualizar=findViewById(R.id.btnTrabActualizar)
        btnSeleccionarImagen=findViewById(R.id.btnSeleccionarImagen)
        btnTrabEliminar=findViewById(R.id.btnTrabEliminar)
        btnTrabSalir=findViewById(R.id.btnTrabSalir)

        atvServicio.setOnItemClickListener(this)
        imageView = findViewById(R.id.imageView)

        btnSeleccionarImagen.setOnClickListener { seleccionarImagen() }

        btnTrabActualizar.setOnClickListener {actualizar()}
        btnTrabEliminar.setOnClickListener {eliminar()}
        btnTrabSalir.setOnClickListener {salir()}

        obtenerDatos()
        cargarServicio()
    }


    fun cargarServicio(){
        //invocar al método listadoDistritos
        data= ArregloServicio().listadoServicio()
        //crear un adaptador con los valores de data
        var adaptador= ArrayAdapter(this,android.R.layout.simple_list_item_1,data)
        //enviar el objeto "adaptador" al atributo atvDistrito
        atvServicio.setAdapter(adaptador)
    }


    private fun seleccionarImagen() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, REQUEST_SELECT_IMAGE)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_SELECT_IMAGE && resultCode == RESULT_OK && data != null) {
            imageUri = data.data // Obtiene la URI de la imagen seleccionada
            if (imageUri != null) {
                Picasso.get().load(imageUri).into(imageView)
            }
        }
    }
    fun actualizar() {
        // Variables
        var nomb = ""
        var apel = ""
        var tele = ""
        var edad = 0
        var foto: String
        var cod = 0 // Declaración de la variable para el código del servicio

        // Leer valores de las cajas de texto
        cod = txtTrabCodigo.text.toString().toInt()
        nomb = txtTrabNombre.text.toString()
        apel = txtTrabApellido.text.toString()
        tele = txtTrabTelefono.text.toString()
        edad = txtTrabEdad.text.toString().toInt()

        // Obtener el código del servicio seleccionado (si hay uno)
        val codigoServicioSeleccionado = posServicio // Utiliza la variable 'posServicio' obtenida del onItemClick

        foto = imageUri?.toString() ?: bundle?.foto ?: ""

        // Crear un objeto Trabajador con los datos recopilados
        val trab = Trabajador(cod, nomb, apel, codigoServicioSeleccionado, tele, edad, foto)

        // Actualizar el trabajador en la base de datos o almacenamiento correspondiente
        val estado = ArregloTrabajador().actualizar(trab)

        if (estado > 0) {
            Toast.makeText(this, "Trabajador actualizado", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Error en la actualización", Toast.LENGTH_LONG).show()
        }


        salir()
    }
    fun eliminar(){
        val dialogo1: AlertDialog.Builder = AlertDialog.Builder(this)
        dialogo1.setTitle("Sistema")
        dialogo1.setMessage("¿ Seguro de eliminar?")
        dialogo1.setCancelable(false)
        dialogo1.setPositiveButton("Aceptar",
            DialogInterface.OnClickListener { dialogInterface: DialogInterface, i: Int ->
                var cod:Int
                cod=txtTrabCodigo.text.toString().toInt()
                var estado=ArregloTrabajador().eliminar(cod)
                //validar estado
                if (estado>0)
                    Toast.makeText(this,"Trabajador eliminado", Toast.LENGTH_LONG).show()
                else
                    Toast.makeText(this,"Error en la eliminación", Toast.LENGTH_LONG).show()
                salir()
            })
        dialogo1.setNegativeButton("Cancelar",
            DialogInterface.OnClickListener { dialogo1, id -> })
        dialogo1.setIcon(android.R.drawable.ic_dialog_alert)
        dialogo1.show()

    }
    fun salir(){
        var intent= Intent(this,TrabajadorMainActivity::class.java)
        startActivity(intent)
    }
    fun obtenerDatos() {
        bundle = intent.getSerializableExtra("trabajador") as? Trabajador

        bundle?.let {
            txtTrabCodigo.setText(it.codigo.toString())
            txtTrabNombre.setText(it.nombre)
            txtTrabApellido.setText(it.apellido)
            txtTrabTelefono.setText(it.telefono)
            txtTrabEdad.setText(it.edad.toString())

            // Carga los datos del servicio en el AutoCompleteTextView
            cargarServicio()

            val nombreServicio = obtenerNombreServicio(it.codigoServicio) // Obtener el nombre del servicio

            // Establece el servicio actual del trabajador en el AutoCompleteTextView
            atvServicio.setText(nombreServicio)

            val fotoUrl = it.foto

            Log.d("TrabajadorEditar", "URI de la imagen seleccionada: $imageUri") // Verifica la URI en el registro

            // Carga la imagen seleccionada en el ImageView usando Picasso
            if (imageUri != null) {
                Picasso.get().load(imageUri).into(imageView, object : Callback {
                    override fun onSuccess() {
                        Log.d("TrabajadorEditar", "Imagen cargada correctamente")
                    }

                    override fun onError(e: Exception?) {
                        Log.e("TrabajadorEditar", "Error al cargar la imagen: $e")
                    }
                })
            }
        }
    }

    fun obtenerNombreServicio(codigoServicio: Int): String {
        val arregloServicio = ArregloServicio()
        val listaNombresServicio = arregloServicio.listadoServicio()

        return if (listaNombresServicio.size > codigoServicio - 1) {
            listaNombresServicio[codigoServicio - 1]
        } else {
            "Servicio no encontrado" // Cambiar por el mensaje que desees en caso de no encontrar el servicio
        }
    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        posServicio=p2+1
    }


}