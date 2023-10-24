package com.kuvalin.findtheparent.data


import com.kuvalin.findtheparent.R
import com.kuvalin.findtheparent.domain.entity.Card
import com.kuvalin.findtheparent.presentation.gamesettings.GameSettingsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


sealed class CardStyleState() {

    object Style1 : CardStyleState()
    object Style2 : CardStyleState()
    object Style3 : CardStyleState()

    companion object {
        private val _cardStyleState = MutableStateFlow<CardStyleState>(Style1)
        val cardStyleState = _cardStyleState.asStateFlow()

        fun putCardStyleState(cardStyleState: CardStyleState) {
            _cardStyleState.value = cardStyleState
        }
    }
}


class CardList {

    private val cardStyleState = CardStyleState.cardStyleState

    fun getCardCollection(gameSettingsState: GameSettingsState): List<Card> {

        //region collection1
        val collection1 = listOf( // 16
            R.drawable.style_1_camel,
            R.drawable.style_1_cat,
            R.drawable.style_1_cow,
            R.drawable.style_1_deer,
            R.drawable.style_1_duck,
            R.drawable.style_1_elephant,
            R.drawable.style_1_fish,
            R.drawable.style_1_flamingo,
            R.drawable.style_1_fox,
            R.drawable.style_1_lion,
            R.drawable.style_1_monkey,
            R.drawable.style_1_panda,
            R.drawable.style_1_pig,
            R.drawable.style_1_pigeon,
            R.drawable.style_1_rat,
            R.drawable.style_1_tiger,
            R.drawable.style_1_turtle
        )
        //endregion
        //region collection2
        val collection2 = listOf(
            // 14
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
        //endregion
        //region collection3
        val collection3 = listOf(
            // 14
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
        //endregion

        return prepareCollection(
            collection = when(cardStyleState.value){
                is CardStyleState.Style1 -> {
                    collection1
                }
                is CardStyleState.Style2 -> {
                    collection2
                }
                is CardStyleState.Style3 -> {
                    collection3
                }
            },
            gameSettingsState = gameSettingsState
        )
    }

    //region prepareCollection
    private fun prepareCollection(collection: List<Int>, gameSettingsState: GameSettingsState): List<Card> {
        val papa = R.drawable.papa
        val mama = R.drawable.mama

        val specialCollection = mutableListOf<Card>()
        val regularCollection = mutableListOf<Card>().apply { // 4
            add(Card(resourceId = papa, id = 1))
            add(Card(resourceId = papa, id = 2))
            add(Card(resourceId = mama, id = 1))
            add(Card(resourceId = mama, id = 2))
        }

        val collectionUsed = collection.shuffled()

        when (gameSettingsState) {
            is GameSettingsState.Easy -> { // Максимальное количество 12 (-4)
                for (i in 1..(gameSettingsState.numberCells - 4) / 2) {
                    regularCollection.add(Card(resourceId = collectionUsed[i], id = 1))
                    regularCollection.add(Card(resourceId = collectionUsed[i], id = 2))
                }
            }

            is GameSettingsState.Medium -> { // Максимальное количество 16 (-4)
                for (i in 1..(gameSettingsState.numberCells - 4) / 2) {
                    regularCollection.add(Card(resourceId = collectionUsed[i], id = 1))
                    regularCollection.add(Card(resourceId = collectionUsed[i], id = 2))
                }
            }

            is GameSettingsState.Hard -> { // Максимальное количество 20 (-4)
                for (i in 1..(gameSettingsState.numberCells - 4) / 2) {
                    regularCollection.add(Card(resourceId = collectionUsed[i], id = 1))
                    regularCollection.add(Card(resourceId = collectionUsed[i], id = 2))
                }
            }

            is GameSettingsState.Special -> { // Максимальное количество 20
                for (i in 1..gameSettingsState.numberCells / 2) {
                    specialCollection.add(Card(resourceId = collectionUsed[i], id = 1))
                    specialCollection.add(Card(resourceId = collectionUsed[i], id = 2))
                }
                return specialCollection.shuffled()
            }
        }

        return regularCollection.shuffled()
    }
    //endregion

}