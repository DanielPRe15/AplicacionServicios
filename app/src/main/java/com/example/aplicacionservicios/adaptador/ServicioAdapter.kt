package com.example.aplicacionservicios.adaptador

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.aplicacionservicios.R
import com.example.aplicacionservicios.ServicioElectricistaActivity
import com.example.aplicacionservicios.ServicioEnfermeriaActivity
import com.example.aplicacionservicios.ServicioHogarActivity
import com.example.aplicacionservicios.ServicioMecanicoActivity
import com.example.aplicacionservicios.ServicioPlomeriaActivity
import com.example.aplicacionservicios.ServicioTecnicoActivity
import com.example.aplicacionservicios.entidad.Servicio
import com.example.aplicacionservicios.utils.appConfig

class ServicioAdapter(var data:ArrayList<Servicio>): RecyclerView.Adapter<ViewServicio>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewServicio {
        //inflar
        var vista =
            LayoutInflater.from(parent.context).inflate(R.layout.list_element, parent, false)
        return ViewServicio(vista)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    //2
    override fun onBindViewHolder(holder: ViewServicio, position: Int) {
       // holder.tvServCodi.text=data.get(position).codigo.toString()
        holder.tvServicio.text = data.get(position).nombre
        //holder.tvServTraba.text=data.get(position).codigoTrabajador.toString()

        //contexto de ViewDocente
        var contexto: Context = holder.itemView.context
        //identificador para la IMG
        var IMG = -1
        IMG = contexto.resources.getIdentifier(
            data.get(position).foto,
            "drawable",
            contexto.packageName
        )
        holder.imgFoto.setImageResource(IMG)

        //asignar evento click al objeto holder
       /* holder.itemView.setOnClickListener {
            var intent=Intent(appConfig.CONTEXT,ServicioMecanicoActivity::class.java)
            intent.putExtra("servicio",data.get(position))
            ContextCompat.startActivity(appConfig.CONTEXT,intent,null)

        }*/

        holder.itemView.setOnClickListener {
            val servicio = data.get(position)

            var intent: Intent? = null

            when (servicio.nombre) {
                "Servicio Tecnico" -> {
                    intent = Intent(appConfig.CONTEXT, ServicioTecnicoActivity::class.java)
                    intent.putExtra("servicio", servicio)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    ContextCompat.startActivity(appConfig.CONTEXT, intent, null)
                }

                "Plomeria" -> {
                    intent = Intent(appConfig.CONTEXT, ServicioPlomeriaActivity::class.java)
                    intent.putExtra("servicio", servicio)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    ContextCompat.startActivity(appConfig.CONTEXT, intent, null)
                }

                "Electricista" -> {
                    intent = Intent(appConfig.CONTEXT, ServicioElectricistaActivity::class.java)
                    intent.putExtra("servicio", servicio)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    ContextCompat.startActivity(appConfig.CONTEXT, intent, null)

                }

                "Limpieza del hogar" -> {
                    intent = Intent(appConfig.CONTEXT, ServicioHogarActivity::class.java)
                    intent.putExtra("servicio", servicio)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    ContextCompat.startActivity(appConfig.CONTEXT, intent, null)
                }

                "Mecanico" -> {
                    intent = Intent(appConfig.CONTEXT, ServicioMecanicoActivity::class.java)
                    intent.putExtra("servicio", servicio)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    ContextCompat.startActivity(appConfig.CONTEXT, intent, null)
                }

                "Enfermeria" -> {
                    intent = Intent(appConfig.CONTEXT, ServicioEnfermeriaActivity::class.java)
                    intent.putExtra("servicio", servicio)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    ContextCompat.startActivity(appConfig.CONTEXT, intent, null)
                }
            }


        }
    }

}


private fun Intent.putExtra(s: String, get: Servicio) {

}
