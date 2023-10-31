package com.kuvalin.findtheparent.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kuvalin.findtheparent.domain.entity.Card


@Entity("initial_load_state")
data class InitialLoadState (
    @PrimaryKey()
    val initialLoadState: Boolean = false
)