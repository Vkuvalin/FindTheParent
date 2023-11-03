package com.kuvalin.findtheparent.data.repository


import android.content.Context
import android.net.Uri
import com.kuvalin.findtheparent.data.database.AppDatabase
import com.kuvalin.findtheparent.data.database.CardListDao
import com.kuvalin.findtheparent.data.mapper.CardMapper
import com.kuvalin.findtheparent.data.model.InitialLoadState
import com.kuvalin.findtheparent.domain.entity.Card
import com.kuvalin.findtheparent.domain.entity.Score
import com.kuvalin.findtheparent.domain.repository.CardListRepository
import com.kuvalin.findtheparent.generals.AppInitLoadState
import com.kuvalin.findtheparent.generals.CardStyle
import com.kuvalin.findtheparent.generals.CardStyleState
import com.kuvalin.findtheparent.generals.CardType
import com.kuvalin.findtheparent.presentation.gamesettings.GameSettingsState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class CardListRepositoryImpl @Inject constructor(
    private val context: Context,
    private val cardListDao: CardListDao,
    private val mapper: CardMapper
) : CardListRepository {

//    private val cardListDao: CardListDao = AppDatabase.getInstance(context).cardListDao()
//    private val mapper = CardMapper()
    private val scope = CoroutineScope(Dispatchers.Default)
    private var loadState = false


    init {
        if (!loadState) {
            scope.launch {
                AppDatabase.getInstance(context).cardListDao()
                    .addInitialLoadState(InitialLoadState(true))
                delay(500)
                loadState = cardListDao.getInitialLoadState().initialLoadState
            }
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
    override suspend fun addCardStyleState(cardStyleState: CardStyleState) {
        cardListDao.addCardStyleState(mapper.mapEntityToDbModelCardStyle(cardStyleState))
    }

    override suspend fun getCardStyleState(): CardStyleState {
        return mapper.mapDbModelToEntityCardStyle(cardListDao.getCardStyleState())
    }


    // AppInitLoadState
    override suspend fun addAppInitLoadState(appInitLoadState: AppInitLoadState) {
        cardListDao.addAppInitLoadState(mapper.mapEntityToDbModelAppInitLoadState(appInitLoadState))
    }

    override suspend fun getAppInitLoadState(): AppInitLoadState {
        delay(5000)
        return mapper.mapDbModelToEntityAppInitLoadState(cardListDao.getAppInitLoadState())
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
                        specialCollection.add(collectionUsed[i])
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