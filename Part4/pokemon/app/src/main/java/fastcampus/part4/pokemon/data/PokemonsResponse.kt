package fastcampus.part4.pokemon.data

data class PokemonsResponse(
    val count: Int,
    val previous: String?,
    val next: String?,
    val results: List<Result>
) {
    data class Result(
        val url: String,
        val name: String
    )
}