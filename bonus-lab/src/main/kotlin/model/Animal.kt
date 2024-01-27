package model

class Animal(private var position: Vector2d = Vector2d(2, 2)) {
    private var orientation: MapDirection = MapDirection.NORTH

    fun move(direction: MoveDirection, validator: WorldMap) {
        when (direction) {
            MoveDirection.RIGHT -> orientation = orientation.next()
            MoveDirection.LEFT -> orientation = orientation.previous()
            MoveDirection.FORWARD -> moveHelper(validator, orientation.toUnitVector())
            MoveDirection.BACKWARD -> moveHelper(validator, orientation.toUnitVector().opposite())
        }
    }

    private fun moveHelper(validator: WorldMap, unitVector: Vector2d) {
        val newPosition = position + unitVector
        if (validator.canMoveTo(newPosition)) {
            this.position = newPosition
        }
    }

    override fun toString() = "Z$position"

    fun isAt(otherPosition: Vector2d): Boolean =  this.position == otherPosition

    fun getOrientation(): MapDirection = orientation

    fun getPosition(): Vector2d = position
}