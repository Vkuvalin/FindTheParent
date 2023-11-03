package com.kuvalin.findtheparent.domain.usecase

import com.kuvalin.findtheparent.domain.entity.Score
import com.kuvalin.findtheparent.domain.repository.CardListRepository
import javax.inject.Inject

class GetGameScoreUseCase @Inject constructor(
    private val cardListRepository: CardListRepository
) {
    suspend operator fun invoke(): Score = cardListRepository.getGameScore()
}