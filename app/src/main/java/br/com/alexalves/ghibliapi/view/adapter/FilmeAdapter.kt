package br.com.alexalves.ghibliapi.view.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.alexalves.ghibliapi.R
import br.com.alexalves.ghibliapi.model.Filme
import com.bumptech.glide.Glide

class FilmeAdapter(
    private val filmeList: List<Filme>,
    private val context: Context,
    private val onItemClicked: (filme: Filme) -> Unit

) : RecyclerView.Adapter<FilmeAdapter.FilmeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmeViewHolder {
        val layout = LayoutInflater
            .from(context).inflate(R.layout.item_recycler_view, parent, false)
        return FilmeViewHolder(layout)
    }

    override fun onBindViewHolder(holder: FilmeViewHolder, position: Int) {
        holder.itemView.setOnClickListener { onItemClicked.invoke(filmeList[position]) }
        holder.titulo.setText(filmeList[position].title)
        holder.descricao.setText(filmeList[position].description)
        val nomeImagem = filmeList[position].title.replace(" ","_").replace("'","").lowercase()+"_poster"
        val idImage = context.resources.getIdentifier(nomeImagem, "drawable", context.packageName)
        Glide.with(context).load(idImage).into(holder.imageView)

    }

    override fun getItemCount(): Int {
        return filmeList.size
    }

    class FilmeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titulo = itemView.findViewById<TextView>(R.id.item_text_titulo)
        val descricao = itemView.findViewById<TextView>(R.id.item_text_descricao)
        val imageView = itemView.findViewById<ImageView>(R.id.item_image)
    }
}
