package model

enum class MapDirection {
    NORTH {
        override fun toString(): String = "Północ"
        override fun next(): MapDirection = EAST
        override fun previous(): MapDirection = WEST
    },
    SOUTH {
        override fun toString(): String = "Południe"
        override fun next(): MapDirection = WEST
        override fun previous(): MapDirection = EAST
    },
    WEST {
        override fun toString(): String = "Zachód"
        override fun next(): MapDirection = NORTH
        override fun previous(): MapDirection = SOUTH
    },
    EAST {
        override fun toString(): String = "Wschód"
        override fun next(): MapDirection = SOUTH
        override fun previous(): MapDirection = NORTH
    };

    abstract fun next(): MapDirection
    abstract fun previous(): MapDirection
}