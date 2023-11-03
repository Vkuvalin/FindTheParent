package com.kuvalin.findtheparent.data.database


import android.content.Context
import android.util.Log
import androidx.compose.material3.Card
import androidx.compose.runtime.DisallowComposableCalls
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.kuvalin.findtheparent.R
import com.kuvalin.findtheparent.data.mapper.CardMapper
import com.kuvalin.findtheparent.data.model.AppInitLoadStateConverter
import com.kuvalin.findtheparent.data.model.AppInitLoadStateDbModel
import com.kuvalin.findtheparent.data.model.CardDbModel
import com.kuvalin.findtheparent.data.model.CardStyleStateConverter
import com.kuvalin.findtheparent.data.model.CardStyleStateDbModel
import com.kuvalin.findtheparent.data.model.InitialLoadState
import com.kuvalin.findtheparent.data.model.ScoreDbModel
import com.kuvalin.findtheparent.data.model.UriTypeConverter
import com.kuvalin.findtheparent.domain.entity.Card
import com.kuvalin.findtheparent.generals.AppInitLoadState
import com.kuvalin.findtheparent.generals.CardStyle
import com.kuvalin.findtheparent.generals.CardType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


// TODO Правильно ли так делать?
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
var cardId = 1
private val mapper = CardMapper()
private suspend fun addCardList(list: List<Int>, style: CardStyle, dao: CardListDao) {

    val cardList = mutableListOf<Card>()
    list.forEach {
        cardList.add(
            Card(
                id = cardId,
                resourceId = it,
                style = style
            )
        )
        cardId++
    }

    dao.addCardList(mapper.mapListEntityToListDbModel(cardList))
}



@Database(entities = [
    CardDbModel::class,
    ScoreDbModel::class,
    CardStyleStateDbModel::class,
    InitialLoadState::class,
    AppInitLoadStateDbModel::class
                     ],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    CardStyleStateConverter::class,
    UriTypeConverter::class,
    AppInitLoadStateConverter::class
)
abstract class AppDatabase: RoomDatabase() {
    companion object {

        private var INSTANCE: AppDatabase? = null
        private val LOCK = Any() // Базы данных должны быть синхронизированы
        private const val DB_NAME = "card_item.db"
        private val scope = CoroutineScope(Dispatchers.IO)

        fun getInstance(context: Context): AppDatabase {

            INSTANCE?.let {
                return it
            }
            //region Зачем нужен synchronized
            /*
                1. Если вдруг 2 потока одновременно вызвали данный метод (getInstance)
                2. Оба потока сделали проверку выше и оказалось, что INSTANCE == null
                3. Тогда оба потока дальше дойдут до synchronized и ОДИН из них войдет в данный блок
                4. И если не повторить проверку, то может возникнуть ситуация, когда будут созданы 2
                экземпляра базы данных.
                * Данная проверка называется "Дабл Чек"

                *** Аннотация @Synchronized - работает по другому (добавлю комментарий Сумина):
                    В данном примере если бд уже была проинициализирована, то её экземпляр могут
                    получить все потоки одновременно, а в случае с @Synchronized каждый поток
                    должен будет ждать, пока другой поток освободит монитор.
            */
            //endregion
            synchronized(LOCK) {
                INSTANCE?.let {
                    return it
                }
                val db = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    DB_NAME
                ).addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        scope.launch {
                            val dao = getInstance(context).cardListDao()
                            dao.addInitialLoadState(InitialLoadState(false))
                            dao.addAppInitLoadState(AppInitLoadStateDbModel(Card.UNDEFINED_ID, AppInitLoadState.Initial))
                            dao.addGameScore(ScoreDbModel(Card.UNDEFINED_ID, 0, 0))


                            dao.addMatherPhotoCard(
                                CardDbModel(
                                    id = 777,
                                    resourceId = R.drawable.mama,
                                    type = CardType.MATHER,
                                    style = CardStyle.NULL,
                                    imageUri = null
                                )
                            )
                            dao.addFatherPhotoCard(
                                CardDbModel(
                                    id = 888,
                                    resourceId = R.drawable.papa,
                                    type = CardType.FATHER,
                                    style = CardStyle.NULL,
                                    imageUri = null
                                )
                            )

                            addCardList(cardsCollectionStyle1, CardStyle.STYLE1, dao)
                            addCardList(cardsCollectionStyle2, CardStyle.STYLE2, dao)
                            addCardList(cardsCollectionStyle3, CardStyle.STYLE3, dao)

                        }
                    }
                })
                    .build()
                INSTANCE = db
                return db // Возвращается именно db, потому что INSTANCE "нулабельная".
            }
        }
    }

    abstract fun cardListDao(): CardListDao
}