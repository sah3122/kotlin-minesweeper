package minesweeper.domain.field

sealed interface Dot

object Mine : Dot

data class NonMine(val mineCount: Int) : Dot {
    companion object {
        private const val DEFAULT_MINE_COUNT = 0

        val DEFAULT = NonMine(DEFAULT_MINE_COUNT)
    }
}
