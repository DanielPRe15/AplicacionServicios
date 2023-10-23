package com.example.aplicacionservicios.adaptador

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.aplicacionservicios.R
import com.example.aplicacionservicios.entidad.Servicio
import com.example.aplicacionservicios.utils.appConfig

class ServicioAdapter(var data:ArrayList<Servicio>): RecyclerView.Adapter<ViewServicio>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewServicio {
        //inflar
        var vista= LayoutInflater.from(parent.context).inflate(R.layout.list_element,parent,false)
        return ViewServicio(vista)
    }
    override fun getItemCount(): Int {
        return data.size
    }
    //2
    override fun onBindViewHolder(holder: ViewServicio, position: Int) {
        holder.tvServicio.text=data.get(position).nombre

        //contexto de ViewDocente
        var contexto:Context=holder.itemView.context
        //identificador para la IMG
        var IMG=-1
        IMG=contexto.resources.getIdentifier(data.get(position).foto,"drawable",contexto.packageName)
        holder.imgFoto.setImageResource(IMG)
        //asignar evento click al objeto holder


    }
}