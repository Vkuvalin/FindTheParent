package com.kuvalin.findtheparent.presentation

import androidx.lifecycle.ViewModel
import com.kuvalin.findtheparent.domain.usecase.AddAppInitLoadStateUseCase
import com.kuvalin.findtheparent.domain.usecase.AddCardStyleStateUseCase
import com.kuvalin.findtheparent.domain.usecase.AddFatherPhotoCardUseCase
import com.kuvalin.findtheparent.domain.usecase.AddGameScoreUseCase
import com.kuvalin.findtheparent.domain.usecase.AddMatherPhotoCardUseCase
import com.kuvalin.findtheparent.domain.usecase.GetAppInitLoadStateUseCase
import com.kuvalin.findtheparent.domain.usecase.GetCardListUseCase
import com.kuvalin.findtheparent.domain.usecase.GetCardStyleStateUseCase
import com.kuvalin.findtheparent.domain.usecase.GetFatherPhotoCardUseCase
import com.kuvalin.findtheparent.domain.usecase.GetGameScoreUseCase
import com.kuvalin.findtheparent.domain.usecase.GetMatherPhotoCardUseCase

import javax.inject.Inject

class AppViewModel @Inject constructor(
    private val addGameScoreUseCase: AddGameScoreUseCase,
    private val getGameScoreUseCase: GetGameScoreUseCase,
    private val getCardListUseCase: GetCardListUseCase,
    private val addFatherPhotoCardUseCase: AddFatherPhotoCardUseCase,
    private val addMatherPhotoCardUseCase: AddMatherPhotoCardUseCase,
    private val getCardStyleStateUseCase: GetCardStyleStateUseCase,
    private val getFatherPhotoCardUseCase: GetFatherPhotoCardUseCase,
    private val getMatherPhotoCardUseCase: GetMatherPhotoCardUseCase,
    private val addAppInitLoadStateUseCase: AddAppInitLoadStateUseCase,
    private val addCardStyleStateUseCase: AddCardStyleStateUseCase,
    private val getAppInitLoadStateUseCase: GetAppInitLoadStateUseCase,
): ViewModel() {

    val addGameScore = addGameScoreUseCase
    val getGameScore = getGameScoreUseCase
    val getCardList = getCardListUseCase
    val addFatherPhotoCard = addFatherPhotoCardUseCase
    val addMatherPhotoCard = addMatherPhotoCardUseCase
    val getCardStyleState = getCardStyleStateUseCase
    val getFatherPhotoCard = getFatherPhotoCardUseCase
    val getMatherPhotoCard = getMatherPhotoCardUseCase
    val addAppInitLoadState = addAppInitLoadStateUseCase
    val addCardStyleState = addCardStyleStateUseCase
    val getAppInitLoadState= getAppInitLoadStateUseCase

}