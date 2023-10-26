package com.kuvalin.findtheparent.domain.entity

import com.kuvalin.findtheparent.generals.CardStyle
import com.kuvalin.findtheparent.generals.CardType


data class Card(
    val id: Int = UNDEFINED_ID,
    val resourceId: Int,
    val style: CardStyle = CardStyle.STYLE1,
    val type: CardType = CardType.NORMAL
) {
    companion object{
        const val UNDEFINED_ID = 0
    }
}