package fastcampus.part5.chapter3.feature.common.entity

sealed class EntityWrapper<T> {
    data class Success<T>(val entity: T) : EntityWrapper<T>()
    data class Fail<T>(val error: Throwable) : EntityWrapper<T>()
}
