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
import com.example.aplicacionservicios.TrabajadorEditarActivity
import com.example.aplicacionservicios.entidad.Trabajador
import com.example.aplicacionservicios.utils.appConfig

class TrabajadorAdapter(var data:ArrayList<Trabajador>): RecyclerView.Adapter<ViewTrabajador>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewTrabajador {
        //inflar
        var vista =
            LayoutInflater.from(parent.context).inflate(R.layout.trabajador_item, parent, false)
        return ViewTrabajador(vista)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    //2
    override fun onBindViewHolder(holder: ViewTrabajador, position: Int) {
        holder.tvTrabCodigo.text = data[position].codigo.toString()
        holder.tvTrabNombre.text = data[position].nombre
        holder.tvTrabApellido.text = data[position].apellido
        holder.tvTrabTelefono.text = data[position].telefono
        holder.tvTrabEdad.text = data[position].edad.toString()

        val trabajador = data[position]
        val contexto: Context = holder.itemView.context
        val rutaImagen = trabajador.foto

        if (rutaImagen.startsWith("content://")) {
            try {
                Glide.with(contexto)
                    .load(Uri.parse(rutaImagen))
                    .placeholder(R.drawable.cancelar)
                    .error(R.drawable.cancelar)
                    .into(holder.imgTrabFoto)
            } catch (e: Exception) {
                e.printStackTrace()
                holder.imgTrabFoto.setImageResource(R.drawable.cancelar)
            }
        } else {
            val resourceID =
                contexto.resources.getIdentifier(rutaImagen, "drawable", contexto.packageName)
            Glide.with(contexto)
                .load(resourceID)
                .placeholder(R.drawable.cancelar)
                .error(R.drawable.cancelar)
                .into(holder.imgTrabFoto)
        }

        holder.itemView.setOnClickListener {
            val intent = Intent(appConfig.CONTEXT, TrabajadorEditarActivity::class.java)
            intent.putExtra("trabajador", data[position])
            holder.itemView.context.startActivity(intent)
        }
    }
}
private fun Intent.putExtra(s: String, get: Trabajador) {

}
