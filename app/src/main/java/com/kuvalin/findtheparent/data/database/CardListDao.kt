package com.kuvalin.findtheparent.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kuvalin.findtheparent.data.CardStyle
import com.kuvalin.findtheparent.data.model.CardDbModel
import com.kuvalin.findtheparent.data.model.CardStyleStateDbModel
import com.kuvalin.findtheparent.data.model.ScoreDbModel
import com.kuvalin.findtheparent.generals.CardStyle
import com.kuvalin.findtheparent.generals.CardType


@Dao
interface CardListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addGameScoreUseCase(score: ScoreDbModel)
    @Query("SELECT * FROM score")
    fun getGameScoreUseCase(): ScoreDbModel


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCardStyleState(cardStyleState: CardStyleStateDbModel)
    @Query("SELECT * FROM card_style_state")
    fun getCardStyleState(): CardStyleStateDbModel


    // Узнать, как добавить всё ещё на этапе создания
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCardList(style: CardStyle, type: CardType): List<CardDbModel>
    @Query("SELECT * FROM card_items WHERE style=:style AND type=:type")
    fun getCardList(style: CardStyle, type: CardType): LiveData<List<CardDbModel>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)   // ПРАВИЛЬНЫЙ?
    fun addMatherPhotoCardUseCase(resId: Int)
    @Query("SELECT * FROM card_items WHERE type=:type LIMIT 1")
    fun getMatherPhotoCardUseCase(type: CardType): Int



    @Insert(onConflict = OnConflictStrategy.REPLACE)  // ПРАВИЛЬНЫЙ?
    fun addFatherPhotoCardUseCase(resId: Int)
    @Query("SELECT * FROM card_items WHERE type=:type LIMIT 1")
    fun getFatherPhotoCardUseCase(type: CardType): Int

}