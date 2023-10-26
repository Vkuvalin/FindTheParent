package com.kuvalin.findtheparent.domain.repository

import com.kuvalin.findtheparent.domain.entity.Card
import com.kuvalin.findtheparent.domain.entity.Score
import com.kuvalin.findtheparent.generals.CardStyle
import com.kuvalin.findtheparent.generals.CardStyleState
import com.kuvalin.findtheparent.generals.CardType
import com.kuvalin.findtheparent.presentation.gamesettings.GameSettingsState

interface CardListRepository {

    suspend fun addGameScore(score: Score)
    suspend fun getGameScore(): Score


    suspend fun addCardStyleState(cardStyleState: CardStyleState) // А это же мне нужно, да?
    suspend fun getCardStyleState(): CardStyleState // А это же мне нужно, да?


    suspend fun getCardList(
        style: CardStyle,
        type: CardType,
        gameSettingsState: GameSettingsState
    ): List<Card>


    suspend fun addMatherPhotoCard(resId: Int)
    suspend fun getMatherPhotoCard(type: CardType): Card


    suspend fun addFatherPhotoCard(resId: Int)
    suspend fun getFatherPhotoCard(type: CardType): Card

}