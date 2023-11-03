package com.kuvalin.findtheparent.domain.usecase


import com.kuvalin.findtheparent.domain.repository.CardListRepository
import com.kuvalin.findtheparent.generals.AppInitLoadState
import com.kuvalin.findtheparent.generals.CardStyleState
import javax.inject.Inject

class GetAppInitLoadStateUseCase @Inject constructor(
    private val cardListRepository: CardListRepository
) {
    suspend operator fun invoke(): AppInitLoadState = cardListRepository.getAppInitLoadState()
}