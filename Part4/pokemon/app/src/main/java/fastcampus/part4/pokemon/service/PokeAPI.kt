package fastcampus.part4.pokemon.service

import fastcampus.part4.pokemon.data.PokemonResponse
import fastcampus.part4.pokemon.data.PokemonsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeAPI {
    @GET("pokemon/")
    suspend fun getPokemons(): PokemonsResponse

    // https://pokeapi.co/api/v2/pokemon/?offset=20&limit=20
    @GET("pokemon/")
    suspend fun getPokemons(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): PokemonsResponse

    @GET("pokemon/{pid}/")
    suspend fun getPokemon(@Path("pid") pid: Int): PokemonResponse
}