package br.com.alexalves.ghibliapi.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.alexalves.ghibliapi.R
import br.com.alexalves.ghibliapi.model.Filme
import br.com.alexalves.ghibliapi.model.Personagem
import com.bumptech.glide.Glide

class PersonagemAdapter(val personagens: List<Personagem>,
                        val context: Context,
                        private val onItemClicked: (personagem: Personagem) -> Unit
) : RecyclerView.Adapter<PersonagemAdapter.PersonagemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonagemViewHolder {
        val layout = LayoutInflater.from(context).inflate(R.layout.item_recycler_view, parent, false)
        return PersonagemViewHolder(layout)
    }

    override fun onBindViewHolder(holder: PersonagemViewHolder, position: Int) {
        holder.itemView.setOnClickListener { onItemClicked.invoke(personagens[position]) }
        holder.titulo.setText(personagens[position].name)
        holder.descricao.setText(personagens[position].gender)
        val nomeImagem = personagens[position].name.replace(" ","_").replace("-","_").lowercase()
        val idImage = context.resources.getIdentifier(nomeImagem, "drawable", context.packageName)
        Glide.with(context).load(idImage).into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return personagens.size
    }

    class PersonagemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titulo = itemView.findViewById<TextView>(R.id.item_text_titulo)
        val descricao = itemView.findViewById<TextView>(R.id.item_text_descricao)
        val imageView = itemView.findViewById<ImageView>(R.id.item_image)
    }

}
