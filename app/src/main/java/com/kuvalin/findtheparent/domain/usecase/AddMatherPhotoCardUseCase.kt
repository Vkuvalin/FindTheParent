package com.kuvalin.findtheparent.domain.usecase


import android.net.Uri
import com.kuvalin.findtheparent.domain.repository.CardListRepository

class AddMatherPhotoCardUseCase(
    private val cardListRepository: CardListRepository
) {
    suspend operator fun invoke(resId: Int, imageUri: Uri? = null) = cardListRepository.addMatherPhotoCard(resId, imageUri)
}