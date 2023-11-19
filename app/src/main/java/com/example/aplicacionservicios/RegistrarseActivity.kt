package com.example.aplicacionservicios

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.regex.Pattern


class RegistrarseActivity  : AppCompatActivity(){

    private lateinit var txtNombreRE: TextInputEditText
    private lateinit var txtApellidoRE: TextInputEditText
    private lateinit var txtCorreoRE: TextInputEditText
    private lateinit var txtContrasena : TextInputEditText
    private lateinit var progressBar: ProgressBar
    private lateinit var dbReference: DatabaseReference
    private lateinit var database: FirebaseDatabase
    private lateinit var auth: FirebaseAuth




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrarse)



        txtNombreRE = findViewById(R.id.txtNombreRE)
        txtApellidoRE = findViewById(R.id.txtApellidoRE)
        txtCorreoRE = findViewById(R.id.txtCorreoRE)
        txtContrasena = findViewById(R.id.txtContrasena)


        progressBar = findViewById(R.id.progressBar)

        database = FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()

        dbReference = database.reference.child("User")



    }

    fun register(view: View)
    {
        createNewAccount()
    }

    private fun createNewAccount() {
        val nombre: String = txtNombreRE.text.toString().trim()
        val apellido: String = txtApellidoRE.text.toString().trim()
        val correo: String = txtCorreoRE.text.toString().trim()
        val contrasena: String = txtContrasena.text.toString().trim()
        val db = Firebase.firestore
        val collectionName = "usuarios"

        if (nombre.isNotEmpty() && apellido.isNotEmpty() && correo.isNotEmpty() && contrasena.isNotEmpty()) {
            // Validar el formato del correo electrónico
            if (android.util.Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
                progressBar.visibility = View.VISIBLE

                if (contrasena.length >= 5) {

                    auth.createUserWithEmailAndPassword(correo, contrasena)
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                val user: FirebaseUser? = auth.currentUser

                                // Verificar si el usuario existe y su email está verificado
                                verificarEmail(user)

                                // Obtener el UID del usuario creado
                                val uid = user?.uid

                                // Verificar si el UID no es nulo y luego guardar los datos en Firestore
                                if (uid != null) {
                                    val userDocument = db.collection(collectionName).document(uid)
                                    userDocument.set(
                                        mapOf(
                                            "nombre" to nombre,
                                            "apellido" to apellido,
                                            "rol" to "cliente"
                                        )
                                    ).addOnSuccessListener {
                                        registrado()

                                    }.addOnFailureListener { e ->
                                        // Manejar el fallo al crear el documento en Firestore
                                        Toast.makeText(
                                            this,
                                            "Error al crear documento en Firestore: ${e.message}",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                }

                            } else {
                                // Manejar el fallo en la creación de la cuenta de usuario
                                Toast.makeText(
                                    this,
                                    "Error al crear la cuenta: ${task.exception?.message}",
                                    Toast.LENGTH_LONG
                                ).show()
                                progressBar.visibility = View.GONE
                            }
                        }
                }
                else {
                    txtContrasena.error = "Contraseña invalida! "
                    mostrarAlerta("Por favor, Ingrese una contraseña valida, la contraseña debe ser mayor a 5 caracteres")
                    progressBar.visibility = View.GONE
                }

            } else {
                // Mostrar un mensaje de error si el correo electrónico no tiene un formato válido
                txtCorreoRE.error = "Correo electrónico no válido"
                mostrarAlerta("Por favor, ingrese un formato correcto para el correo")
                progressBar.visibility = View.GONE

            }
        } else {
            // Mostrar un mensaje si algún campo está vacío
            mostrarAlerta("Por favor, complete todos los campos")
            progressBar.visibility = View.GONE
        }
    }


    private fun registrado()
    {
        startActivity(Intent(this,MainActivity ::class.java))
    }

    private fun verificarEmail(user: FirebaseUser?)
    {
        user?.sendEmailVerification()
            ?.addOnCompleteListener(this){
                task ->
                if(task.isComplete)
                {
                    Toast.makeText(this, "Correo Enviado", Toast.LENGTH_LONG).show()


                }
                else
                {
                    Toast.makeText(this, "Error al enviar correo", Toast.LENGTH_LONG).show()


                }

            }
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