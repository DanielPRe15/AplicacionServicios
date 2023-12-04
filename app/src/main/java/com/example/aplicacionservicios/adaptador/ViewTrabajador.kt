package com.example.aplicacionservicios.adaptador

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aplicacionservicios.R

class ViewTrabajador(item: View): RecyclerView.ViewHolder(item)  {
    var tvTrabCodigo: TextView
    var tvTrabNombre: TextView
    var tvTrabApellido: TextView
    //var tvTrabServicio: TextView
    var tvTrabTelefono: TextView
    var tvTrabEdad: TextView
    var imgTrabFoto: ImageView


    init {
        tvTrabCodigo = item.findViewById(R.id.tvTrabCodigo)
        tvTrabNombre = item.findViewById(R.id.tvTrabNombre)
        tvTrabApellido = item.findViewById(R.id.tvTrabApellido)
       // tvTrabServicio = item.findViewById(R.id.tvTrabServicio)
        tvTrabTelefono = item.findViewById(R.id.tvTrabTelefono)
        tvTrabEdad = item.findViewById(R.id.tvTrabEdad)
        imgTrabFoto = item.findViewById(R.id.imgTrabFoto)

    }

}