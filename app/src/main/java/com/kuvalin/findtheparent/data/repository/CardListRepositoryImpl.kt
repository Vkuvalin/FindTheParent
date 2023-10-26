package com.kuvalin.findtheparent.data.repository


import android.content.Context
import android.util.Log
import com.kuvalin.findtheparent.R
import com.kuvalin.findtheparent.data.database.AppDatabase
import com.kuvalin.findtheparent.data.mapper.CardMapper
import com.kuvalin.findtheparent.domain.entity.Card
import com.kuvalin.findtheparent.domain.entity.Score
import com.kuvalin.findtheparent.domain.repository.CardListRepository
import com.kuvalin.findtheparent.generals.CardStyle
import com.kuvalin.findtheparent.generals.CardStyleState
import com.kuvalin.findtheparent.generals.CardType
import com.kuvalin.findtheparent.presentation.gamesettings.GameSettingsState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CardListRepositoryImpl(
    private val context: Context
//    private val cardListDao: CardListDao,
//    private val mapper: CardMapper
) : CardListRepository {

    private val cardListDao = AppDatabase.getInstance(context).cardListDao()
    private val mapper = CardMapper()

    private val scope = CoroutineScope(context = Dispatchers.Default)

    //region cardsCollectionStyle1
    val cardsCollectionStyle1 = listOf( // 16
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
    //region cardsCollectionStyle2
    val cardsCollectionStyle2 = listOf(
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
    //region cardsCollectionStyle3
    val cardsCollectionStyle3 = listOf(
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





    init {
        scope.launch {
            addCardList(cardsCollectionStyle1, CardStyle.STYLE1)
            addCardList(cardsCollectionStyle2, CardStyle.STYLE2)
            addCardList(cardsCollectionStyle3, CardStyle.STYLE3)

            // TODO  "Добавить сюда дефолтные аватарки мужчины и женщины"
            addFatherPhotoCard(R.drawable.papa)
            addMatherPhotoCard(R.drawable.mama)
        }
    }


    // ############ Реализация репозитория ############
    // Score
    override suspend fun addGameScore(score: Score) {
        cardListDao.addGameScore(mapper.mapEntityToDbModelScore(score))
    }

    override suspend fun getGameScore(): Score {
        return mapper.mapDbModelToEntityScore(cardListDao.getGameScore())
    }


    // CardStyleState
    // А это же мне нужно, да?
    override suspend fun addCardStyleState(cardStyleState: CardStyleState) {
        cardListDao.addCardStyleState(mapper.mapEntityToDbModelCardStyle(cardStyleState))
    }

    override suspend fun getCardStyleState(): CardStyleState {
        return mapper.mapDbModelToEntityScore(cardListDao.getCardStyleState())
    }


    // CardList (Да-да, можно было обойтись без БД, но сделал чисто для тренировки)
    private suspend fun addCardList(list: List<Int>, style: CardStyle) {

        val cardList = mutableListOf<Card>()
        list.forEach {
            cardList.add(
                Card(
                    resourceId = it,
                    style = style
                )
            )
        }

        cardListDao.addCardList(mapper.mapListEntityToListDbModel(cardList))
    }

    override suspend fun getCardList(
        style: CardStyle,
        type: CardType,
        gameSettingsState: GameSettingsState
    ): List<Card> {
        val collection = mapper.mapListDbModelToListEntity(cardListDao.getCardList(style, type))

        return prepareCollection(collection = collection, gameSettingsState = gameSettingsState)
    }


    // Mather
    override suspend fun addMatherPhotoCard(resId: Int) {
        cardListDao.addMatherPhotoCard(
            mapper.mapEntityToDbModelCard(
                Card(
                    resourceId = resId,
                    type = CardType.MATHER,
                    style = CardStyle.NULL
                )
            )
        )
    }

    override suspend fun getMatherPhotoCard(type: CardType): Card {
        return mapper.mapDbModelToEntityCard(cardListDao.getMatherPhotoCard(type))
    }


    // Father
    override suspend fun addFatherPhotoCard(resId: Int) {
        cardListDao.addFatherPhotoCard(
            mapper.mapEntityToDbModelCard(
                Card(
                    resourceId = resId,
                    type = CardType.FATHER,
                    style = CardStyle.NULL
                )
            )
        )
    }

    override suspend fun getFatherPhotoCard(type: CardType): Card {
        return mapper.mapDbModelToEntityCard(cardListDao.getFatherPhotoCard(type))
    }


    // ############ Вспомогательная часть ############

    //region prepareCollection
    private suspend fun prepareCollection(
        collection: List<Card>,
        gameSettingsState: GameSettingsState
    ): List<Card> {
        val papa = getFatherPhotoCard(CardType.FATHER)
        val mama = getFatherPhotoCard(CardType.MATHER)

        val specialCollection = mutableListOf<Card>()
        val regularCollection = mutableListOf<Card>().apply { // 4
            add(papa)
            add(papa)
            add(mama)
            add(mama)
        }

        val collectionUsed = collection.shuffled()

        when (gameSettingsState) {
            is GameSettingsState.Easy -> { // Максимальное количество 12 (-4)
                for (i in 1..(gameSettingsState.numberCells - 4) / 2) {
                    repeat(2) {
                        regularCollection.add(collectionUsed[i])
                    }
                }
            }

            is GameSettingsState.Medium -> { // Максимальное количество 16 (-4)
                for (i in 1..(gameSettingsState.numberCells - 4) / 2) {
                    repeat(2) {
                        regularCollection.add(collectionUsed[i])
                    }
                }
            }

            is GameSettingsState.Hard -> { // Максимальное количество 20 (-4)
                for (i in 1..(gameSettingsState.numberCells - 4) / 2) {
                    repeat(2) {
                        regularCollection.add(collectionUsed[i])
                    }
                }
            }

            is GameSettingsState.Special -> { // Максимальное количество 20
                for (i in 1..gameSettingsState.numberCells / 2) {
                    repeat(2) {
                        regularCollection.add(collectionUsed[i])
                    }
                }
                return specialCollection.shuffled()
            }
        }

        return regularCollection.shuffled()
    }
    //endregion

}















// ############ ЧУЛАН ############

//override fun getCardList( style: CardStyle, type: CardType): LiveData<List<Card>> = MediatorLiveData<List<Card>>().apply {
//    addSource(cardListDao.getCardList(style, type)) {
//        value = mapper.mapListDbModelToListEntity(it)
//    }
//}