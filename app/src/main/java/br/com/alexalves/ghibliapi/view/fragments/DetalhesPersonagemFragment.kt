package br.com.alexalves.ghibliapi.view.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import br.com.alexalves.ghibliapi.R
import br.com.alexalves.ghibliapi.model.Personagem
import com.bumptech.glide.Glide

class DetalhesPersonagemFragment: Fragment() {

    private lateinit var inflatedView: View
    private lateinit var personagem: Personagem

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inflatedView = inflater.inflate(R.layout.detalhes_personagem_fragment, container, false)
        configuraPersonagem()
        configuraCampos()
        return inflatedView
    }

    @SuppressLint("SetTextI18n")
    private fun configuraCampos() {

        val nomeImagem = personagem.name.replace(" ","_").lowercase()
        val idImage = resources.getIdentifier(nomeImagem, "drawable", requireContext().packageName)
        val imageView = inflatedView.findViewById<ImageView>(R.id.detalhe_personagem_image_view)
        Glide.with(requireContext()).load(idImage).into(imageView)

        val textTitulo = inflatedView.findViewById<TextView>(R.id.detalhe_filme_text_nome)
        textTitulo.setText(personagem.name)

        val textGenero = inflatedView.findViewById<TextView>(R.id.detalhe_filme_text_titulo_genero)
        textGenero.setText("Genero: ${personagem.gender}")

        val textIdade = inflatedView.findViewById<TextView>(R.id.detalhe_filme_text_fixo_idade)
        textIdade.setText("Idade: ${personagem.age} anos")

    }

    private fun configuraPersonagem() {
        personagem = arguments?.get("personagem") as Personagem
    }

}
