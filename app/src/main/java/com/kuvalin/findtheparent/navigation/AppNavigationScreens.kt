package com.kuvalin.findtheparent.navigation



import android.content.Context
import com.kuvalin.findtheparent.domain.entity.Card
import com.kuvalin.findtheparent.presentation.gamesettings.GameSettingsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

sealed class AppNavigationScreens {

    object Welcome : AppNavigationScreens() // Он же Initial
    object MainMenu: AppNavigationScreens()
    data class Game(
        private val context: Context,
        var gameSettingsState: GameSettingsState,
        val cardList: List<Card>
    ) : AppNavigationScreens()
    object GameSettingsMenu: AppNavigationScreens()


    companion object {
        private val _screenState = MutableStateFlow<AppNavigationScreens>(Welcome)
        val screenState = _screenState.asStateFlow()

        fun putScreenState(screenState: AppNavigationScreens) {
            _screenState.value = screenState
        }
    }

}

//        val cardList: List<Card> = CardList().getCardCollection(gameSettingsState)
// Вот это, на сколько я понимаю, должно быть во ViewModel. То что выше можно так и оставить.