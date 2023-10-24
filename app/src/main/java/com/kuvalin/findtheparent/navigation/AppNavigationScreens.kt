package com.kuvalin.findtheparent.navigation


import com.kuvalin.findtheparent.domain.entity.Card
import com.kuvalin.findtheparent.data.CardList
import com.kuvalin.findtheparent.presentation.gamesettings.GameSettingsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

sealed class AppNavigationScreens {

    object Welcome : AppNavigationScreens() // Он же Initial
    object MainMenu: AppNavigationScreens()
    data class Game(
        var gameSettingsState: GameSettingsState,
        val cardList: List<Card> = CardList().getCardCollection(gameSettingsState)
    ) : AppNavigationScreens()
    object GameSettingsMenu: AppNavigationScreens()


    companion object {   // Вот это, на сколько я понимаю, должно быть во ViewModel. То что выше можно так и оставить.
        private val _screenState = MutableStateFlow<AppNavigationScreens>(Welcome)
        val screenState = _screenState.asStateFlow()

        fun putScreenState(screenState: AppNavigationScreens) {
            _screenState.value = screenState
        }
    }

}
