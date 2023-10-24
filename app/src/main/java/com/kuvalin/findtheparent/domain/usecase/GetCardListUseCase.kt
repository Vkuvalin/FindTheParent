package com.kuvalin.findtheparent.domain.usecase

import androidx.lifecycle.LiveData
import com.kuvalin.findtheparent.data.CardStyle
import com.kuvalin.findtheparent.domain.entity.Card
import com.kuvalin.findtheparent.domain.repository.CardListRepository
import com.kuvalin.findtheparent.generals.CardType

class GetCardListUseCase(
    private val cardListRepository: CardListRepository
) {

    fun getCardList(style: CardStyle, type: CardType): LiveData<List<Card>> {
        return cardListRepository.getCardList(style, type)
    }

}