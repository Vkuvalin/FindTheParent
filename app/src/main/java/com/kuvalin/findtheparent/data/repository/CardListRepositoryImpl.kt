package com.kuvalin.findtheparent.data.repository


import android.content.Context
import android.net.Uri
import android.util.Log
import com.kuvalin.findtheparent.R
import com.kuvalin.findtheparent.data.database.AppDatabase
import com.kuvalin.findtheparent.data.database.cardId
import com.kuvalin.findtheparent.data.database.cardsCollectionStyle1
import com.kuvalin.findtheparent.data.database.cardsCollectionStyle2
import com.kuvalin.findtheparent.data.database.cardsCollectionStyle3
import com.kuvalin.findtheparent.data.mapper.CardMapper
import com.kuvalin.findtheparent.data.model.InitialLoadState
import com.kuvalin.findtheparent.domain.entity.Card
import com.kuvalin.findtheparent.domain.entity.Score
import com.kuvalin.findtheparent.domain.repository.CardListRepository
import com.kuvalin.findtheparent.generals.CardStyle
import com.kuvalin.findtheparent.generals.CardStyleState
import com.kuvalin.findtheparent.generals.CardType
import com.kuvalin.findtheparent.presentation.gamesettings.GameSettingsState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CardListRepositoryImpl(
    private val context: Context
) : CardListRepository {

    private val cardListDao = AppDatabase.getInstance(context).cardListDao()
    private val mapper = CardMapper()



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


    override suspend fun getCardList(
        style: CardStyle,
        type: CardType,
        gameSettingsState: GameSettingsState
    ): List<Card> {
        val collection = mapper.mapListDbModelToListEntity(cardListDao.getCardList(style, type))

        return prepareCollection(collection = collection, gameSettingsState = gameSettingsState)
    }


    // Mather
    override suspend fun addMatherPhotoCard(resId: Int, imageUri: Uri?) {
        cardListDao.addMatherPhotoCard(
            mapper.mapEntityToDbModelCard(
                Card(
                    id = 777,
                    resourceId = resId,
                    type = CardType.MATHER,
                    style = CardStyle.NULL,
                    imageUri = imageUri
                )
            )
        )
    }

    override suspend fun getMatherPhotoCard(type: CardType): Card {
//        Log.d("recomposition", "repositoryMather id ${cardListDao.getMatherPhotoCard(type).id}")
//        Log.d(
//            "recomposition",
//            "repositoryMather imageUri ${cardListDao.getMatherPhotoCard(type).imageUri}"
//        )
//        Log.d(
//            "recomposition",
//            "repositoryMather resourceId ${cardListDao.getMatherPhotoCard(type).resourceId}"
//        )
        return mapper.mapDbModelToEntityCard(cardListDao.getMatherPhotoCard(type))
    }


    // Father
    override suspend fun addFatherPhotoCard(resId: Int, imageUri: Uri?) {
        cardListDao.addFatherPhotoCard(
            mapper.mapEntityToDbModelCard(
                Card(
                    id = 888,
                    resourceId = resId,
                    type = CardType.FATHER,
                    style = CardStyle.NULL,
                    imageUri = imageUri
                )
            )
        )
    }

    override suspend fun getFatherPhotoCard(type: CardType): Card {
//        Log.d("recomposition", "repositoryFather id ${cardListDao.getFatherPhotoCard(type).id}")
//        Log.d(
//            "recomposition",
//            "repositoryFather imageUri ${cardListDao.getFatherPhotoCard(type).imageUri}"
//        )
//        Log.d(
//            "recomposition",
//            "repositoryFather resourceId ${cardListDao.getFatherPhotoCard(type).resourceId}"
//        )
        return mapper.mapDbModelToEntityCard(cardListDao.getFatherPhotoCard(type))
    }


    // ############ Вспомогательная часть ############

    //region prepareCollection
    private suspend fun prepareCollection(
        collection: List<Card>,
        gameSettingsState: GameSettingsState
    ): List<Card> {
        val papa = getFatherPhotoCard(CardType.FATHER) // TODO
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