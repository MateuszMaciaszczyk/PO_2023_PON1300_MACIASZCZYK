package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GrassFieldTest {
    @Test
    void isOccupied() throws PositionAlreadyOccupiedException {
        GrassField grassField = new GrassField(5);
        Animal animal = new Animal(new Vector2d(2, 2));
        grassField.place(animal);

        assertTrue(grassField.isOccupied(animal.getPosition()));
    }

    @Test
    void grassSizeTest() {
        GrassField grass = new GrassField(10);
        assertEquals(grass.getGrass().size(), 10);
    }
}
