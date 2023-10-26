package com.kuvalin.findtheparent.domain.usecase

import com.kuvalin.findtheparent.domain.repository.CardListRepository

class AddMatherPhotoCardUseCase(
    private val cardListRepository: CardListRepository
) {
    suspend operator fun invoke(resId: Int) = cardListRepository.addMatherPhotoCard(resId)
}