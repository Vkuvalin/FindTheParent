package com.kuvalin.findtheparent.domain.usecase


import com.kuvalin.findtheparent.domain.entity.Card
import com.kuvalin.findtheparent.domain.repository.CardListRepository
import com.kuvalin.findtheparent.generals.CardType
import javax.inject.Inject

class GetMatherPhotoCardUseCase @Inject constructor(
    private val cardListRepository: CardListRepository
) {
    suspend operator fun invoke(type: CardType): Card? = cardListRepository.getMatherPhotoCard(type)
}