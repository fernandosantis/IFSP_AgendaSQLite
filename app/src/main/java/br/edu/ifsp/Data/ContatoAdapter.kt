package br.edu.ifsp.Data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifsp.Model.Contato
import br.edu.ifsp.R

class ContatoAdapter(val listaContatos:ArrayList<Contato>):RecyclerView.Adapter<ContatoAdapter.ContatoViewHolder> () {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContatoAdapter.ContatoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contato, parent, false)
        return ContatoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContatoAdapter.ContatoViewHolder, position: Int) {
        holder.vhNome.text = listaContatos[position].nome
        holder.vhFone.text = listaContatos[position].fone
    }

    override fun getItemCount(): Int {
        return listaContatos.size
    }

    // Suporte (Holder) para manter a referencia da View, no caso Item_Contato
    inner class ContatoViewHolder(view: View):RecyclerView.ViewHolder(view)
    {
        val vhNome = view.findViewById<TextView>(R.id.tv_Nome)
        val vhFone = view.findViewById<TextView>(R.id.tv_Fone)
    }

}