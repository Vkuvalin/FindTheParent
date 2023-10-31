package com.kuvalin.findtheparent.domain.usecase


import com.kuvalin.findtheparent.domain.entity.Card
import com.kuvalin.findtheparent.domain.repository.CardListRepository
import com.kuvalin.findtheparent.generals.CardType

class GetFatherPhotoCardUseCase(
    private val cardListRepository: CardListRepository
) {
    suspend operator fun invoke(type: CardType): Card? = cardListRepository.getFatherPhotoCard(type)

}