package com.kuvalin.findtheparent.generals


import android.content.Context
import com.kuvalin.findtheparent.data.repository.CardListRepositoryImpl
import com.kuvalin.findtheparent.domain.usecase.AddCardStyleStateUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

// Где будет правильно разместить этого друга?
sealed class CardStyleState() {

    object Style1 : CardStyleState()
    object Style2 : CardStyleState()
    object Style3 : CardStyleState()

    companion object {

        private val _cardStyleState = MutableStateFlow<CardStyleState>(Style1)
        val cardStyleState = _cardStyleState.asStateFlow()

        suspend fun putCardStyleState(cardStyleState: CardStyleState, context: Context) {
            _cardStyleState.value = cardStyleState
            AddCardStyleStateUseCase(CardListRepositoryImpl(context)).invoke(cardStyleState)
        }
    }
}