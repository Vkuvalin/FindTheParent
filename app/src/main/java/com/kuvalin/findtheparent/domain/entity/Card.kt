package com.kuvalin.findtheparent.domain.entity


data class Card(
    val id: Int,            // 777/888
    val resourceId: Int,
    val style: String = ""  // mama/papa
)