package com.kuvalin.findtheparent.data.repository

import androidx.lifecycle.LiveData
import com.kuvalin.findtheparent.data.CardStyleState
import com.kuvalin.findtheparent.data.database.CardListDao
import com.kuvalin.findtheparent.domain.entity.Card
import com.kuvalin.findtheparent.domain.entity.Score
import com.kuvalin.findtheparent.domain.repository.CardListRepository
import com.kuvalin.findtheparent.generals.CardStyle
import com.kuvalin.findtheparent.generals.CardType

class CardListRepositoryImpl(
    private val cardListDao: CardListDao

): CardListRepository {

    override fun addGameScoreUseCase(score: Score) {

    }
    override fun getGameScoreUseCase(): Score {
        return TODO()
    }



    // А это же мне нужно, да?
    override fun addCardStyleState(cardStyleState: CardStyleState) {

    }
    override fun getCardStyleState(): CardStyleState {
        return TODO()
    }



    override fun addCardList(style: CardStyle, type: CardType) { // private

    }
    override fun getCardList(style: CardStyle, type: CardType): LiveData<List<Card>> {
        return TODO()
    }



    override fun addMatherPhotoCardUseCase(resId: Int) {

    }
    override fun getMatherPhotoCardUseCase(type: CardType): Int {
        return TODO()
    }



    override fun addFatherPhotoCardUseCase(resId: Int) {

    }
    override fun getFatherPhotoCardUseCase(type: CardType): Int {
        return TODO()
    }

}