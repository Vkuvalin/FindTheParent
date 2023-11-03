package com.kuvalin.findtheparent.domain.usecase


import com.kuvalin.findtheparent.domain.repository.CardListRepository
import com.kuvalin.findtheparent.generals.CardStyleState
import javax.inject.Inject

class AddCardStyleStateUseCase @Inject constructor(
    private val cardListRepository: CardListRepository
) {
    suspend operator fun invoke(cardStyleState: CardStyleState) = cardListRepository.addCardStyleState(cardStyleState)
}