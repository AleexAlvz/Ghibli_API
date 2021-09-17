package br.com.alexalves.ghibliapi.view.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.alexalves.ghibliapi.R
import br.com.alexalves.ghibliapi.model.Filme
import br.com.alexalves.ghibliapi.model.Personagem
import br.com.alexalves.ghibliapi.view.adapter.PersonagemAdapter
import br.com.alexalves.ghibliapi.viewmodel.PersonagensViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.android.ext.android.inject

class DetalhesFilmeFragment : Fragment() {

    val personagensViewModel: PersonagensViewModel by inject()
    private lateinit var inflatedView: View
    private lateinit var filme: Filme

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inflatedView = inflater.inflate(R.layout.detalhes_filme_fragment, container, false)
        configuraFilme()
        configuraCampos()
        configuraRecyclerViewPersonagens()

        return inflatedView
    }

    private fun configuraRecyclerViewPersonagens() {
        val recyclerView = inflatedView.findViewById<RecyclerView>(R.id.detalhes_filme_recyclerview_personagens)
        personagensViewModel.buscaPersonagensPorFilme(filme)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        personagensViewModel.liveDataListaPersonagemPorFilme.observe(viewLifecycleOwner, Observer { personagens ->
            if (personagens!= null){
                val adapter = PersonagemAdapter( personagens, requireContext(), this::startDetalhesPersonagemFragment)
                adapter.notifyDataSetChanged()
                recyclerView.adapter = adapter
            }
        })
    }

    private fun startDetalhesPersonagemFragment(personagem: Personagem) {
        val bundle = Bundle()
        bundle.putSerializable("personagem", personagem)
        val detalhesPersonagemFragment = DetalhesPersonagemFragment()
        detalhesPersonagemFragment.arguments = bundle
        activity?.supportFragmentManager?.let {
            it.beginTransaction()
                .replace(R.id.main_activity_container_fragment, detalhesPersonagemFragment, null)
                .commit()
        }
    }


    @SuppressLint("SetTextI18n")
    private fun configuraCampos() {

        val nomeImagem = filme.title.replace(" ", "_").replace("'", "").lowercase() + "_poster"
        val idImage = resources.getIdentifier(nomeImagem, "drawable", requireContext().packageName)
        val imageView = inflatedView.findViewById<ImageView>(R.id.detalhe_filme_image_view)
        imageView.setImageResource(idImage)

        val textTitulo = inflatedView.findViewById<TextView>(R.id.detalhe_filme_text_titulo)
        textTitulo.setText(filme.title)

        val textTituloRoma =
            inflatedView.findViewById<TextView>(R.id.detalhe_filme_text_titulo_roma)
        textTituloRoma.setText(filme.original_title_romanised)

        val textDescricao = inflatedView.findViewById<TextView>(R.id.detalhe_filme_text_descricao)
        textDescricao.setText(filme.description)

        val textDiretor = inflatedView.findViewById<TextView>(R.id.detalhe_filme_text_diretor)
        textDiretor.setText("Diretor: ${filme.director}")

        val textProdutor = inflatedView.findViewById<TextView>(R.id.detalhe_filme_text_produtor)
        textProdutor.setText("Produtor: ${filme.producer}")

        val textDataLancamento =
            inflatedView.findViewById<TextView>(R.id.detalhe_filme_text_lancamento)
        textDataLancamento.setText("Ano de lançamento: ${filme.release_date}")

        val textDuracao = inflatedView.findViewById<TextView>(R.id.detalhe_filme_text_duracao)
        textDuracao.setText("Duração: ${filme.running_time} minutos")

        val textNota = inflatedView.findViewById<TextView>(R.id.detalhe_filme_text_nota)
        textNota.setText("Rotten Tomato Score: ${filme.rt_score}/100")
    }

    private fun configuraFilme() {
        filme = arguments?.get("filme") as Filme
    }

}
