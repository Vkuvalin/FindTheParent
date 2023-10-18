package com.kuvalin.findtheparent

import android.os.Bundle
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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            var endOfAnimation by remember { mutableStateOf(false) }
            var runToGameScreen by remember { mutableStateOf(false) }
            val cardList = CardList()

            FindTheParentTheme {

                // Переделать потом эту хрень на стейты!
                when (endOfAnimation) {
                    false -> {
                        WelcomeScreen{
                            endOfAnimation = !endOfAnimation
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