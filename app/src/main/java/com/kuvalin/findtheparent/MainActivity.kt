package com.kuvalin.findtheparent

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.kuvalin.findtheparent.ui.CardList
import com.kuvalin.findtheparent.ui.screens.FindParentMainScreen
import com.kuvalin.findtheparent.ui.screens.FindParentsGameScreen
import com.kuvalin.findtheparent.ui.screens.WelcomeScreen
import com.kuvalin.findtheparent.ui.theme.FindTheParentTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            var runToMainScreen by remember { mutableStateOf(false) }
            var runToGameScreen by remember { mutableStateOf(false) }
            val cardList = CardList()

            FindTheParentTheme {

                // Переделать потом эту хрень на стейты!
                when (runToMainScreen) {
                    false -> {
                        WelcomeScreen{
                            runToMainScreen = !runToMainScreen
                        }
                    }
                    true -> {
                        if (!runToGameScreen){
                            FindParentMainScreen{
                                runToGameScreen = !runToGameScreen
                            }
                        } else {
                            FindParentsGameScreen(cardList.getCardCollection()) {
                                runToGameScreen = !runToGameScreen
                            }
                        }
                    }
                }

            }
        }
    }
}