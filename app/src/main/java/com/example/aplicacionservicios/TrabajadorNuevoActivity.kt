package com.example.aplicacionservicios

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
    private lateinit var  txtTrabTelefonoNew: TextInputEditText
    private lateinit var  txtTrabEdadNew: TextInputEditText

    private lateinit var  btnTrabGrabar: Button
    private lateinit var  btnTrabSalirNew: Button
    private lateinit var  btnSeleccionarImagen: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.trabajador_nuevo)
        txtTrabNombreNew=findViewById(R.id.txtTrabNombreNew)
        txtTrabApellidoNew=findViewById(R.id.txtTrabApellidoNew)
        txtTrabTelefonoNew=findViewById(R.id.txtTrabTelefonoNew)
        txtTrabEdadNew=findViewById(R.id.txtTrabEdadNew)

        btnTrabGrabar=findViewById(R.id.btnTrabGrabar)
        btnSeleccionarImagen=findViewById(R.id.btnSeleccionarImagen)
        btnTrabSalirNew=findViewById(R.id.btnTrabSalirNew)

        imageView = findViewById(R.id.imageView)
        //

        val btnSeleccionarImagen: Button = findViewById(R.id.btnSeleccionarImagen)
        btnSeleccionarImagen.setOnClickListener {
            seleccionarImagen()
        }


        btnTrabGrabar.setOnClickListener {grabar()}
        btnTrabSalirNew.setOnClickListener {salir()}
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

            val trab = Trabajador(0, nomb, apel, tele, edad, foto)
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
        TODO("Not yet implemented")
    }

}