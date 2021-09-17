package br.com.alexalves.ghibliapi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.alexalves.ghibliapi.model.Filme
import br.com.alexalves.ghibliapi.retrofit.GhibliServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class FilmesViewModel(val service: GhibliServices): ViewModel() {

    val listaFilmes = MutableLiveData<List<Filme>>()

    fun buscaFilmes(){
        CoroutineScope(IO).launch {
            val call = service.getFilmes()
            try {
                val filmes = call.execute().body()
                withContext(Main){
                    listaFilmes.value = filmes
                }
            } catch (erro: IOException){
                erro.printStackTrace()
            }
        }
    }
}