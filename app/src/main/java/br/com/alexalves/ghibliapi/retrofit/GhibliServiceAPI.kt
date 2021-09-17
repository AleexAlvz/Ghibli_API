package br.com.alexalves.ghibliapi.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GhibliServiceAPI {

    fun getGhibliService(): GhibliServices {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://ghibliapi.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(GhibliServices::class.java)
    }
}