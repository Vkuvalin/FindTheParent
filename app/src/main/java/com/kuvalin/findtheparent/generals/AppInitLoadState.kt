package com.kuvalin.findtheparent.generals


import android.content.Context
import com.kuvalin.findtheparent.domain.usecase.AddAppInitLoadStateUseCase
import com.kuvalin.findtheparent.presentation.AppViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


sealed class AppInitLoadState() {

    object Initial : AppInitLoadState()
    object Successive  : AppInitLoadState()

    companion object {

        private val _appInitLoadState = MutableStateFlow<AppInitLoadState>(Initial)
        val appInitLoadState = _appInitLoadState.asStateFlow()

        suspend fun putAppInitLoadState(viewModel: AppViewModel, appInitLoadState: AppInitLoadState) {
            _appInitLoadState.value = appInitLoadState
            viewModel.addAppInitLoadState.invoke(appInitLoadState)
        }
    }
}