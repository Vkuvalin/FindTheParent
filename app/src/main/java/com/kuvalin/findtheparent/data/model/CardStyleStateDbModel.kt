package com.kuvalin.findtheparent.data.model

import androidx.room.Entity
import com.kuvalin.findtheparent.data.CardStyleState

@Entity("card_style_state")
data class CardStyleStateDbModel(
    val cardStyleState: CardStyleState
)