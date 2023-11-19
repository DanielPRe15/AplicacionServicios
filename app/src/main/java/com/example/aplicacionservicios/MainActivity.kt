package com.example.aplicacionservicios

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    private lateinit var txtUsuario : TextInputEditText
    private lateinit var txtContrasena : TextInputEditText
    private lateinit var progressBar: ProgressBar
    private lateinit var auth : FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        txtUsuario = findViewById(R.id.txtUsuario)
        txtContrasena = findViewById(R.id.txtContrasena)

        progressBar = findViewById(R.id.progressBar2)
        auth = FirebaseAuth.getInstance()

    }

    fun Password(view : View)
    {
        startActivity(Intent(this,OlvidarContrasenaActivity::class.java))
    }

    fun register(view : View)
    {
        startActivity(Intent(this,RegistrarseActivity::class.java))
    }

    fun Login(view : View)
    {
        loginUser()
    }

    private fun loginUser() {
        val usuario: String = txtUsuario.text.toString()
        val contrasena: String = txtContrasena.text.toString()

        if (usuario.isNotEmpty() && contrasena.isNotEmpty()) {
            progressBar.visibility = View.VISIBLE

            if (android.util.Patterns.EMAIL_ADDRESS.matcher(usuario).matches()) {

                if (contrasena.length >= 5) {

                    auth.signInWithEmailAndPassword(usuario, contrasena)
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                val currentUser = FirebaseAuth.getInstance().currentUser
                                if (currentUser != null) {
                                    val userId = currentUser.uid
                                    val usuariosRef =
                                        FirebaseFirestore.getInstance().collection("usuarios")
                                            .document(userId)

                                    usuariosRef.get()
                                        .addOnSuccessListener { document ->
                                            if (document != null && document.exists()) {
                                                val rol = document.getString("rol")
                                                // Redirigir según el rol obtenido
                                                when (rol) {
                                                    "administrador" -> ingresarAdmin()
                                                    "cliente" -> ingresarUsu()
                                                    else -> {
                                                        // Manejar otros roles o situaciones
                                                        Toast.makeText(
                                                            this,
                                                            "Rol desconocido",
                                                            Toast.LENGTH_LONG
                                                        ).show()
                                                    }
                                                }
                                            }
                                        }
                                        .addOnFailureListener { exception ->
                                            // Manejar el fallo al obtener la información del usuario
                                            Toast.makeText(
                                                this,
                                                "Error al obtener información del usuario",
                                                Toast.LENGTH_LONG
                                            ).show()
                                        }
                                }
                            } else {
                                // Manejar fallos en el inicio de sesión
                                Toast.makeText(this, "Error al iniciar sesión", Toast.LENGTH_LONG)
                                    .show()
                                progressBar.visibility = View.GONE
                            }

                        }
                }
                else{

                    txtContrasena.error = "Contraseña invalida! "
                    mostrarAlerta("Por favor, Ingrese una contraseña valida")
                    progressBar.visibility = View.GONE
                }
            }
            else{
                txtUsuario.error = "Correo electrónico no válido"
                mostrarAlerta("Por favor, ingrese un formato correcto para el correo")
                progressBar.visibility = View.GONE
            }
        }
        else{
            // Mostrar un mensaje si algún campo está vacío
            mostrarAlerta("Por favor, complete todos los campos")
            progressBar.visibility = View.GONE
        }
    }


    private fun obtenerRolYRedirigir() {
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            val userId = currentUser.uid
            val usuariosRef = FirebaseFirestore.getInstance().collection("usuarios").document(userId)

            usuariosRef.get()
                .addOnSuccessListener { document ->
                    if (document != null && document.exists()) {
                        val rol = document.getString("rol")
                        // Redirigir a las pantallas según el rol

                        if (rol == "administrador") {
                            ingresarAdmin()
                        } else if (rol == "cliente") {
                            ingresarUsu()
                        }

                    }
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(this,"Error al iniciar", Toast.LENGTH_LONG).show()
                }
        }
    }


    private fun ingresarUsu(){
        startActivity(Intent(this,MenuPrincipalActivity ::class.java))
    }

    private fun ingresarAdmin(){
        startActivity(Intent(this,PrincipalAdminActivity ::class.java))
    }

    private fun mostrarAlerta(mensaje: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Advertencia")
        builder.setMessage(mensaje)
        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss() // Cierra el diálogo
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }



}