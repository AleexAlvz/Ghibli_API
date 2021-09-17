package br.com.alexalves.ghibliapi.di

import br.com.alexalves.ghibliapi.retrofit.GhibliServiceAPI
import br.com.alexalves.ghibliapi.retrofit.GhibliServices
import br.com.alexalves.ghibliapi.viewmodel.FilmesViewModel
import br.com.alexalves.ghibliapi.viewmodel.PersonagensViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module{

    single<GhibliServices> {
        GhibliServiceAPI().getGhibliService()
    }

    viewModel {
        FilmesViewModel(get<GhibliServices>())
    }
    viewModel {
        PersonagensViewModel(get<GhibliServices>())
    }

}