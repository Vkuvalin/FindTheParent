package com.kuvalin.findtheparent.di

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module


// https://stackoverflow.com/questions/58040392/how-to-provide-context-with-dagger-2
@Module
interface ContextModule {

    @Binds
    @ApplicationScope
    fun context(appInstance: Application): Context

}