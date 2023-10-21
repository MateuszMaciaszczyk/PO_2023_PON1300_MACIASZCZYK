package agh.ics.oop.model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Vector2dTest {
    Vector2d v1 = new Vector2d(1, 1);
    Vector2d v2 = new Vector2d(2, 2);
    Vector2d v3 = new Vector2d(3, 3);

    @Test
    void equals() {
        assertFalse(v1.equals(v2));
        assertTrue(v1.equals(v1));
    }

    @Test
    void toStringTest() {
        assertEquals("(1,1)", v1.toString());
    }

    @Test
    void precedes() {
        assertTrue(v1.precedes(v3));
        assertFalse(v3.precedes(v1));
    }

    @Test
    void follows() {
        assertFalse(v1.follows(v3));
        assertTrue(v3.follows(v1));
    }

    @Test
    void upperRight() {
        assertEquals(v3, v1.upperRight(v3));
    }

    @Test
    void lowerLeft() {
        assertEquals(v1, v1.lowerLeft(v3));
    }

    @Test
    void add() {
        assertEquals(v3, v1.add(v2));
    }

    @Test
    void subtract() {
        assertEquals(v1, v3.subtract(v2));
    }

    @Test
    void opposite() {
        assertEquals(new Vector2d(-1, -1), v1.opposite());
    }
}
