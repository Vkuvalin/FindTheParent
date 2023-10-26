package com.kuvalin.findtheparent.domain.usecase

import com.kuvalin.findtheparent.domain.entity.Card
import com.kuvalin.findtheparent.domain.repository.CardListRepository
import com.kuvalin.findtheparent.generals.CardStyle
import com.kuvalin.findtheparent.generals.CardType
import com.kuvalin.findtheparent.presentation.gamesettings.GameSettingsState

class GetCardListUseCase(
    private val cardListRepository: CardListRepository
) {

    suspend operator fun invoke(
        style: CardStyle,
        type: CardType,
        gameSettingsState: GameSettingsState
    ): List<Card> = cardListRepository.getCardList(style, type, gameSettingsState)

}