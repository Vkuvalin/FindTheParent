package com.kuvalin.findtheparent.data.model

import androidx.room.Entity

@Entity("score")
data class ScoreDbModel(
    val mama: Int,
    val papa: Int
)
