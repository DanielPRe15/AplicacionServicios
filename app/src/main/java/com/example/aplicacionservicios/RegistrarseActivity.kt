package com.example.aplicacionservicios

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.Task
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.lang.ref.PhantomReference

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

    private fun createNewAccount()
    {
        val nombre : String = txtNombreRE.text.toString()
        val apellido : String = txtApellidoRE.text.toString()
        val correo : String = txtCorreoRE.text.toString()
        val contrasena : String = txtContrasena.text.toString()

        if(!TextUtils.isEmpty(nombre) && !TextUtils.isEmpty(apellido) && !TextUtils.isEmpty(correo) && !TextUtils.isEmpty(contrasena))
        {
            progressBar.visibility = View.VISIBLE

            auth.createUserWithEmailAndPassword(correo,contrasena)
                .addOnCompleteListener(this){
                    task ->
                    if(task.isComplete){
                        val user : FirebaseUser? = auth.currentUser
                        verificarEmail(user)

                        val userBD = dbReference.child(user?.uid.toString())
                        userBD.child("Name").setValue(nombre)
                        userBD.child("LastName").setValue(apellido)
                        registrado()
                    }
                }
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
                    Toast.makeText(this,"Correo Enviado",Toast.LENGTH_LONG).show()
                }
                else
                {
                    Toast.makeText(this,"Error al Enviar Correo",Toast.LENGTH_LONG).show()
                }

            }
    }



}