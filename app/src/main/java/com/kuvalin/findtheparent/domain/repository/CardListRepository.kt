package com.kuvalin.findtheparent.domain.repository

import androidx.lifecycle.LiveData
import com.kuvalin.findtheparent.data.CardStyleState
import com.kuvalin.findtheparent.domain.entity.Card
import com.kuvalin.findtheparent.domain.entity.Score

interface CardListRepository {
    fun addCardStyleState(style: CardStyleState) // А это же мне нужно, да?
    fun addFatherPhotoCardUseCase(resId: Int)
    fun addGameScoreUseCase(score: Score)
    fun addMatherPhotoCardUseCase(resId: Int)

    fun getCardStyleState(): CardStyleState // А это же мне нужно, да?
    fun getCardList(): LiveData<Card>
    fun getGameScoreUseCase(): Score
    fun getFatherPhotoCardUseCase(card: Card): Int
    fun getMatherPhotoCardUseCase(card: Card): Int
}