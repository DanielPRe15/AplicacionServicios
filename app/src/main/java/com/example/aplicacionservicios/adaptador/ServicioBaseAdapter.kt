package com.example.aplicacionservicios.adaptador

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.aplicacionservicios.Pedido1EditarActivity
import com.example.aplicacionservicios.R
import com.example.aplicacionservicios.ServicioBaseEditarActivity
import com.example.aplicacionservicios.TrabajadorEditarActivity
import com.example.aplicacionservicios.entidad.Servicio
import com.example.aplicacionservicios.entidad.ServicioTecnico
import com.example.aplicacionservicios.entidad.Trabajador
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
        holder.tvServBaseCodi.text=data.get(position).codigo.toString()
        holder.tvServBaseNomb.text=data.get(position).nombre

        var contexto: Context =holder.itemView.context

        var IMG=-1
        IMG=contexto.resources.getIdentifier(data.get(position).foto,"drawable",contexto.packageName)
        holder.imgServBaseFoto.setImageResource(IMG)
        //contexto de ViewDocente
        holder.itemView.setOnClickListener{
            var intent = Intent(appConfig.CONTEXT, ServicioBaseEditarActivity::class.java)
            intent.putExtra("servicio",data.get(position))
            ContextCompat.startActivity(appConfig.CONTEXT, intent, null)
        }
    }
}