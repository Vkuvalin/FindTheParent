package com.kuvalin.findtheparent.data.database


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kuvalin.findtheparent.data.model.CardDbModel
import com.kuvalin.findtheparent.data.model.CardStyleStateDbModel
import com.kuvalin.findtheparent.data.model.InitialLoadState
import com.kuvalin.findtheparent.data.model.ScoreDbModel
import com.kuvalin.findtheparent.generals.CardStyle
import com.kuvalin.findtheparent.generals.CardType


@Dao
interface CardListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addGameScore(score: ScoreDbModel)
    @Query("SELECT * FROM score")
    suspend fun getGameScore(): ScoreDbModel


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCardStyleState(cardStyleState: CardStyleStateDbModel)
    @Query("SELECT * FROM card_style_state")
    suspend fun getCardStyleState(): CardStyleStateDbModel


    // Узнать, как добавить всё ещё на этапе создания
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCardList(cardList: List<CardDbModel>)
    @Query("SELECT * FROM card_items WHERE style=:style AND type=:type")
    suspend fun getCardList(style: CardStyle, type: CardType): List<CardDbModel>



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMatherPhotoCard(card: CardDbModel)
    @Query("SELECT * FROM card_items WHERE type=:type LIMIT 1")
    suspend fun getMatherPhotoCard(type: CardType): CardDbModel



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFatherPhotoCard(card: CardDbModel)
    @Query("SELECT * FROM card_items WHERE type=:type LIMIT 1")
    suspend fun getFatherPhotoCard(type: CardType): CardDbModel


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addInitialLoadState(initialLoadState: InitialLoadState)
    @Query("SELECT * FROM initial_load_state")
    suspend fun getInitialLoadState(): InitialLoadState

}