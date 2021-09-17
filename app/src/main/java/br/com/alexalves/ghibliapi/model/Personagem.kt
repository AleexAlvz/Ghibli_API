package br.com.alexalves.ghibliapi.model

import android.content.Context
import java.io.Serializable

class Personagem(
    val id: String,
    val name: String,
    val gender: String,
    val age: String,
    val films: List<String>,
    val species: String,
): Serializable {

    fun getTumbNailName(): String {
        return this.name.lowercase().replace(" ", "_").replace("'","")+"poster"
    }

    fun getTumbNail(context: Context): Int{
        return context.resources.getIdentifier(getTumbNailName(), "drawable", context.packageName)
    }

}
