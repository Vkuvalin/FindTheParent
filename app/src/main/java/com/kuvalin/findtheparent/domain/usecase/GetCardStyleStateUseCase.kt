package com.kuvalin.findtheparent.domain.usecase


import com.kuvalin.findtheparent.domain.repository.CardListRepository
import com.kuvalin.findtheparent.generals.CardStyleState

class GetCardStyleStateUseCase(
    private val cardListRepository: CardListRepository
) {
    suspend operator fun invoke(): CardStyleState = cardListRepository.getCardStyleState()
}