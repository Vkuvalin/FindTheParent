package com.kuvalin.findtheparent.domain.usecase

import com.kuvalin.findtheparent.domain.repository.CardListRepository

class AddFatherPhotoCardUseCase(
    private val cardListRepository: CardListRepository
) {
    fun addFatherPhotoCardUseCase(resId: Int) {
        cardListRepository.addFatherPhotoCardUseCase(resId)
    }
}