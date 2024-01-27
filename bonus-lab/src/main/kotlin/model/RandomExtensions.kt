package model

import kotlin.random.Random

fun Map<Vector2d, *>.randomPosition(): Vector2d? {
    return if (this.isNotEmpty()) {
        keys.random(Random)
    } else {
        null
    }
}

fun Map<Vector2d, *>.randomFreePosition(mapSize: Vector2d): Vector2d? {
    val allPossiblePositions = (0..<mapSize.x).flatMap { x ->
        (0..<mapSize.y).map { y -> Vector2d(x, y) }
    }

    val freePositions = allPossiblePositions.filter { it !in this.keys }

    return if (freePositions.isNotEmpty()) {
        freePositions.random(Random)
    } else {
        null
    }
}
