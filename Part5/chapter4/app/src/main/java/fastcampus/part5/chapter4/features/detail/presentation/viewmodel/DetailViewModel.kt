package fastcampus.part5.chapter4.features.detail.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fastcampus.part5.chapter4.features.detail.domain.usecase.IGetRestaurantDetailUseCase
import fastcampus.part5.chapter4.features.detail.presentation.input.IDetailViewModelInput
import fastcampus.part5.chapter4.features.detail.presentation.output.DetailState
import fastcampus.part5.chapter4.features.detail.presentation.output.DetailUiEffect
import fastcampus.part5.chapter4.features.detail.presentation.output.IDetailViewModelOutput
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getRestaurantDetailUseCase: IGetRestaurantDetailUseCase,
) : ViewModel(), IDetailViewModelInput, IDetailViewModelOutput {

    val input: IDetailViewModelInput = this
    val output: IDetailViewModelOutput = this

    private val _detailState: MutableStateFlow<DetailState> =
        MutableStateFlow(DetailState.Initial)
    override val detailState: StateFlow<DetailState>
        get() = _detailState

    private val _detailUiEffect = MutableSharedFlow<DetailUiEffect>(replay = 0)
    override val detailUiEffect: SharedFlow<DetailUiEffect>
        get() = _detailUiEffect

    suspend fun initDetail(id: Int) {
        _detailState.value = DetailState.Main(
            detailEntity = getRestaurantDetailUseCase(id)
        )
    }

    override fun goBackToFeed() {
        viewModelScope.launch {
            _detailUiEffect.emit(
                DetailUiEffect.Back
            )
        }
    }

    override fun googleMapClicked() {
//        viewModelScope.launch {
//            if (detailState.value is DetailState.Main) {
//                val value = detailState.value as DetailState.Main
//                _detailUiEffect.emit(
//                    DetailUiEffect.OpenUrl(
//                        value.detailEntity
//                    )
//                )
//            }
//        }
    }

    override fun rateClicked() {
        viewModelScope.launch {
            if (detailState.value is DetailState.Main) {
                val value = detailState.value as DetailState.Main
                _detailUiEffect.emit(
                    DetailUiEffect.RateRestaurant(
                        restaurantName = value.detailEntity.name,
                        rating = value.detailEntity.rating
                    )
                )
            }
        }
    }
}