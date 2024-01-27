package model

import java.util.*
import kotlin.collections.HashMap

class BouncyMap(private val width: Int, private val height: Int) : WorldMap {
    private val animals: MutableMap<Vector2d, Animal> = HashMap()

    override fun place(animal: Animal) {
        var newPosition = animal.getPosition()

        while (freeSpaces() > 0) {
            if (canMoveTo(newPosition)) {
                animals[newPosition] = animal
                return
            } else {
                newPosition = findEmptyPosition(animal.getPosition())
            }
        }

        val randomAnimal = animals.values.elementAt(Random().nextInt(animals.size))
        animals.remove(randomAnimal.getPosition())
        animals[animal.getPosition()] = animal
    }

    private fun findEmptyPosition(originalPosition: Vector2d): Vector2d {
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