package com.kuvalin.findtheparent.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kuvalin.findtheparent.generals.CardStyle
import com.kuvalin.findtheparent.generals.CardType

@Entity("card_items")
data class CardDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val resourceId: Int,
    val style: CardStyle = CardStyle.STYLE1,
    val type: CardType = CardType.NORMAL
)