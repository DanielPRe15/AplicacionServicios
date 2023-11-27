package com.example.aplicacionservicios

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aplicacionservicios.adaptador.ServicioAdapter
import com.example.aplicacionservicios.controlador.ArregloServicio
import com.example.aplicacionservicios.entidad.Servicio
import com.google.firebase.auth.FirebaseAuth

class MenuPrincipalActivity : AppCompatActivity(), ServicioAdapter.OnServicioItemClickListener  {
    private lateinit var servicioAdapter: ServicioAdapter
    private lateinit var rvServicio: RecyclerView
    private lateinit var editTextSearch: EditText
    private var serviciosList = ArrayList<Servicio>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_principal)
        rvServicio=findViewById(R.id.rvServicio)
        editTextSearch = findViewById(R.id.editTextSearch)


        serviciosList = ArregloServicio().listado()
        servicioAdapter = ServicioAdapter(serviciosList, this)


        //estilo tipo fila para visualizar datos en rvDocentes
        rvServicio.layoutManager= LinearLayoutManager(this)
        //rvServicio.layoutManager=GridLayoutManager(this,1)
        //mostrar el valor del objeto adaptador al atributo rvDocentes
        rvServicio.adapter=servicioAdapter

        editTextSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                servicioAdapter.filter.filter(s)
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }



    fun cerrarSesion(view: View) {
        FirebaseAuth.getInstance().signOut()
        startActivity(Intent(this,MainActivity ::class.java))
    }

    override fun onServicioItemClick(servicio: Servicio) {
        val intent = Intent(this, ServicioTecnicoActivity::class.java)
        intent.putExtra("servicio", servicio)
        startActivity(intent)
    }

}

private fun Intent.putExtra(s: String, servicio: Servicio) {

}