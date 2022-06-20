package minesweeper.domain

import minesweeper.domain.field.Coordinate
import minesweeper.domain.field.CoordinateValue
import minesweeper.domain.field.Field
import minesweeper.domain.field.Mine
import minesweeper.domain.field.NonMine
import minesweeper.domain.vo.Height
import minesweeper.domain.vo.NumberOfMine
import minesweeper.domain.vo.Width

class MineField(
    private val _fields: MutableList<Field>
) {
    val fields: List<Field>
        get() = _fields.toList()

    init {
        require(_fields.isNotEmpty()) { "지뢰판은 비어있을수 없습니다." }
    }

    companion object {
        private const val START_INDEX = 0

        fun create(height: Height, width: Width, numberOfMine: NumberOfMine, mineCoordinateGenerator: MineCoordinateGenerator): MineField {
            require(height.value * width.value >= numberOfMine.value) { "지뢰 판 크기보다 지뢰 수가 많을수 없습니다." }

            val coordinates = generateCoordinates(height, width)
            val mineCoordinates = mineCoordinateGenerator.generate(coordinates, numberOfMine)

            val mineField = coordinates.map {
                if (it in mineCoordinates) {
                    Field(it, Mine)
                } else {
                    Field(it, NonMine(0))
                }
            }.let { MineField(it.toMutableList()) }

            mineField.calculateMineCount()
            return mineField
        }

        private fun generateCoordinates(
            height: Height,
            width: Width
        ): List<Coordinate> = (START_INDEX until height.value).flatMap { x ->
            (START_INDEX until width.value).map { y -> Coordinate(CoordinateValue(y), CoordinateValue(x)) }
        }
    }

    private fun calculateMineCount() {
        fields.map { it.findAroundCoordinates() }
    }
}
