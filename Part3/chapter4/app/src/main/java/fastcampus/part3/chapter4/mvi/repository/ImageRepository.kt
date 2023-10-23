package fastcampus.part3.chapter4.mvi.repository

import fastcampus.part3.chapter4.mvi.model.Image

interface ImageRepository {

    suspend fun getRandomImage() : Image
}