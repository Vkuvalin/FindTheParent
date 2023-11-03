package com.kuvalin.findtheparent.di

import android.content.Context
import com.kuvalin.findtheparent.data.database.AppDatabase
import com.kuvalin.findtheparent.data.database.CardListDao
import com.kuvalin.findtheparent.data.repository.CardListRepositoryImpl
import com.kuvalin.findtheparent.domain.repository.CardListRepository
import dagger.Binds
import dagger.Module
import dagger.Provides


@Module
interface DataModule {


    @Binds
    @ApplicationScope
    fun bindCardListRepository(impl: CardListRepositoryImpl): CardListRepository

    companion object {

        @Provides
        @ApplicationScope
        fun provideCardListDao(
            context: Context
        ): CardListDao {
            return AppDatabase.getInstance(context).cardListDao()
        }

    }

}