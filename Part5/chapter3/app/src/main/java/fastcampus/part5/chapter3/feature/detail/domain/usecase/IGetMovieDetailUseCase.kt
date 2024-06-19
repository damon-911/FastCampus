package fastcampus.part5.chapter3.feature.detail.domain.usecase

import fastcampus.part5.chapter3.feature.common.entity.MovieDetailEntity

interface IGetMovieDetailUseCase {
    suspend operator fun invoke(name: String): MovieDetailEntity
}