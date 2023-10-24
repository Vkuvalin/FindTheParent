package com.kuvalin.findtheparent.domain.usecase

import com.kuvalin.findtheparent.data.CardStyleState
import com.kuvalin.findtheparent.domain.repository.CardListRepository

class GetCardStyleStateUseCase(
    private val cardListRepository: CardListRepository
) {
    fun getCardStyleState(): CardStyleState{
        return cardListRepository.getCardStyleState()
    }
}