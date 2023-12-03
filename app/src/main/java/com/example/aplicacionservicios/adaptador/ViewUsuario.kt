package com.example.aplicacionservicios.adaptador

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aplicacionservicios.R

class ViewUsuario(item: View): RecyclerView.ViewHolder(item)  {
    var tvUsuarioNombre: TextView
    var tvUsuarioApellido: TextView
    var tvUsuarioRol: TextView


    init {
        tvUsuarioNombre = item.findViewById(R.id.tvUsuarioNombre)
        tvUsuarioApellido = item.findViewById(R.id.tvUsuarioApellido)
        tvUsuarioRol = item.findViewById(R.id.tvUsuarioRol)
    }

}