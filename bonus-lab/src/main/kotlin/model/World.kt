package model

fun main() {
    val bouncyMap = BouncyMap(5, 5)
    val animal1 = Animal(Vector2d(2, 2))
    val animal2 = Animal(Vector2d(2, 3))
    bouncyMap.place(animal1)
    bouncyMap.place(animal2)

    bouncyMap.place(animal1) // Trigger collision
    println(bouncyMap.getElements())
}