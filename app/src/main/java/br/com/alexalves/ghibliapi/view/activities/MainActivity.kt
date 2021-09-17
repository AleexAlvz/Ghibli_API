package br.com.alexalves.ghibliapi.view.activities

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import br.com.alexalves.ghibliapi.R
import br.com.alexalves.ghibliapi.model.Filme
import br.com.alexalves.ghibliapi.view.fragments.DetalhesFilmeFragment
import br.com.alexalves.ghibliapi.view.fragments.FilmesFragment
import br.com.alexalves.ghibliapi.view.fragments.PersonagensFragment
import br.com.alexalves.ghibliapi.viewmodel.FilmesViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startFragment(FilmesFragment())
        configuraBottomNavigation()
    }

    private fun configuraBottomNavigation() {
        val bottomNavigation =
            findViewById<BottomNavigationView>(R.id.main_activity_bottom_navigation_view)
        bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.item_filmes -> {
                    startFragment(FilmesFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.item_personagens -> {
                    startFragment(PersonagensFragment())
                    return@setOnItemSelectedListener true
                }
                else -> return@setOnItemSelectedListener false
            }
        }
    }

    private fun startFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_activity_container_fragment, fragment, null)
            .commit()
    }
}