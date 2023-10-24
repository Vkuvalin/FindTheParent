package com.kuvalin.findtheparent.domain.usecase

import com.kuvalin.findtheparent.domain.entity.Card
import com.kuvalin.findtheparent.domain.repository.CardListRepository

class GetMatherPhotoCardUseCase(
    private val cardListRepository: CardListRepository
) {
    fun getMatherPhotoCardUseCase(card: Card): Int {
        return cardListRepository.getMatherPhotoCardUseCase(card)
    }
}