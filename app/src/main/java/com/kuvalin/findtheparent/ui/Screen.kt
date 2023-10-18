package com.kuvalin.findtheparent.ui

sealed class Screen {

    object Welcome : Screen() // Он же Initial
    object Main: Screen()
    object Game : Screen()
    object GameSettings: Screen()

}
