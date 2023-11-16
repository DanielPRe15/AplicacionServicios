package com.example.aplicacionservicios

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

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

    private fun loginUser()
    {
        val usuario : String = txtUsuario.text.toString()
        val contrasena : String = txtContrasena.text.toString()

        if(!TextUtils.isEmpty(usuario) && !TextUtils.isEmpty(contrasena)){
            progressBar.visibility = View.VISIBLE

            auth.signInWithEmailAndPassword(usuario, contrasena)
                .addOnCompleteListener(this ){
                    task ->
                    if(task.isSuccessful){
                        ingresar()
                    }
                    else{
                        Toast.makeText(this,"Error al Iniciar sesión", Toast.LENGTH_LONG).show()
                    }
                }
        }

    }

    private fun ingresar(){
        startActivity(Intent(this,MenuPrincipalActivity ::class.java))
    }





}