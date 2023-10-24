package com.kuvalin.findtheparent.presentation.gamesettings

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

sealed class GameSettingsState(
    val numberCells: Int,
    val column: Int,
    val cardSize: Dp
) {


    object Easy : GameSettingsState(
        numberCells = 12,
        column = 3,
        cardSize = 100.dp
    )

    object Medium : GameSettingsState(
        numberCells = 16,
        column = 4,
        cardSize = 75.dp
    )

    object Hard : GameSettingsState(
        numberCells = 20,
        column = 4,
        cardSize = 75.dp
    )

    object Special : GameSettingsState(
        numberCells = 20,
        column = 4,
        cardSize = 75.dp
    )

}
