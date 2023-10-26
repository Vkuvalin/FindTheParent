package com.kuvalin.findtheparent.data.database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kuvalin.findtheparent.data.model.CardDbModel
import com.kuvalin.findtheparent.data.model.CardStyleStateConverter
import com.kuvalin.findtheparent.data.model.CardStyleStateDbModel
import com.kuvalin.findtheparent.data.model.ScoreDbModel


@Database(entities = [CardDbModel::class, ScoreDbModel::class, CardStyleStateDbModel::class], version = 1, exportSchema = false)
@TypeConverters(CardStyleStateConverter::class)
abstract class AppDatabase: RoomDatabase() {
    companion object {

        private var INSTANCE: AppDatabase? = null
        private val LOCK = Any() // Базы данных должны быть синхронизированы
        private const val DB_NAME = "card_item.db"

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
                ).build()
                INSTANCE = db
                return db // Возвращается именно db, потому что INSTANCE "нулабельная".
            }
        }
    }

    abstract fun cardListDao(): CardListDao
}