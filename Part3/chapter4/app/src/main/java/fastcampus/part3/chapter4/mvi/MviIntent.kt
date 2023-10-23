package fastcampus.part3.chapter4.mvi

sealed class MviIntent {
    object LoadImage : MviIntent()
}