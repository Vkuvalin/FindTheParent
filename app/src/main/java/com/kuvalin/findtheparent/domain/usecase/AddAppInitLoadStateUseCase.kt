package com.kuvalin.findtheparent.domain.usecase


import com.kuvalin.findtheparent.domain.repository.CardListRepository
import com.kuvalin.findtheparent.generals.AppInitLoadState
import com.kuvalin.findtheparent.generals.CardStyleState
import com.kuvalin.findtheparent.generals.CardStyleState.Companion.cardStyleState
import javax.inject.Inject

class AddAppInitLoadStateUseCase @Inject constructor(
    private val cardListRepository: CardListRepository
) {
    suspend operator fun invoke(appInitLoadState: AppInitLoadState) = cardListRepository.addAppInitLoadState(appInitLoadState)
}