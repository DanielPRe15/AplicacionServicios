package com.example.aplicacionservicios

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.aplicacionservicios.controlador.ArregloServicio
import com.example.aplicacionservicios.entidad.Servicio
import com.google.android.material.textfield.TextInputEditText


class ServicioBaseEditarActivity: AppCompatActivity(), AdapterView.OnItemClickListener  {
    private lateinit var  txtServBaseCodigo: TextInputEditText
    private lateinit var  txtServBaseNombre: TextInputEditText
    private lateinit var  txtServBaseCodigoTrabajador: TextInputEditText
    private lateinit var  txtServBaseFoto: TextInputEditText
    private lateinit var  btnServBaseActualizar: Button
    private lateinit var  btnServBaseEliminar: Button
    private lateinit var  btnServBaseSalir: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.servicio_editar)
        txtServBaseCodigo=findViewById(R.id.txtServBaseCodigo)
        txtServBaseNombre=findViewById(R.id.txtServBaseNombre)
        txtServBaseCodigoTrabajador=findViewById(R.id.txtServBaseCodigoTrabajador)
        txtServBaseFoto=findViewById(R.id.txtServBaseFoto)
        btnServBaseActualizar=findViewById(R.id.btnServBaseActualizar)
        btnServBaseEliminar=findViewById(R.id.btnServBaseEliminar)
        btnServBaseSalir=findViewById(R.id.btnServBaseSalir)

        btnServBaseActualizar.setOnClickListener {actualizar()}
        btnServBaseEliminar.setOnClickListener {eliminar()}
        btnServBaseSalir.setOnClickListener {salir()}
        obtenerDatos()


    }
    fun actualizar(){
        //variables
        var nomb="";var foto="";
        var cod:Int;

        //leer cajas
        cod=txtServBaseCodigo.text.toString().toInt()
        nomb=txtServBaseNombre.text.toString()
        foto=txtServBaseFoto.text.toString()

        var serv=Servicio(cod,nomb,foto)
        //invocar al método actualizar
        var estado=ArregloServicio().actualizar(serv)
        //validar estado
        if (estado>0)
            Toast.makeText(this,"Servicio actualizado", Toast.LENGTH_LONG).show()
        else
            Toast.makeText(this,"Error en la actualización", Toast.LENGTH_LONG).show()
    }
    fun eliminar(){
        val dialogo1: AlertDialog.Builder = AlertDialog.Builder(this)
        dialogo1.setTitle("Sistema")
        dialogo1.setMessage("¿ Seguro de eliminar?")
        dialogo1.setCancelable(false)
        dialogo1.setPositiveButton("Aceptar",
            DialogInterface.OnClickListener { dialogInterface: DialogInterface, i: Int ->
                var cod:Int
                cod=txtServBaseCodigo.text.toString().toInt()
                var estado=ArregloServicio().eliminar(cod)
                //validar estado
                if (estado>0)
                    Toast.makeText(this,"Servicio eliminado", Toast.LENGTH_LONG).show()
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
        var intent= Intent(this,ServicioBaseMainActivity::class.java)
        startActivity(intent)
    }
    fun obtenerDatos(){
        var bundle=intent.getSerializableExtra("servicio") as Servicio

        //mostrar en los controles el valor de bundle
        txtServBaseCodigo.setText(bundle.codigo.toString())
        txtServBaseNombre.setText(bundle.nombre.toString())
        txtServBaseFoto.setText(bundle.foto)
    }
    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        TODO("Not yet implemented")
    }

}