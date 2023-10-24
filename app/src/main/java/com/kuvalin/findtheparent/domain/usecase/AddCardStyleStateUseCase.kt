package com.kuvalin.findtheparent.domain.usecase

import com.kuvalin.findtheparent.data.CardStyleState
import com.kuvalin.findtheparent.domain.repository.CardListRepository

class AddCardStyleStateUseCase(
    private val cardListRepository: CardListRepository
) {
    fun addCardStyleState(cardStyleState: CardStyleState){
        cardListRepository.addCardStyleState(cardStyleState)
    }
}