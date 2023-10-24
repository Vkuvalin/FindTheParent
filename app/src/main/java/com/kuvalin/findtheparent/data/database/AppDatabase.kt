package com.kuvalin.findtheparent.data.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kuvalin.findtheparent.data.model.CardDbModel
import com.kuvalin.findtheparent.data.model.CardStyleStateDbModel
import com.kuvalin.findtheparent.data.model.ScoreDbModel


@Database(entities = [CardDbModel::class, ScoreDbModel::class, CardStyleStateDbModel::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun cardListDao(): CardListDao

    companion object {

        private var INSTANCE: AppDatabase? = null
        private val LOCK = Any() // Базы данных должны быть синхронизированы
        private const val DB_NAME = "card_item.db"

        fun getInstance(application: Application): AppDatabase {

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
                    application,
                    AppDatabase::class.java,
                    DB_NAME
                ).build()
                INSTANCE = db
                return db // Возвращается именно db, потому что INSTANCE "нулабельная".
            }
        }

    }
}