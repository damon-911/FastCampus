package fastcampus.part4.pokemon.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import fastcampus.part4.pokemon.data.PokemonResponse
import fastcampus.part4.pokemon.data.PokemonsResponse
import fastcampus.part4.pokemon.service.PokeAPI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val pokeAPI: PokeAPI
) : ViewModel() {

    val pokemonList: Flow<PagingData<PokemonsResponse.Result>> =
        getPokemons().cachedIn(viewModelScope)

    var pokemonResult by mutableStateOf(
        PokemonResponse(
            PokemonResponse.Species(""),
            PokemonResponse.Sprites("")
        )
    )

    private fun getPokemons(): Flow<PagingData<PokemonsResponse.Result>> = Pager(
        config = PagingConfig(
            pageSize = 20,
            enablePlaceholders = true,
            prefetchDistance = 5
        ),
        pagingSourceFactory = {
            object : PagingSource<Int, PokemonsResponse.Result>() {
                override fun getRefreshKey(state: PagingState<Int, PokemonsResponse.Result>): Int? {
                    return state.anchorPosition
                }

                override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonsResponse.Result> {
                    try {
                        val pokemons = if (params.key != null) {
                            pokeAPI.getPokemons(params.key as Int, params.loadSize)
                        } else {
                            pokeAPI.getPokemons()
                        }
                        return LoadResult.Page(
                            data = pokemons.results,
                            prevKey = pokemons.previous?.substringAfter("offset=")
                                ?.substringBefore("&")?.toInt(),
                            nextKey = pokemons.next?.substringAfter("offset=")
                                ?.substringBefore("&")?.toInt()
                        )
                    } catch (e: Exception) {
                        Log.e("PokemonViewModel", "error: $e")
                        e.printStackTrace()
                        return LoadResult.Error(e)
                    }
                }
            }
        }
    ).flow

    fun getPokemon(pokemonId: Int) {
        viewModelScope.launch {
            pokemonResult = pokeAPI.getPokemon(pokemonId)
        }
    }
}