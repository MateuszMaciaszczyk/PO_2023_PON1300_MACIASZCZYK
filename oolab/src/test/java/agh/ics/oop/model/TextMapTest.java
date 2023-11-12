package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TextMapTest {
    @Test
    void placeTest() {
        WorldMap<String, Integer> map = new TextMap();
        map.place("Ala");
        map.place("ma");
        map.place("kota");
        assertEquals("Ala ma kota ", map.toString());
    }

    @Test
    void moveTest() {
        WorldMap<String, Integer> map = new TextMap();
        map.place("Ala");
        map.place("ma");
        map.place("kota");
        map.move("Ala", MoveDirection.FORWARD);
        assertEquals("ma Ala kota ", map.toString());
        map.move("Ala", MoveDirection.FORWARD);
        assertEquals("ma kota Ala ", map.toString());
        map.move("Ala", MoveDirection.FORWARD);
        map.move("Ala", MoveDirection.FORWARD);
        assertEquals("ma kota Ala ", map.toString());
    }
}
