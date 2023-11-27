package com.example.aplicacionservicios.adaptador

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aplicacionservicios.R

class ViewServicio(item:View):RecyclerView.ViewHolder(item) {
    //declarar 4 atributos (TextView)
   // var tvServCodi: TextView
    var tvServicio: TextView
    //var tvServTraba: TextView
    var imgFoto: ImageView

    //refrenciar atributos con los controles de la pantalla
    init {
        //tvServCodi = item.findViewById(R.id.tvServCodi)
        tvServicio = item.findViewById(R.id.tvServicio)
        //tvServTraba = item.findViewById(R.id.tvServTraba)
        imgFoto = item.findViewById(R.id.imgFoto)

    }
}