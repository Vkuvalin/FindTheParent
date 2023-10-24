package com.kuvalin.findtheparent.domain.usecase

import com.kuvalin.findtheparent.domain.entity.Card
import com.kuvalin.findtheparent.domain.repository.CardListRepository

class GetFatherPhotoCardUseCase(
    private val cardListRepository: CardListRepository
) {
    fun getFatherPhotoCardUseCase(card: Card): Int {
        return cardListRepository.getFatherPhotoCardUseCase(card)
    }
}