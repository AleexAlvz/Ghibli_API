package br.com.alexalves.ghibliapi.model

import android.content.Context
import java.io.Serializable

class Filme(
    val id: String,
    val title: String,
    val original_title_romanised: String,
    val description: String,
    val director: String,
    val producer: String,
    val release_date: String,
    val running_time: String,
    val rt_score: String,
    val people: ArrayList<String>
): Serializable {

}