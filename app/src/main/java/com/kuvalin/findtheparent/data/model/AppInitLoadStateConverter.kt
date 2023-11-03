package com.kuvalin.findtheparent.data.model

import androidx.room.TypeConverter
import com.kuvalin.findtheparent.generals.AppInitLoadState
import com.kuvalin.findtheparent.generals.CardStyleState

class AppInitLoadStateConverter {
    @TypeConverter
    fun fromAppInitLoadState(appInitLoadState: AppInitLoadState): String {
        return when (appInitLoadState) {
            is AppInitLoadState.Initial -> "Initial"
            is AppInitLoadState.Successive -> "Successive"
        }
    }

    @TypeConverter
    fun toAppInitLoadState(appInitLoadStateString: String): AppInitLoadState {
        return when (appInitLoadStateString) {
            "Initial" -> AppInitLoadState.Initial
            "Successive" -> AppInitLoadState.Successive
            else -> AppInitLoadState.Initial
        }
    }
}