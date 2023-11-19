package com.example.aplicacionservicios.adaptador

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.aplicacionservicios.R
import com.example.aplicacionservicios.TrabajadorEditarActivity
import com.example.aplicacionservicios.entidad.Trabajador
import com.example.aplicacionservicios.utils.appConfig

class TrabajadorAdapter(var data:ArrayList<Trabajador>): RecyclerView.Adapter<ViewTrabajador>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewTrabajador {
        //inflar
        var vista= LayoutInflater.from(parent.context).inflate(R.layout.trabajador_item,parent,false)
        return ViewTrabajador(vista)
    }
    override fun getItemCount(): Int {
        return data.size
    }
    //2
    override fun onBindViewHolder(holder: ViewTrabajador, position: Int) {
        holder.tvTrabCodigo.text=data.get(position).codigo.toString()
        holder.tvTrabNombre.text=data.get(position).nombre
        holder.tvTrabApellido.text=data.get(position).apellido
        holder.tvTrabTelefono.text=data.get(position).telefono
        holder.tvTrabEdad.text= data.get(position).edad.toString()
        //contexto de ViewDocente
        var contexto: Context =holder.itemView.context
        //identificador para la IMG
        var IMG=-1
        IMG=contexto.resources.getIdentifier(data.get(position).foto,"drawable",contexto.packageName)
        holder.imgTrabFoto.setImageResource(IMG)
        //asignar evento click al objeto holder
        holder.itemView.setOnClickListener{
            var intent = Intent(appConfig.CONTEXT, TrabajadorEditarActivity::class.java)
            intent.putExtra("trabajador",data.get(position))
            ContextCompat.startActivity(appConfig.CONTEXT,intent,null)
        }
    }
}

private fun Intent.putExtra(s: String, get: Trabajador) {

}
