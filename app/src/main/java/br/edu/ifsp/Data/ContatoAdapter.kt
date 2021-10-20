package br.edu.ifsp.Data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifsp.Model.Contato
import br.edu.ifsp.R

class ContatoAdapter(val listaContatos: ArrayList<Contato>) : RecyclerView.Adapter<ContatoAdapter.ContatoViewHolder> (),
    Filterable {

    var listener: ContatoListener? = null

    var listaContatosFilterable = ArrayList<Contato>()

    init {
        this.listaContatosFilterable = listaContatos
    }


    fun setClickListener(listener: ContatoListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContatoAdapter.ContatoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contato, parent, false)
        return ContatoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContatoAdapter.ContatoViewHolder, position: Int) {
        holder.vhNome.text = listaContatosFilterable[position].nome
        holder.vhFone.text = listaContatosFilterable[position].fone
    }

    override fun getItemCount(): Int {
        return listaContatosFilterable.size
    }

    // Suporte (Holder) para manter a referencia da View, no caso Item_Contato
    inner class ContatoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val vhNome = view.findViewById<TextView>(R.id.tv_Nome)
        val vhFone = view.findViewById<TextView>(R.id.tv_Fone)

        init {
            view.setOnClickListener {
                listener?.onItemClick(adapterPosition)
            }
        }
    }

    // Interface
    interface ContatoListener {
        fun onItemClick(pos: Int)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty())
                    listaContatosFilterable = listaContatos
                else
                {
                    val resultList = ArrayList<Contato>()
                    for (contato in listaContatos)
                        if (contato.nome.lowercase().contains(constraint.toString().lowercase()))
                            resultList.add(contato)
                    listaContatosFilterable = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = listaContatosFilterable
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                listaContatosFilterable = results?.values as ArrayList<Contato>
                notifyDataSetChanged()
            }

        }
    }
}
