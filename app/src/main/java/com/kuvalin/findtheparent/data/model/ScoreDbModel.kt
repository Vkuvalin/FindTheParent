package com.kuvalin.findtheparent.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("score")
data class ScoreDbModel(
    @PrimaryKey()
    val id: Int,
    val mama: Int,
    val papa: Int
)
