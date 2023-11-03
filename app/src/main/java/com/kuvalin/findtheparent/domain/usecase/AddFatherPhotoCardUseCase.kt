package com.kuvalin.findtheparent.domain.usecase


import android.net.Uri
import com.kuvalin.findtheparent.domain.repository.CardListRepository
import javax.inject.Inject

class AddFatherPhotoCardUseCase @Inject constructor(
    private val cardListRepository: CardListRepository
) {
    suspend operator fun invoke(resId: Int, imageUri: Uri? = null) = cardListRepository.addFatherPhotoCard(resId, imageUri)
}