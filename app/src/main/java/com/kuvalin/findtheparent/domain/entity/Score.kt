package com.kuvalin.findtheparent.domain.entity

import com.kuvalin.findtheparent.domain.entity.Card.Companion.UNDEFINED_ID

data class Score(
    val id: Int = UNDEFINED_ID,
    val mama: Int,
    val papa: Int
)
