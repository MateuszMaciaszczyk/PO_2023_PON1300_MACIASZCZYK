package model

import java.util.*
import kotlin.collections.HashMap

class BouncyMap(private val width: Int, private val height: Int) : WorldMap {
    private val animals: MutableMap<Vector2d, Animal> = HashMap()

    override fun place(animal: Animal) {
        if (!animals.containsValue(animal)) {
            while (freeSpaces() > 0) {
                if (canMoveTo(animal.getPosition()) and !isOccupied(animal.getPosition())) {
                    animals[animal.getPosition()] = animal
                    return
                } else {
                    animal.setPosition(findEmptyPosition())
                }
            }
        }

        var oldPosition = animal.getPosition()
        animal.move(orientationToDirection(animal.getOrientation()), this)
        var newPosition = animal.getPosition()

        while (freeSpaces() > 0) {
            if (canMoveTo(newPosition) and !isOccupied(newPosition)) {
                animals.remove(oldPosition)
                animals[newPosition] = animal
                animal.setPosition(newPosition)
                return
            } else {
                newPosition = findEmptyPosition()
            }
        }
    }

    private fun orientationToDirection(orientation: MapDirection): MoveDirection {
        return when (orientation) {
            MapDirection.NORTH -> MoveDirection.FORWARD
            MapDirection.SOUTH -> MoveDirection.BACKWARD
            MapDirection.EAST -> MoveDirection.RIGHT
            MapDirection.WEST -> MoveDirection.LEFT
        }
    }

    private fun findEmptyPosition(): Vector2d {
        val x = Random().nextInt(width)
        val y = Random().nextInt(height)
        return Vector2d(x, y)
    }

    override fun isOccupied(position: Vector2d): Boolean {
        return animals.containsKey(position)
    }

    override fun objectAt(position: Vector2d): Animal? {
        return animals[position]
    }

    override fun canMoveTo(position: Vector2d): Boolean {
        return position.x in 0..<width && position.y in 0..<height
    }

    override fun getElements(): Set<Animal> {
        return animals.values.toSet()
    }

    fun freeSpaces(): Int {
        return width * height - animals.size
    }
}