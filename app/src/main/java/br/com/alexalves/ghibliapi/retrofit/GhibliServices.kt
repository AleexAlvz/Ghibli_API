package br.com.alexalves.ghibliapi.retrofit

import br.com.alexalves.ghibliapi.model.Filme
import br.com.alexalves.ghibliapi.model.Personagem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GhibliServices {

    @GET("films")
    fun getFilmes(): Call<List<Filme>>

    @GET("people")
    fun getPersonagens(): Call<List<Personagem>>

    @GET("people/{id}")
    fun getPersonagensPorId(@Path("id") id: String): Call<Personagem>

}