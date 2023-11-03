package com.kuvalin.findtheparent.presentation.main

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.kuvalin.findtheparent.R
import com.kuvalin.findtheparent.data.model.InitialLoadState
import com.kuvalin.findtheparent.data.repository.CardListRepositoryImpl
import com.kuvalin.findtheparent.domain.usecase.GetAppInitLoadStateUseCase
import com.kuvalin.findtheparent.generals.AppInitLoadState
import com.kuvalin.findtheparent.generals.AppInitLoadState.Companion.putAppInitLoadState
import com.kuvalin.findtheparent.generals.FindTheParentsApplication
import com.kuvalin.findtheparent.navigation.AppNavigationScreens
import com.kuvalin.findtheparent.navigation.AppNavigationScreens.Companion.screenState
import com.kuvalin.findtheparent.presentation.game.Game
import com.kuvalin.findtheparent.presentation.gamesettings.GameSettingsMenu
import com.kuvalin.findtheparent.presentation.mainmenu.MainMenu
import com.kuvalin.findtheparent.presentation.welcome.WelcomeScreen
import com.kuvalin.findtheparent.ui.theme.FindTheParentTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val scope = CoroutineScope(Dispatchers.Default)
            val context = LocalContext.current
            val repository = CardListRepositoryImpl(context)

            var playerState by remember { mutableStateOf(true) }
            val audioList = listOf(
                MediaPlayer.create(context, R.raw.a737e81d54d999b),
                MediaPlayer.create(context, R.raw.afe34ea6a6f0abd),
                MediaPlayer.create(context, R.raw.c93bdf8c4c82d599),
                MediaPlayer.create(context, R.raw.e2134c6a3178314)
            )
            var mMediaPlayer = audioList.random()

            val soundCard = MediaPlayer.create(context, R.raw.coup)
            val soundWin = MediaPlayer.create(context, if((0..1).random() == 1) R.raw.sound_win_1 else R.raw.sound_win_2) // Можно листом TODO

            FindTheParentTheme {

                scope.launch {

                    if (screenState.value != AppNavigationScreens.Welcome) {
                        while (true) {
                            if (playerState) {
                                mMediaPlayer.start()
                                playerState = false
                            }
                            if (!mMediaPlayer.isPlaying) {
                                playerState = true
                                mMediaPlayer = audioList.random()
                            }
                            delay(1000)
                        }
                    }
                }


                val screenState = AppNavigationScreens.screenState.collectAsState()
                val appInitLoadState = AppInitLoadState.appInitLoadState.collectAsState()


                scope.launch {
                    delay(500)
                    putAppInitLoadState(GetAppInitLoadStateUseCase(repository).invoke(), context)
                }

                when (val currentState = screenState.value) {
                    is AppNavigationScreens.Welcome -> {
                        if (appInitLoadState.value == AppInitLoadState.Successive) {
                            AppNavigationScreens.putScreenState(AppNavigationScreens.MainMenu)
                        }
                        WelcomeScreen {
                            AppNavigationScreens.putScreenState(AppNavigationScreens.MainMenu)
                            scope.launch { putAppInitLoadState(AppInitLoadState.Successive, context) }
                        }
                    }

                    is AppNavigationScreens.MainMenu -> {
                        MainMenu(repository, appInitLoadState.value)
                    }

                    is AppNavigationScreens.GameSettingsMenu -> {
                        GameSettingsMenu(
                            repository,
                            onBackPress = {
                                AppNavigationScreens.putScreenState(AppNavigationScreens.MainMenu)
                            }
                        )
                    }

                    is AppNavigationScreens.Game -> {
                        Game(
                            repository = repository,
                            cardList = currentState.cardList,
                            gameSettingsState = currentState.gameSettingsState,
                            onBackPress = {
                                AppNavigationScreens.putScreenState(AppNavigationScreens.GameSettingsMenu)
                            },
                            soundCard = soundCard,
                            soundWin = soundWin
                        )
                    }
                }
            }

        }
    }
}
