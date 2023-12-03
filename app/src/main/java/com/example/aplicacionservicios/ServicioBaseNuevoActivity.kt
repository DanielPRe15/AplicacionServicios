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
import com.example.aplicacionservicios.entidad.Servicio
import com.google.android.material.textfield.TextInputEditText

class ServicioBaseNuevoActivity: AppCompatActivity(), AdapterView.OnItemClickListener {


    companion object {
        private const val REQUEST_SELECT_IMAGE = 100 // Puedes usar cualquier número entero
    }

    private lateinit var imageView: ImageView
    private var imageUri: Uri? = null

    private lateinit var  txtServBaseNombreNew: TextInputEditText
    private lateinit var  atvTrabajador: AutoCompleteTextView

    private lateinit var  btnServBaseGrabar: Button
    private lateinit var  btnServBaseSalirNew: Button
    private lateinit var  btnTrabSalirNew: Button
    private lateinit var  btnSeleccionarImagen: Button

    private lateinit var data: List<String>
    var posTrabajadores=-1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.servicio_nuevo)
        txtServBaseNombreNew=findViewById(R.id.txtServBaseNombreNew)
        atvTrabajador=findViewById(R.id.atvTrabajador)

        btnServBaseGrabar=findViewById(R.id.btnServBaseGrabar)
        btnSeleccionarImagen=findViewById(R.id.btnSeleccionarImagen)
        btnServBaseSalirNew=findViewById(R.id.btnServBaseSalirNew)


        atvTrabajador.setOnItemClickListener(this)

        imageView = findViewById(R.id.imageView)
        //

        val btnSeleccionarImagen: Button = findViewById(R.id.btnSeleccionarImagen)
        btnSeleccionarImagen.setOnClickListener {
            seleccionarImagen()
        }

        btnServBaseGrabar.setOnClickListener {grabar()}
        btnServBaseSalirNew.setOnClickListener {salir()}

        cargarTrabajadores()

    }


    fun cargarTrabajadores(){
        //invocar al método listadoDistritos
        data= ArregloTrabajador().listadoTrabajador()
        //crear un adaptador con los valores de data
        var adaptador= ArrayAdapter(this,android.R.layout.simple_list_item_1,data)
        //enviar el objeto "adaptador" al atributo atvDistrito
        atvTrabajador.setAdapter(adaptador)
    }
    private fun seleccionarImagen() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, ServicioBaseNuevoActivity.REQUEST_SELECT_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ServicioBaseNuevoActivity.REQUEST_SELECT_IMAGE && resultCode == RESULT_OK && data != null) {
            imageUri = data.data // Obtiene la URI de la imagen seleccionada
            imageView.setImageURI(imageUri) // Muestra la imagen seleccionada en el ImageView
        }
    }
    fun grabar(){
        var nomb="";var foto="";var trab:Int

        nomb=txtServBaseNombreNew.text.toString()



        //validar estado
        if (imageUri != null) {
            val foto = imageUri.toString() // Obtener la ruta de la imagen

            val serv = Servicio(0, nomb, posTrabajadores, foto)
            val estado = ArregloServicio().adicionar(serv)

            if (estado > 0) {
                Toast.makeText(this, "Servicio registrado", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Error en el registro", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(this, "Por favor, selecciona una imagen", Toast.LENGTH_LONG).show()
        }

        salir()
    }
    fun salir(){
        var intent= Intent(this,ServicioBaseMainActivity::class.java)
        startActivity(intent)
    }


    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        posTrabajadores=p2+1
    }

}