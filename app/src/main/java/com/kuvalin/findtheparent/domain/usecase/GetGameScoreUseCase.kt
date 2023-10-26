package com.kuvalin.findtheparent.domain.usecase

import com.kuvalin.findtheparent.domain.entity.Score
import com.kuvalin.findtheparent.domain.repository.CardListRepository

class GetGameScoreUseCase(
    private val cardListRepository: CardListRepository
) {
    suspend operator fun invoke(): Score = cardListRepository.getGameScore()
}