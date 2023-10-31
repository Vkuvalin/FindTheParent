package com.kuvalin.findtheparent.data.mapper

import android.util.Log
import com.kuvalin.findtheparent.data.model.CardDbModel
import com.kuvalin.findtheparent.data.model.CardStyleStateDbModel
import com.kuvalin.findtheparent.data.model.ScoreDbModel
import com.kuvalin.findtheparent.domain.entity.Card
import com.kuvalin.findtheparent.domain.entity.Card.Companion.UNDEFINED_ID
import com.kuvalin.findtheparent.domain.entity.Score
import com.kuvalin.findtheparent.generals.CardStyleState
import com.kuvalin.findtheparent.generals.CardType

class CardMapper {

    // Score
    fun mapEntityToDbModelScore(score: Score) : ScoreDbModel {
        return ScoreDbModel(
            id = score.id,
            mama = score.mama,
            papa = score.papa
        )
    }
    fun mapDbModelToEntityScore(scoreDbModel: ScoreDbModel) : Score {
        return Score(
            id = scoreDbModel.id,
            mama = scoreDbModel.mama,
            papa = scoreDbModel.papa
        )
    }



    // CardStyleState
    fun mapEntityToDbModelCardStyle(cardStyleState: CardStyleState) : CardStyleStateDbModel {
        return CardStyleStateDbModel(UNDEFINED_ID, cardStyleState)
    }
    fun mapDbModelToEntityScore(cardStyleStateDbModel: CardStyleStateDbModel) : CardStyleState {
        return cardStyleStateDbModel.cardStyleState
    }



    // Card
    fun mapDbModelToEntityCard(cardDbModel: CardDbModel) : Card {
        return Card(
            id = cardDbModel.id,
            resourceId = cardDbModel.resourceId,
            style = cardDbModel.style,
            type = cardDbModel.type,
            imageUri = cardDbModel.imageUri
        )
    }
    fun mapListDbModelToListEntity(list: List<CardDbModel>) = list.map {
        mapDbModelToEntityCard(it)
    }
    fun mapEntityToDbModelCard(card: Card) : CardDbModel {

        if (card.type == CardType.FATHER || card.type == CardType.MATHER) {
            Log.d("recomposition", "-----------------------")
            Log.d("recomposition", card.toString())
            Log.d("recomposition", "-----------------------")
        }


        return CardDbModel(
            id = card.id,
            resourceId = card.resourceId,
            style = card.style,
            type = card.type,
            imageUri = card.imageUri
        )
    }
    fun mapListEntityToListDbModel(list: List<Card>) = list.map {
        mapEntityToDbModelCard(it)
    }

}