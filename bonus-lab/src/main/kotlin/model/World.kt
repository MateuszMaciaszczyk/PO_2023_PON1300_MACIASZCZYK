package model

fun main() {
    val map = mapOf(Vector2d(0, 0) to "value1", Vector2d(1, 1) to "value2")
    val mapSize = Vector2d(3, 3)

    val randomPosition = map.randomPosition()
    println("Random position: $randomPosition")

    val randomFreePosition = map.randomFreePosition(mapSize)
    println("Random free position: $randomFreePosition")
}