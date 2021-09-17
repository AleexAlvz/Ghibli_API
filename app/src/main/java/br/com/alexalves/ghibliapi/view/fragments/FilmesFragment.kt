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
import br.com.alexalves.ghibliapi.view.adapter.FilmeAdapter
import br.com.alexalves.ghibliapi.viewmodel.FilmesViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.androidx.viewmodel.ext.android.viewModel

class FilmesFragment : Fragment() {

    private val filmesViewModel: FilmesViewModel by viewModel()
    private lateinit var inflatedView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        inflatedView = inflater.inflate(R.layout.fragment_filmes, container, false)
        configuraFilmesFragment()
        configuraFilmesObserver()
        filmesViewModel.buscaFilmes()

        return inflatedView
    }

    private fun configuraFilmesFragment() {
        val bottomNavigationView = activity?.findViewById<BottomNavigationView>(R.id.main_activity_bottom_navigation_view)
        bottomNavigationView?.visibility = View.VISIBLE
    }

    private fun configuraFilmesObserver() {
        filmesViewModel.listaFilmes.observe(viewLifecycleOwner, Observer { filmes ->
            if (filmes != null) {
                configuraRecyclerView(filmes)
            }
        })
    }

    private fun configuraRecyclerView(filmes: List<Filme>) {
        val recyclerView =
            inflatedView.findViewById<RecyclerView>(R.id.fragment_filmes_recycler_view)
        val adapter = FilmeAdapter(filmes, requireContext(), this::startDetalhesFilmeFragment)
        adapter.notifyDataSetChanged()
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
    }

    private fun startDetalhesFilmeFragment(filme: Filme) {
        val bundle = Bundle()
        bundle.putSerializable("filme", filme)
        val detalhesFilmeFragment = DetalhesFilmeFragment()
        detalhesFilmeFragment.arguments = bundle
        activity?.supportFragmentManager?.let {
            it.beginTransaction()
                .replace(R.id.main_activity_container_fragment, detalhesFilmeFragment, null)
                .commit()
        }
    }

}
