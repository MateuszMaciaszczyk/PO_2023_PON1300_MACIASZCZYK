package model

class Vector2d(val x: Int, val y: Int) {
    fun upperRight(other: Vector2d): Vector2d = Vector2d(Math.max(this.x, other.x), Math.max(this.y, other.y))
    fun lowerLeft(other: Vector2d): Vector2d = Vector2d(Math.min(this.x, other.x), Math.min(this.y, other.y))
    fun opposite(): Vector2d = Vector2d(-this.x, -this.y)
    operator fun plus(other: Vector2d): Vector2d = Vector2d(this.x + other.x, this.y + other.y)
    operator fun minus(other: Vector2d): Vector2d = Vector2d(this.x - other.x, this.y - other.y)
    operator fun compareTo(other: Vector2d): Int {
        return when {
            this.x >= other.x && this.y >= other.y -> 1
            this.x <= other.x && this.y <= other.y -> -1
            else -> 0
        }
    }

    override fun hashCode(): Int = this.x * 31 + this.y

    override fun toString(): String = "($x,$y)"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Vector2d) return false
        return this.x == other.x && this.y == other.y
    }
}

fun MapDirection.toUnitVector(): Vector2d {
    return when (this) {
        MapDirection.NORTH -> Vector2d(0, 1)
        MapDirection.SOUTH -> Vector2d(0, -1)
        MapDirection.WEST -> Vector2d(-1, 0)
        MapDirection.EAST -> Vector2d(1, 0)
    }
}