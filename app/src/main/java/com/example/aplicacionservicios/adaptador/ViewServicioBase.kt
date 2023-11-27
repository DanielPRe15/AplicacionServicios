package com.example.aplicacionservicios.adaptador

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aplicacionservicios.R

class ViewServicioBase(item:View):RecyclerView.ViewHolder(item) {
    //declarar 4 atributos (TextView)
    var tvServBaseCodi: TextView
    var tvServBaseNomb: TextView
    //var tvServTraba: TextView
    var imgServBaseFoto: ImageView

    //refrenciar atributos con los controles de la pantalla
    init {
        tvServBaseCodi = item.findViewById(R.id.tvServBaseCodi)
        tvServBaseNomb = item.findViewById(R.id.tvServBaseNomb)
        //tvServTraba = item.findViewById(R.id.tvServTraba)
        imgServBaseFoto = item.findViewById(R.id.imgServBaseFoto)

    }
}