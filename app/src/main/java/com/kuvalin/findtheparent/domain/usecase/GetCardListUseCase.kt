package com.kuvalin.findtheparent.domain.usecase

import androidx.lifecycle.LiveData
import com.kuvalin.findtheparent.domain.entity.Card
import com.kuvalin.findtheparent.domain.repository.CardListRepository

class GetCardListUseCase(
    private val cardListRepository: CardListRepository
) {

    fun getCardList(): LiveData<Card> {
        return cardListRepository.getCardList()
    }
}