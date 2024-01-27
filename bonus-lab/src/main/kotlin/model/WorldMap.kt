package model

import java.util.*

interface WorldMap {
    fun place(animal: Animal)

    fun isOccupied(position: Vector2d): Boolean

    fun objectAt(position: Vector2d): Animal?

    fun canMoveTo(position: Vector2d): Boolean

    fun getElements(): Set<Animal>
}
