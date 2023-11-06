package agh.ics.oop.model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AnimalTest {
    @Test
    void orientation() {
        Animal animal = new Animal();
        MoveValidator validator = new RectangularMap(4, 4);
        animal.move(MoveDirection.LEFT, validator);
        animal.move(MoveDirection.LEFT, validator);
        animal.move(MoveDirection.RIGHT, validator);
        assertEquals(MapDirection.WEST, animal.getOrientation());
        animal.move(MoveDirection.LEFT, validator);
        animal.move(MoveDirection.LEFT, validator);
        assertEquals(MapDirection.EAST, animal.getOrientation());
    }

    @Test
    void position() {
        Animal animal = new Animal();
        MoveValidator validator = new RectangularMap(4, 4);
        animal.move(MoveDirection.FORWARD, validator);
        animal.move(MoveDirection.FORWARD, validator);
        animal.move(MoveDirection.LEFT, validator);
        animal.move(MoveDirection.FORWARD, validator);
        animal.move(MoveDirection.RIGHT, validator);
        animal.move(MoveDirection.BACKWARD, validator);
        assertTrue(animal.isAt(new Vector2d(1, 3)));
    }

    @Test
    void in_area() {
        Animal animal = new Animal();
        MoveValidator validator = new RectangularMap(4, 4);
        animal.move(MoveDirection.FORWARD, validator);
        animal.move(MoveDirection.FORWARD, validator);
        animal.move(MoveDirection.FORWARD, validator);
        animal.move(MoveDirection.FORWARD, validator);
        animal.move(MoveDirection.FORWARD, validator);
        assertTrue(animal.isAt(new Vector2d(2, 4)));
    }
}
