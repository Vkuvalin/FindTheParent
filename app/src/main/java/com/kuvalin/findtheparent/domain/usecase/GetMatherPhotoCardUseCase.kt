package com.kuvalin.findtheparent.domain.usecase


import com.kuvalin.findtheparent.domain.repository.CardListRepository
import com.kuvalin.findtheparent.generals.CardType

class GetMatherPhotoCardUseCase(
    private val cardListRepository: CardListRepository
) {
    fun getMatherPhotoCardUseCase(type: CardType): Int {
        return cardListRepository.getMatherPhotoCardUseCase(type)
    }
}