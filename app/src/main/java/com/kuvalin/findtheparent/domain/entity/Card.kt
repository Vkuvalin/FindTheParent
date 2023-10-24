package com.kuvalin.findtheparent.domain.entity

import com.kuvalin.findtheparent.generals.CardStyle
import com.kuvalin.findtheparent.generals.CardType


data class Card(
    val id: Int,
    val resourceId: Int,
    val style: CardStyle = CardStyle.STYLE1,
    val type: CardType = CardType.NORMAL
)