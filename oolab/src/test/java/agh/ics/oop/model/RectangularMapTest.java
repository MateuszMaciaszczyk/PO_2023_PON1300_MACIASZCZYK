package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RectangularMapTest {
    @Test
    public void canMoveToTest(){
        RectangularMap map = new RectangularMap(3,3);
        assertEquals(map.canMoveTo(new Vector2d(1,1)),true);
        assertEquals(map.canMoveTo(new Vector2d(3,3)),true);
        assertEquals(map.canMoveTo(new Vector2d(0,0)),true);
        assertEquals(map.canMoveTo(new Vector2d(4,4)),false);
        assertEquals(map.canMoveTo(new Vector2d(-1,-1)),false);
    }

    @Test
    public void placeTest(){
        RectangularMap map = new RectangularMap(3,3);
        assertEquals(map.place(new Animal(new Vector2d(1,1))),true);
        assertEquals(map.place(new Animal(new Vector2d(1,1))),false);
        assertEquals(map.place(new Animal(new Vector2d(3,3))),true);
        assertEquals(map.place(new Animal(new Vector2d(4,4))),false);
    }

    @Test
    public void isOccupiedTest(){
        RectangularMap map = new RectangularMap(3,3);
        map.place(new Animal(new Vector2d(1,1)));
        assertEquals(map.isOccupied(new Vector2d(1,1)),true);
        assertEquals(map.isOccupied(new Vector2d(1,2)),false);
        assertEquals(map.isOccupied(new Vector2d(3,3)),false);
    }

    @Test
    public void objectAtTest(){
        RectangularMap map = new RectangularMap(3,3);
        Animal animal1 = new Animal(new Vector2d(1,1));
        Animal animal2 = new Animal(new Vector2d(2,2));
        map.place(animal1);
        map.place(animal2);
        assertEquals(map.objectAt(new Vector2d(1,1)),animal1);
        assertEquals(map.objectAt(new Vector2d(2,2)),animal2);
        assertEquals(map.objectAt(new Vector2d(3,3)),null);
    }

    @Test
    public void moveTest() {
        RectangularMap map = new RectangularMap(3, 3);
        Animal animal1 = new Animal(new Vector2d(1, 1));
        Animal animal2 = new Animal(new Vector2d(2, 2));
        map.place(animal1);
        map.place(animal2);
        map.move(animal1, MoveDirection.FORWARD);
        assertEquals(animal1.getPosition(), new Vector2d(1, 2));
        assertEquals(animal1.getOrientation(), MapDirection.NORTH);
        assertEquals(map.objectAt(new Vector2d(1, 2)), animal1);
        assertEquals(map.objectAt(new Vector2d(1, 1)), null);

        map.move(animal1, MoveDirection.FORWARD);
        assertEquals(animal1.getPosition(), new Vector2d(1, 3));
        assertEquals(animal1.getOrientation(), MapDirection.NORTH);
        assertEquals(map.objectAt(new Vector2d(1, 3)), animal1);
        assertEquals(map.objectAt(new Vector2d(1, 2)), null);

        map.move(animal1, MoveDirection.RIGHT);
        assertEquals(animal1.getPosition(), new Vector2d(1, 3));
        assertEquals(animal1.getOrientation(), MapDirection.EAST);
        assertEquals(map.objectAt(new Vector2d(1, 3)), animal1);
        assertEquals(map.objectAt(new Vector2d(1, 2)), null);

        map.move(animal1, MoveDirection.LEFT);
        assertEquals(animal1.getPosition(), new Vector2d(1, 3));
        assertEquals(animal1.getOrientation(), MapDirection.NORTH);
        assertEquals(map.objectAt(new Vector2d(1, 3)), animal1);
        assertEquals(map.objectAt(new Vector2d(1, 2)), null);

        map.move(animal2, MoveDirection.RIGHT);
        map.move(animal2, MoveDirection.BACKWARD);
        assertEquals(animal2.getPosition(), new Vector2d(1, 2));
        assertEquals(animal2.getOrientation(), MapDirection.EAST);
        assertEquals(map.objectAt(new Vector2d(1, 2)), animal2);
        assertEquals(map.objectAt(new Vector2d(2, 2)), null);

        //collision test
        map.move(animal1, MoveDirection.BACKWARD);
        assertEquals(animal1.getPosition(), new Vector2d(1, 3));
        assertEquals(animal1.getOrientation(), MapDirection.NORTH);
        assertEquals(map.objectAt(new Vector2d(1, 3)), animal1);
        assertEquals(map.objectAt(new Vector2d(1, 2)), animal2);

    }


}
