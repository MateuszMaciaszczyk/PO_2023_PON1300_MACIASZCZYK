import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import model.Animal
import model.BouncyMap
import model.MoveDirection
import model.Vector2d

class BouncyMapTest : ShouldSpec({

    val mapWidth = 5
    val mapHeight = 5

    should("place an animal on an empty spot") {
        val bouncyMap = BouncyMap(mapWidth, mapHeight)
        val animal = Animal(Vector2d(2, 2))

        bouncyMap.place(animal)

        bouncyMap.isOccupied(animal.getPosition()) shouldBe true
    }

    should("place an animal on another animal") {
        val bouncyMap = BouncyMap(mapWidth, mapHeight)
        val animal1 = Animal(Vector2d(2, 2))
        val animal2 = Animal(Vector2d(2, 3))
        bouncyMap.place(animal1)
        bouncyMap.place(animal2)

        bouncyMap.place(Animal(Vector2d(2, 3))) // Trigger collision

        bouncyMap.isOccupied(animal1.getPosition()) shouldBe true
        bouncyMap.isOccupied(animal2.getPosition()) shouldBe true
    }

    should("move an animal within the map boundaries") {
        val bouncyMap = BouncyMap(mapWidth, mapHeight)
        val animal = Animal(Vector2d(2, 2))
        bouncyMap.place(animal)

        bouncyMap.place(animal)

        bouncyMap.isOccupied(Vector2d(2, 3)) shouldBe true
        bouncyMap.isOccupied(Vector2d(2, 2)) shouldBe false
    }

    should("not move an animal outside the map boundaries") {
        val bouncyMap = BouncyMap(mapWidth, mapHeight)
        val animal = Animal(Vector2d(mapWidth - 1, mapHeight - 1))
        bouncyMap.place(animal)

        animal.move( MoveDirection.FORWARD, bouncyMap)

        bouncyMap.isOccupied(Vector2d(mapWidth - 1, mapHeight - 1)) shouldBe true
    }

    should("handle collision by bouncing off to a random position") {
        val bouncyMap = BouncyMap(mapWidth, mapHeight)
        val animal1 = Animal(Vector2d(2, 2))
        val animal2 = Animal(Vector2d(2, 3))
        bouncyMap.place(animal1)
        bouncyMap.place(animal2)

        bouncyMap.place(animal1) // Trigger collision

        bouncyMap.isOccupied(animal1.getPosition()) shouldBe true
        bouncyMap.isOccupied(animal2.getPosition()) shouldBe true
        animal1.getPosition() shouldNotBe animal2.getPosition()
    }

    should("handle collision when there is no free space by knocking off a random animal") {
        val bouncyMap = BouncyMap(mapWidth, mapHeight)
        repeat(mapWidth * mapHeight) {
            bouncyMap.place(Animal(Vector2d(0, 0)))
        }
        val originalAnimalCount = bouncyMap.getElements().size

        bouncyMap.place(Animal(Vector2d(2, 2))) // Trigger collision

        bouncyMap.getElements().size shouldBe originalAnimalCount
    }
})
