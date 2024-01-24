import agh.ics.oop.model.*;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AbstractWorldMapTest {
    @Test
    void testGetOrderedAnimals() throws PositionAlreadyOccupiedException{
            AbstractWorldMap map = new AbstractWorldMap() {
                @Override
                public Boundary getCurrentBounds() {
                    return new Boundary(new Vector2d(0, 0), new Vector2d(5, 5));
                }
            };

            Animal animal1 = new Animal(new Vector2d(1, 1));
            Animal animal2 = new Animal(new Vector2d(2, 2));
            Animal animal3 = new Animal(new Vector2d(3, 3));

            map.setCurrentBounds(new Boundary(new Vector2d(0, 0), new Vector2d(5, 5)));
            map.place(animal1);
            map.place(animal2);
            map.place(animal3);

            Collection<Animal> orderedAnimal = map.getOrderedAnimals();
            Iterator<Animal> animalIterator = orderedAnimal.iterator();

            assertEquals(animal1, animalIterator.next());
            assertEquals(animal2, animalIterator.next());
            assertEquals(animal3, animalIterator.next());
    }
}
