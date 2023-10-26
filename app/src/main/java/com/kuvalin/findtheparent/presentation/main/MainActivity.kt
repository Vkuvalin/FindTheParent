package com.kuvalin.findtheparent.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import com.kuvalin.findtheparent.navigation.AppNavigationScreens
import com.kuvalin.findtheparent.presentation.game.Game
import com.kuvalin.findtheparent.presentation.gamesettings.GameSettingsMenu
import com.kuvalin.findtheparent.presentation.mainmenu.MainMenu
import com.kuvalin.findtheparent.presentation.welcome.WelcomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val screenState = AppNavigationScreens.screenState.collectAsState()


//            FindTheParentTheme {

            when (val currentState = screenState.value) {
                is AppNavigationScreens.Welcome -> {
                        AppNavigationScreens.putScreenState(AppNavigationScreens.MainMenu)
                    WelcomeScreen{
                        AppNavigationScreens.putScreenState(AppNavigationScreens.MainMenu)
                    }
                }
                is AppNavigationScreens.MainMenu -> {
                    MainMenu()
                }
                is AppNavigationScreens.GameSettingsMenu -> {
                    GameSettingsMenu(
                        onBackPress = {
                            AppNavigationScreens.putScreenState(AppNavigationScreens.MainMenu)
                        }
                    )
                }
                is AppNavigationScreens.Game -> {
                    Game(
                        cardList = currentState.cardList,
                        gameSettingsState = currentState.gameSettingsState,
                        onBackPress = {
                            AppNavigationScreens.putScreenState(AppNavigationScreens.GameSettingsMenu)
                        }
                    )
                }
            }

//            }
        }
    }
}




//region Backup
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        setContent {
//
//            var screenState = AppNavigationScreens.screenState.collectAsState()
//
//            FindTheParentTheme {
//
//                /*
//                Слушай, а на кой хуй я вообще выношу сюда изменение стейтов, если:
//                1. screenState - я чисто читаю.
//                2. А так как записываю через put, то вообще все это можно утопить внутрь, оставив тут минимум
//                */
//
//                when (val currentState = screenState.value) {
//                    is AppNavigationScreens.Welcome -> {
//                        AppNavigationScreens.putScreenState(AppNavigationScreens.MainMenu)
//                        WelcomeScreen{
//                            AppNavigationScreens.putScreenState(AppNavigationScreens.MainMenu)
//                        }
//                    }
//                    is AppNavigationScreens.MainMenu -> {
//                        MainMenu(
//                            onStartGameClick = {
//                                AppNavigationScreens.putScreenState(AppNavigationScreens.GameSettingsMenu)
//                            },
//                            changeIconStyle = {style ->
//
//                                when(style){ // TODO - как ещё можно?
//                                    getString(R.string.cardStyle_1) -> {
//                                        CardStyleState.putCardStyleState(CardStyleState.Style1)
//                                    }
//                                    getString(R.string.cardStyle_2) -> {
//                                        CardStyleState.putCardStyleState(CardStyleState.Style2)
//                                    }
//                                    getString(R.string.cardStyle_3) -> {
//                                        CardStyleState.putCardStyleState(CardStyleState.Style3)
//                                    }
//                                }
//                            }
//                        )
//                    }
//                    is AppNavigationScreens.GameSettingsMenu -> {
//                        GameSettingsMenu(
//                            onClickEasySettings = {
//                                AppNavigationScreens.putScreenState(AppNavigationScreens.Game(gameSettingsState = GameSettingsState.Easy))
//                            },
//                            onClickMediumSettings = {
//                                AppNavigationScreens.putScreenState(AppNavigationScreens.Game(gameSettingsState = GameSettingsState.Medium))
//                            },
//                            onClickHardSettings = {
//                                AppNavigationScreens.putScreenState(AppNavigationScreens.Game(gameSettingsState = GameSettingsState.Hard))
//                            },
//                            onClickSpecialSettings = {
//                                AppNavigationScreens.putScreenState(AppNavigationScreens.Game(gameSettingsState = GameSettingsState.Special))
//                            },
//                            onBackPress = {
//                                AppNavigationScreens.putScreenState(AppNavigationScreens.MainMenu)
//                            }
//                        )
//                    }
//                    is AppNavigationScreens.Game -> {
//                        Game(
//                            cardList = currentState.cardList,
//                            gameSettingsState = currentState.gameSettingsState,
//                            onBackPress = {
//                                AppNavigationScreens.putScreenState(AppNavigationScreens.GameSettingsMenu)
//                            },
//                            onBackPressMain = {
//                                AppNavigationScreens.putScreenState(AppNavigationScreens.MainMenu)
//                            }
//                        )
//                    }
//                }
//
//            }
//        }
//    }
//}
//endregion