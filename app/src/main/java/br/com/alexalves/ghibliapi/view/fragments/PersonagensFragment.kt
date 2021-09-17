package br.com.alexalves.ghibliapi.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

class PersonagensFragment : Fragment() {

    val personagensViewModel: PersonagensViewModel by inject()

    private lateinit var inflatedView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        inflatedView = inflater.inflate(R.layout.fragment_personagens, container, false)
        configuraObserverPersonagens()
        personagensViewModel.buscaPersonagens()
        return inflatedView
    }

    private fun configuraObserverPersonagens() {
        personagensViewModel.listPersonagens.observe(viewLifecycleOwner, Observer { personagens ->
            if (personagens != null) {
                configuraRecyclerView(personagens)
            }
        })
    }

    private fun configuraRecyclerView(personagens: List<Personagem>) {
        val recyclerView = inflatedView.findViewById<RecyclerView>(R.id.fragment_personagens_recycler_view)
        val adapter = PersonagemAdapter( personagens, requireContext(), this::startDetalhesPersonagemFragment)
        adapter.notifyDataSetChanged()
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
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

}
