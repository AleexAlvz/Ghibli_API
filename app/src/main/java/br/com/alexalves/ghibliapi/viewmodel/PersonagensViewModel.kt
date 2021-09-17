package br.com.alexalves.ghibliapi.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.alexalves.ghibliapi.model.Filme
import br.com.alexalves.ghibliapi.model.Personagem
import br.com.alexalves.ghibliapi.retrofit.GhibliServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class PersonagensViewModel(val service: GhibliServices) : ViewModel() {

    val listPersonagens = MutableLiveData<List<Personagem>>()
    val liveDataListaPersonagemPorFilme = MutableLiveData<List<Personagem>>()

    fun buscaPersonagens() {
        CoroutineScope(IO).launch {
            val call = service.getPersonagens()
            try {
                val personagens = call.execute().body()
                withContext(Dispatchers.Main) {
                    if (personagens!= null){
                        listPersonagens.value = personagens
                    }
                }
            } catch (erro: IOException) {
                erro.printStackTrace()
            }
        }
    }

    fun buscaPersonagensPorFilme(filme: Filme) {
        liveDataListaPersonagemPorFilme.value = null
        val listaPersonagemPorFilme = ArrayList<Personagem>()
        CoroutineScope(IO).launch {
            for (personagemUrl in filme.people) {

                val personagemId = personagemUrl.replace("https://ghibliapi.herokuapp.com/people/","")
                if (!personagemId.equals("")){
                    val call = service.getPersonagensPorId(personagemId)
                    try {
                        val personagem = call.execute().body()
                        withContext(Dispatchers.Main) {
                            if (personagem!=null){
                                if (personagem is Personagem){
                                    listaPersonagemPorFilme.add(personagem)
                                    liveDataListaPersonagemPorFilme.value = listaPersonagemPorFilme
                                }
                            }
                        }
                    } catch (erro: IOException) {
                        erro.printStackTrace()
                    }
                }
            }
        }
    }
}