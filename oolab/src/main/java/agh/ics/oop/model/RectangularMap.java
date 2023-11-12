package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.HashMap;
import java.util.Map;

public class RectangularMap implements WorldMap<Animal, Vector2d> {
    private Map<Vector2d, Animal> animals = new HashMap<>();
    private final int height;
    private final int width;
    private final Vector2d lowerLeft;
    private final Vector2d upperRight;
    MapVisualizer mapVisualizer;

    public RectangularMap(int height, int width) {
        this.height = height;
        this.width = width;
        this.lowerLeft = new Vector2d(0, 0);
        this.upperRight = new Vector2d(width, height);
        this.mapVisualizer = new MapVisualizer(this);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(lowerLeft) && position.precedes(upperRight) && !isOccupied(position);
    }

    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())) {
            animals.put(animal.getPosition(), animal);
            return true;
        }
        return false;
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        if (animal == objectAt(animal.getPosition())) {
            animals.remove(animal.getPosition());
            animal.move(direction, this);
            animals.put(animal.getPosition(), animal);
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return animals.containsKey(position);
    }

    @Override
    public Animal objectAt(Vector2d position) {
        if(isOccupied(position)) {
            return animals.get(position);
        }
        return null;
    }

    public String toString() {
        return mapVisualizer.draw(lowerLeft, upperRight);
    }
}
