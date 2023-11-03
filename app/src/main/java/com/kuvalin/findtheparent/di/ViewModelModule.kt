package com.sumin.vknewsclient.di

import androidx.lifecycle.ViewModel
import com.kuvalin.findtheparent.presentation.AppViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

// Урок - https://stepik.org/lesson/926374/step/1?unit=932257
@Module
interface ViewModelModule {

    @IntoMap
    @ViewModelKey(AppViewModel::class)
    @Binds
    fun bindAppViewModel(viewModel: AppViewModel): ViewModel

}