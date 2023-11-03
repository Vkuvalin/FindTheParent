package com.kuvalin.findtheparent.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kuvalin.findtheparent.domain.entity.Card
import com.kuvalin.findtheparent.generals.AppInitLoadState
import com.kuvalin.findtheparent.generals.CardStyleState
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
@Entity("app_init_load_state")
data class AppInitLoadStateDbModel(
    @PrimaryKey()
    val id: Int = Card.UNDEFINED_ID,
    val appInitLoadState: @RawValue AppInitLoadState
): Parcelable

