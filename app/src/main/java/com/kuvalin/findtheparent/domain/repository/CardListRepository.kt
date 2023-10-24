package com.kuvalin.findtheparent.domain.repository

import androidx.lifecycle.LiveData
import com.kuvalin.findtheparent.data.CardStyleState
import com.kuvalin.findtheparent.domain.entity.Card
import com.kuvalin.findtheparent.domain.entity.Score
import com.kuvalin.findtheparent.generals.CardStyle
import com.kuvalin.findtheparent.generals.CardType

interface CardListRepository {

    fun addGameScoreUseCase(score: Score)
    fun getGameScoreUseCase(): Score


    fun addCardStyleState(cardStyleState: CardStyleState) // А это же мне нужно, да?
    fun getCardStyleState(): CardStyleState // А это же мне нужно, да?


    fun addCardList(style: CardStyle, type: CardType) // private
    fun getCardList(style: CardStyle, type: CardType): LiveData<List<Card>>


    fun addMatherPhotoCardUseCase(resId: Int)
    fun getMatherPhotoCardUseCase(type: CardType): Int


    fun addFatherPhotoCardUseCase(resId: Int)
    fun getFatherPhotoCardUseCase(type: CardType): Int

}