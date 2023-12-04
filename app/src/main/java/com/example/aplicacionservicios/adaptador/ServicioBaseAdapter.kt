package com.example.aplicacionservicios.adaptador

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aplicacionservicios.R
import com.example.aplicacionservicios.entidad.Servicio
import com.example.aplicacionservicios.utils.appConfig

class ServicioBaseAdapter(var data:ArrayList<Servicio>): RecyclerView.Adapter<ViewServicioBase>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewServicioBase {
        //inflar
        var vista= LayoutInflater.from(parent.context).inflate(R.layout.item_servicio,parent,false)
        return ViewServicioBase(vista)
    }
    override fun getItemCount(): Int {
        return data.size
    }
    //2
    override fun onBindViewHolder(holder: ViewServicioBase, position: Int) {
        holder.tvServBaseCodi.text=data[position].codigo.toString()
        holder.tvServBaseNomb.text=data[position].nombre

        val servicio = data[position]
        var contexto: Context =holder.itemView.context
        val rutaImagen = servicio.foto

        if (rutaImagen.startsWith("content://")) {
            try {
                Glide.with(contexto)
                    .load(Uri.parse(rutaImagen))
                    .placeholder(R.drawable.cancelar)
                    .error(R.drawable.cancelar)
                    .into(holder.imgServBaseFoto)
            } catch (e: Exception) {
                e.printStackTrace()
                holder.imgServBaseFoto.setImageResource(R.drawable.cancelar)
            }
        } else {
            val resourceID =
                contexto.resources.getIdentifier(rutaImagen, "drawable", contexto.packageName)
            Glide.with(contexto)
                .load(resourceID)
                .placeholder(R.drawable.cancelar)
                .error(R.drawable.cancelar)
                .into(holder.imgServBaseFoto)
        }

    }
}