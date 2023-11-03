package com.kuvalin.findtheparent.generals


import android.content.Context
import com.kuvalin.findtheparent.data.repository.CardListRepositoryImpl
import com.kuvalin.findtheparent.domain.usecase.AddAppInitLoadStateUseCase
import com.kuvalin.findtheparent.domain.usecase.AddCardStyleStateUseCase
import com.kuvalin.findtheparent.generals.CardStyleState.Companion.cardStyleState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


sealed class AppInitLoadState() {

    object Initial : AppInitLoadState()
    object Successive  : AppInitLoadState()

    companion object {

        private val _appInitLoadState = MutableStateFlow<AppInitLoadState>(Initial)
        val appInitLoadState = _appInitLoadState.asStateFlow()

        suspend fun putAppInitLoadState(appInitLoadState: AppInitLoadState, context: Context) {
            _appInitLoadState.value = appInitLoadState
            AddAppInitLoadStateUseCase(CardListRepositoryImpl(context)).invoke(appInitLoadState)
        }
    }
}