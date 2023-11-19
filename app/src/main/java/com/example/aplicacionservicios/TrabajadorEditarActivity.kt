package com.example.aplicacionservicios

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.aplicacionservicios.controlador.ArregloTrabajador
import com.example.aplicacionservicios.entidad.Trabajador
import com.google.android.material.textfield.TextInputEditText

class TrabajadorEditarActivity: AppCompatActivity(), AdapterView.OnItemClickListener  {
    private lateinit var  txtTrabCodigo: TextInputEditText
    private lateinit var  txtTrabNombre: TextInputEditText
    private lateinit var  txtTrabApellido: TextInputEditText
    private lateinit var  txtTrabTelefono: TextInputEditText
    private lateinit var  txtTrabEdad: TextInputEditText
    private lateinit var  txtTrabFoto: TextInputEditText
    private lateinit var  btnTrabActualizar: Button
    private lateinit var  btnTrabEliminar: Button
    private lateinit var  btnTrabSalir: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.trabajador_editar)
        txtTrabCodigo=findViewById(R.id.txtTrabCodigo)
        txtTrabNombre=findViewById(R.id.txtTrabNombre)
        txtTrabApellido=findViewById(R.id.txtTrabApellido)
        txtTrabTelefono=findViewById(R.id.txtTrabTelefono)
        txtTrabEdad=findViewById(R.id.txtTrabEdad)
        txtTrabFoto=findViewById(R.id.txtTrabFoto)
        btnTrabActualizar=findViewById(R.id.btnTrabActualizar)
        btnTrabEliminar=findViewById(R.id.btnTrabEliminar)
        btnTrabSalir=findViewById(R.id.btnTrabSalir)

        btnTrabActualizar.setOnClickListener {actualizar()}
        btnTrabEliminar.setOnClickListener {eliminar()}
        btnTrabSalir.setOnClickListener {salir()}
        obtenerDatos()
    }
    fun actualizar(){
        //variables
        var nomb="";var apel="";var tele="";var edad=0;var foto:String;var cod:Int
        //leer cajas
        cod=txtTrabCodigo.text.toString().toInt()
        nomb=txtTrabNombre.text.toString()
        apel=txtTrabApellido.text.toString()
        tele=txtTrabTelefono.text.toString()
        edad= txtTrabEdad.text.toString().toInt()
        foto=txtTrabFoto.text.toString()
        //crear objeto de la clase Docente
        var trab=Trabajador(cod,nomb,apel,tele,edad,foto)
        //invocar al método actualizar
        var estado=ArregloTrabajador().actualizar(trab)
        //validar estado
        if (estado>0)
            Toast.makeText(this,"Trabajador actualizado", Toast.LENGTH_LONG).show()
        else
            Toast.makeText(this,"Error en la actualización", Toast.LENGTH_LONG).show()
    }
    fun eliminar(){
        val dialogo1: AlertDialog.Builder = AlertDialog.Builder(this)
        dialogo1.setTitle("Sistema")
        dialogo1.setMessage("¿ Seguro de calcular?")
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
    fun obtenerDatos(){
        var bundle=intent.getSerializableExtra("trabajador") as Trabajador
        //mostrar en los controles el valor de bundle
        txtTrabCodigo.setText(bundle.codigo.toString())
        txtTrabNombre.setText(bundle.nombre)
        txtTrabApellido.setText(bundle.apellido)
        txtTrabTelefono.setText(bundle.telefono)
        txtTrabEdad.setText(bundle.edad.toString())
        txtTrabFoto.setText(bundle.foto)
    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        TODO("Not yet implemented")
    }

}