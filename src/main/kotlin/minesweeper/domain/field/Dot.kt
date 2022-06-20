package minesweeper.domain.field

sealed interface Dot

object Mine : Dot

data class NonMine(val mineCount: Int) : Dot
