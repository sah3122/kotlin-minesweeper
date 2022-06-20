package minesweeper.domain.field

data class Coordinate(
    val x: CoordinateValue,
    val y: CoordinateValue
) {
    fun findAround(): List<Coordinate> = listOf(east(), west(), south(), north(), northEast(), northWest(), southEast(), southWest()).filter { it.x.value >= 0 && it.y.value >= 0 }

    private fun east(): Coordinate = Coordinate(x + CoordinateValue.step(), y)

    private fun west(): Coordinate = Coordinate(x - CoordinateValue.step(), y)

    private fun south(): Coordinate = Coordinate(x, y + CoordinateValue.step())

    private fun north(): Coordinate = Coordinate(x, y - CoordinateValue.step())

    private fun northEast(): Coordinate = Coordinate(x + CoordinateValue.step(), y - CoordinateValue.step())

    private fun northWest(): Coordinate = Coordinate(x - CoordinateValue.step(), y - CoordinateValue.step())

    private fun southEast(): Coordinate = Coordinate(x + CoordinateValue.step(), y + CoordinateValue.step())

    private fun southWest(): Coordinate = Coordinate(x - CoordinateValue.step(), y + CoordinateValue.step())
}
