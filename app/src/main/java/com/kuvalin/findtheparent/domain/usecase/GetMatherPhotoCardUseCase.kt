package com.kuvalin.findtheparent.domain.usecase


import com.kuvalin.findtheparent.domain.entity.Card
import com.kuvalin.findtheparent.domain.repository.CardListRepository
import com.kuvalin.findtheparent.generals.CardType

class GetMatherPhotoCardUseCase(
    private val cardListRepository: CardListRepository
) {
    suspend operator fun invoke(type: CardType): Card? = cardListRepository.getMatherPhotoCard(type)
}