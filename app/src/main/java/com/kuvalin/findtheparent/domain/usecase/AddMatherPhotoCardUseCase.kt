package com.kuvalin.findtheparent.domain.usecase

import com.kuvalin.findtheparent.domain.repository.CardListRepository

class AddMatherPhotoCardUseCase(
    private val cardListRepository: CardListRepository
) {

    fun addMatherPhotoCardUseCase(resId: Int) {
        cardListRepository.addMatherPhotoCardUseCase(resId)
    }
}