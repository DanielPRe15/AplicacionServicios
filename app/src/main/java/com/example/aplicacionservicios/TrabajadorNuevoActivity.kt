package com.example.aplicacionservicios

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.aplicacionservicios.controlador.ArregloServicio
import com.example.aplicacionservicios.controlador.ArregloTrabajador
import com.example.aplicacionservicios.entidad.Trabajador
import com.google.android.material.textfield.TextInputEditText

class TrabajadorNuevoActivity: AppCompatActivity(), AdapterView.OnItemClickListener {

    companion object {
        private const val REQUEST_SELECT_IMAGE = 100 // Puedes usar cualquier número entero
    }

    private lateinit var imageView: ImageView
    private var imageUri: Uri? = null

    private lateinit var  txtTrabNombreNew: TextInputEditText
    private lateinit var  txtTrabApellidoNew: TextInputEditText
    private lateinit var atvServicio: AutoCompleteTextView
    private lateinit var  txtTrabTelefonoNew: TextInputEditText
    private lateinit var  txtTrabEdadNew: TextInputEditText

    private lateinit var  btnTrabGrabar: Button
    private lateinit var  btnTrabSalirNew: Button
    private lateinit var  btnSeleccionarImagen: Button
    private lateinit var data: List<String>
    var posServicio=-1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.trabajador_nuevo)
        txtTrabNombreNew=findViewById(R.id.txtTrabNombreNew)
        txtTrabApellidoNew=findViewById(R.id.txtTrabApellidoNew)
        atvServicio=findViewById(R.id.atvServicio)
        txtTrabTelefonoNew=findViewById(R.id.txtTrabTelefonoNew)
        txtTrabEdadNew=findViewById(R.id.txtTrabEdadNew)

        btnTrabGrabar=findViewById(R.id.btnTrabGrabar)
        btnSeleccionarImagen=findViewById(R.id.btnSeleccionarImagen)
        btnTrabSalirNew=findViewById(R.id.btnTrabSalirNew)

        atvServicio.setOnItemClickListener(this)

        imageView = findViewById(R.id.imageView)
        //

        val btnSeleccionarImagen: Button = findViewById(R.id.btnSeleccionarImagen)
        btnSeleccionarImagen.setOnClickListener {
            seleccionarImagen()
        }


        btnTrabGrabar.setOnClickListener {grabar()}
        btnTrabSalirNew.setOnClickListener {salir()}

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
            imageView.setImageURI(imageUri) // Muestra la imagen seleccionada en el ImageView
        }
    }
    fun grabar() {
        val nomb = txtTrabNombreNew.text.toString()
        val apel = txtTrabApellidoNew.text.toString()
        val tele = txtTrabTelefonoNew.text.toString()
        val edad = txtTrabEdadNew.text.toString().toInt()


        if (imageUri != null) {
            val foto = imageUri.toString() // Obtener la ruta de la imagen

            val trab = Trabajador(0, nomb, apel, posServicio, tele, edad, foto)
            val estado = ArregloTrabajador().adicionar(trab)

            if (estado > 0) {
                Toast.makeText(this, "Trabajador registrado", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Error en el registro", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(this, "Por favor, selecciona una imagen", Toast.LENGTH_LONG).show()
        }

        salir()
    }
    fun salir(){
        var intent= Intent(this,TrabajadorMainActivity::class.java)
        startActivity(intent)
    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        posServicio=p2+1
    }

}