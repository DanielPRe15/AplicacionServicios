package com.example.aplicacionservicios

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class OlvidarContrasenaActivity : AppCompatActivity() {

    private lateinit var txtEmail : TextInputEditText
    private lateinit var auth : FirebaseAuth
    private lateinit var progressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_olvidar_contrasena)

        txtEmail = findViewById(R.id.txtEmail)
        auth = FirebaseAuth.getInstance()
        progressBar = findViewById(R.id.progressBar3)
    }

    fun send(view: View){

        val email = txtEmail.text.toString()
        if(!TextUtils.isEmpty(email)){
            auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(this){
                    task ->

                    if(task.isSuccessful){
                        progressBar.visibility=View.VISIBLE
                        mostrarAlerta("Correo Enviado Correctamente")

                    }
                    else {
                        mostrarAlerta("Error al Enviar Correo")

                    }
                }
        }
    }

    private fun Pass()
    {
        startActivity(Intent(this,MainActivity ::class.java))
    }


    private fun mostrarAlerta(mensaje: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Éxito")
        builder.setMessage(mensaje)
        builder.setPositiveButton("OK") { dialog, _ ->
            Pass()
            dialog.dismiss() // Cierra el diálogo
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}