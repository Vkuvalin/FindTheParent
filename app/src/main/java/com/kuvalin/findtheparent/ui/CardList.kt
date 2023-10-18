package com.kuvalin.findtheparent.ui

import com.kuvalin.findtheparent.R
import kotlin.random.Random

class CardList {
    fun getCardCollection(): List<Int> {

        val number = Random.nextInt(2)

        val collection1 = listOf(
            R.drawable.papa,
            R.drawable.mama,
            R.drawable.style_3_bear,
            R.drawable.style_3_beaver,
            R.drawable.style_3_cat,
            R.drawable.style_3_cow,
            R.drawable.style_3_dog,
            R.drawable.style_3_eagle,
            R.drawable.style_3_elephant,
            R.drawable.style_3_fox,
            R.drawable.style_3_giraffe,
            R.drawable.style_3_hippo,
            R.drawable.style_3_lion,
            R.drawable.style_3_mouse,
            R.drawable.style_3_pig,
            R.drawable.style_3_snake,
        )
        val collection2 = listOf(
            R.drawable.papa,
            R.drawable.mama,
            R.drawable.style_2_bear,
            R.drawable.style_2_beaver,
            R.drawable.style_2_cat,
            R.drawable.style_2_cow,
            R.drawable.style_2_dog,
            R.drawable.style_2_eagle,
            R.drawable.style_2_elephant,
            R.drawable.style_2_fox,
            R.drawable.style_2_giraffe,
            R.drawable.style_2_hippo,
            R.drawable.style_2_lion,
            R.drawable.style_2_mouse,
            R.drawable.style_2_pig,
            R.drawable.style_2_snake,
        )

        return if (number == 1) collection1.shuffled() else collection2.shuffled()
    }
}