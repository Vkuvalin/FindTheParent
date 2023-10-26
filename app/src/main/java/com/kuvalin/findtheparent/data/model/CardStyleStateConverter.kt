package com.kuvalin.findtheparent.data.model

import androidx.room.TypeConverter
import com.kuvalin.findtheparent.generals.CardStyleState

class CardStyleStateConverter {
    @TypeConverter
    fun fromCardStyleState(cardStyleState: CardStyleState): String {
        return when (cardStyleState) {
            is CardStyleState.Style1 -> "Style1"
            is CardStyleState.Style2 -> "Style2"
            is CardStyleState.Style3 -> "Style3"
        }
    }

    @TypeConverter
    fun toCardStyleState(cardStyleStateString: String): CardStyleState {
        return when (cardStyleStateString) {
            "Style1" -> CardStyleState.Style1
            "Style2" -> CardStyleState.Style2
            "Style3" -> CardStyleState.Style3
            else -> CardStyleState.Style1 // Default value or handle invalid cases
        }
    }
}