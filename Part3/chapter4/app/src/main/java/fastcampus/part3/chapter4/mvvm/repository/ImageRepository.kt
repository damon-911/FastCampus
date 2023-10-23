package fastcampus.part3.chapter4.mvvm.repository

import fastcampus.part3.chapter4.mvvm.model.Image
import io.reactivex.Single

interface ImageRepository {

    fun getRandomImage() : Single<Image>
}