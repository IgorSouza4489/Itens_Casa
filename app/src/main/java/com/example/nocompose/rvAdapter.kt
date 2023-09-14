import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nocompose.R
import com.example.nocompose.modelitem

class rvAdapter(private val items: ArrayList<modelitem>, val listaClickInterface: ListaClickInterface,) : RecyclerView.Adapter<rvAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.homerecyclerviewitem, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.objeto.text = item.objeto
        holder.quantidade.text = item.preco.toString()

        // Configurar o CheckBox com base no valor da propriedade 'check' do modelo
        holder.checkbox.isChecked = item.check ?: false

        // Definir um ouvinte de clique para o CheckBox
        holder.checkbox.setOnClickListener {
            // Inverter o valor da propriedade 'check' do modelo quando o CheckBox for clicado
            item.check = holder.checkbox.isChecked
            listaClickInterface.onListaClick(items[position])

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateList(newList: List<modelitem>) {
        items.clear()
        items.addAll(newList)
        items.sortedByDescending { it.objeto }
        notifyDataSetChanged()
    }


    interface ListaClickInterface {
        fun onListaClick(modelitem: modelitem)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val objeto: TextView = itemView.findViewById(R.id.nameTextView)
        val quantidade: TextView = itemView.findViewById(R.id.qtntv)
        val checkbox: CheckBox = itemView.findViewById(R.id.checkbox)
    }
}
