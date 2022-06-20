package minesweeper.domain.field

class Field(
    private val coordinate: Coordinate,
    val dot: Dot
) {
    fun findAroundCoordinates(): List<Coordinate> = coordinate.findAround()
}
