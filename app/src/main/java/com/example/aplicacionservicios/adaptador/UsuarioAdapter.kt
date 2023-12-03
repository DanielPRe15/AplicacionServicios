import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aplicacionservicios.R
import com.example.aplicacionservicios.entidad.Usuario2

class UsuarioAdapter(private val userList: List<Usuario2>) : RecyclerView.Adapter<UsuarioAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.usuario_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvUsuarioNombre: TextView = itemView.findViewById(R.id.tvUsuarioNombre)
        private val tvUsuarioApellido: TextView = itemView.findViewById(R.id.tvUsuarioApellido)
        private val tvUsuarioRol: TextView = itemView.findViewById(R.id.tvUsuarioRol)

        fun bind(usuario: Usuario2) {
            tvUsuarioNombre .text = "Nombre: ${usuario.nombre}"
            tvUsuarioApellido.text = "Apellido: ${usuario.apellido}"
            tvUsuarioRol.text = "Rol: ${usuario.rol}"
        }
    }
}
