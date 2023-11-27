package com.example.aplicacionservicios.adaptador

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
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

class ServicioAdapter(private var originalData: ArrayList<Servicio>,
                      private val itemClickListener: OnServicioItemClickListener?=null
): RecyclerView.Adapter<ViewServicio>(), Filterable {
    interface OnServicioItemClickListener {
        fun onServicioItemClick(servicio: Servicio)
    }

    private var filteredData: ArrayList<Servicio> = originalData



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewServicio {
        //inflar
        var vista =
            LayoutInflater.from(parent.context).inflate(R.layout.list_element, parent, false)
        return ViewServicio(vista)
    }

    override fun getItemCount(): Int {
        return filteredData.size
    }

    //2
    override fun onBindViewHolder(holder: ViewServicio, position: Int) {
        holder.tvServicio.text = filteredData[position].nombre

        //contexto de ViewDocente
        var contexto: Context = holder.itemView.context
        //identificador para la IMG
        var IMG = -1
        IMG = contexto.resources.getIdentifier(
            filteredData[position].foto,
            "drawable",
            contexto.packageName
        )
        holder.imgFoto.setImageResource(IMG)

        //asignar evento click al objeto holder
        /*holder.itemView.setOnClickListener {
            var intent=Intent(appConfig.CONTEXT,ServicioMecanicoActivity::class.java)
            intent.putExtra("servicio",data.get(position))
            ContextCompat.startActivity(appConfig.CONTEXT,intent,null)

        }*/

        holder.itemView.setOnClickListener {
            val servicio = filteredData[position]
            this.itemClickListener?.onServicioItemClick(servicio)

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

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filterResults = FilterResults()
                val queryString = constraint?.toString()?.lowercase()

                if (queryString.isNullOrEmpty()) {
                    filteredData = originalData
                } else {
                    val filteredList = ArrayList<Servicio>()
                    for (servicio in originalData) {
                        if (servicio.nombre.lowercase().contains(queryString)) {
                            filteredList.add(servicio)
                        }
                    }
                    filteredData = filteredList
                }

                filterResults.values = filteredData
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredData = results?.values as ArrayList<Servicio>? ?: ArrayList()
                notifyDataSetChanged()
            }
        }
    }
}



private fun Intent.putExtra(s: String, get: Servicio) {

}