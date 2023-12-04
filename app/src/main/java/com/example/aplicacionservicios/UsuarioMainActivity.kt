package com.example.aplicacionservicios

import UsuarioAdapter
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aplicacionservicios.entidad.Usuario2
import com.google.firebase.firestore.FirebaseFirestore


class UsuarioMainActivity: AppCompatActivity() {
    private lateinit var rvUsuario: RecyclerView
    private lateinit var btnUsuarioMainSalir: Button

    private lateinit var adapter: UsuarioAdapter
    private val userList = ArrayList<Usuario2>()

   // private lateinit var BD:DatabaseReference
   // private lateinit var lista:ArrayList<Usuario2>

   // private val userList = ArrayList<Usuario2>()
   // private lateinit var adapter: UsuarioAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.usuario_main)

        rvUsuario = findViewById(R.id.rvUsuario)
        rvUsuario.layoutManager = LinearLayoutManager(this)

        btnUsuarioMainSalir = findViewById(R.id.btnUsuarioMainSalir)
        btnUsuarioMainSalir.setOnClickListener { salir() }

       // Crear un adaptador para el RecyclerView
       adapter = UsuarioAdapter(userList)
       rvUsuario.adapter = adapter

       // Obtener datos de la base de datos
       val db = FirebaseFirestore.getInstance()
       val usuariosRef = db.collection("usuarios")

       usuariosRef.addSnapshotListener { snapshot, e ->
           if (e != null) {
               // Manejar el error
               return@addSnapshotListener
           }

           userList.clear() // Limpiar la lista antes de agregar nuevos datos

           if (snapshot != null && !snapshot.isEmpty) {
               for (document in snapshot.documents) {
                   val usuario = document.toObject(Usuario2::class.java)
                   if (usuario != null) {
                       userList.add(usuario)
                   }
               }
               adapter.notifyDataSetChanged()
           }
       }
   }

        //lista = ArrayList<Usuario2>()
       //conectar()
      // listado()


/*


    fun conectar (){
        FirebaseApp.initializeApp(this)
        BD=FirebaseDatabase.getInstance().reference
    }

    fun listado(){
        BD.child("usuarios").addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot){
                snapshot.children.forEach{row->
                    val bean = Usuario2(
                        row.child("nombre").getValue<String>() as String,
                        row.child("apellido").getValue<String>() as String,
                        row.child("rol").getValue<String>() as String)
                    lista.add(bean)
                }
                var adaptador = UsuarioAdapter(lista)
                rvUsuario.layoutManager=LinearLayoutManager(appConfig.CONTEXT)
                rvUsuario.adapter=adaptador
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(appConfig.CONTEXT, error.message, Toast.LENGTH_LONG).show()
            }
        })
    }
*/

    fun salir(){
        val intent= Intent(this,PrincipalAdminActivity::class.java)
        startActivity(intent)
    }
}