package com.kuvalin.findtheparent.domain.usecase


import com.kuvalin.findtheparent.domain.repository.CardListRepository
import com.kuvalin.findtheparent.generals.CardType

class GetFatherPhotoCardUseCase(
    private val cardListRepository: CardListRepository
) {
    fun getFatherPhotoCardUseCase(type: CardType): Int {
        return cardListRepository.getFatherPhotoCardUseCase(type)
    }
}