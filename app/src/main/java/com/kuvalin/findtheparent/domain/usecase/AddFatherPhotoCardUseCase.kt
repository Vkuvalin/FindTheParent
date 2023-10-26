package com.kuvalin.findtheparent.domain.usecase

import com.kuvalin.findtheparent.domain.repository.CardListRepository

class AddFatherPhotoCardUseCase(
    private val cardListRepository: CardListRepository
) {
    suspend operator fun invoke(resId: Int) = cardListRepository.addFatherPhotoCard(resId)
}