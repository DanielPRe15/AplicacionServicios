package com.example.aplicacionservicios.adaptador

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aplicacionservicios.R

class ViewPedido1(item: View): RecyclerView.ViewHolder(item)  {
    var tvPed1Codigo: TextView
    var tvPed1Cliente: TextView
   // var tvPed1TecnicoTipo: TextView
    var tvPed1Direccion: TextView


    init {
        tvPed1Codigo = item.findViewById(R.id.tvPed1Codigo)
        tvPed1Cliente = item.findViewById(R.id.tvPed1Cliente)
        //tvPed1TecnicoTipo = item.findViewById(R.id.tvPed1TecnicoTipo)
        tvPed1Direccion = item.findViewById(R.id.tvPed1Direccion)
    }

}