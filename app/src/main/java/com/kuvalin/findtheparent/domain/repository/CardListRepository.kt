package com.kuvalin.findtheparent.domain.repository

import android.graphics.Bitmap
import android.net.Uri
import com.kuvalin.findtheparent.domain.entity.Card
import com.kuvalin.findtheparent.domain.entity.Score
import com.kuvalin.findtheparent.generals.AppInitLoadState
import com.kuvalin.findtheparent.generals.CardStyle
import com.kuvalin.findtheparent.generals.CardStyleState
import com.kuvalin.findtheparent.generals.CardType
import com.kuvalin.findtheparent.presentation.gamesettings.GameSettingsState

interface CardListRepository {

    suspend fun addGameScore(score: Score)
    suspend fun getGameScore(): Score


    suspend fun addCardStyleState(cardStyleState: CardStyleState)
    suspend fun getCardStyleState(): CardStyleState

    suspend fun addAppInitLoadState(appInitLoadState: AppInitLoadState)
    suspend fun getAppInitLoadState(): AppInitLoadState


    suspend fun getCardList(
        style: CardStyle,
        type: CardType,
        gameSettingsState: GameSettingsState
    ): List<Card>


    suspend fun addMatherPhotoCard(resId: Int, imageUri: Uri? = null)
    suspend fun getMatherPhotoCard(type: CardType): Card?


    suspend fun addFatherPhotoCard(resId: Int, imageUri: Uri? = null)
    suspend fun getFatherPhotoCard(type: CardType): Card?

}