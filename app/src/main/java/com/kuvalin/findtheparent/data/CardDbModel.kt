package com.kuvalin.findtheparent.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity("card_items")
data class CardDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val resourceId: Int,
    val style: String = ""
)