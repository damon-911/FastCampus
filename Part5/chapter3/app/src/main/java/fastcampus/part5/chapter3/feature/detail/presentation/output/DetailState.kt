package fastcampus.part5.chapter3.feature.detail.presentation.output

import fastcampus.part5.chapter3.feature.common.entity.MovieDetailEntity

sealed class DetailState {
    object Initial : DetailState()
    class Main(val movieDetailEntity: MovieDetailEntity) : DetailState()
}