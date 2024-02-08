package agh.ics.oop.model;

import java.util.HashMap;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GrassField extends AbstractWorldMap {
    private final HashMap<Vector2d, Grass> grass = new HashMap<>();

    public GrassField(int grassNumber) {
        generateGrass(grassNumber);
        super.lowerLeft = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);
        super.upperRight = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    private void generateGrass(int grassNumber) {
        int grassUpperRange = (int) (Math.sqrt(grassNumber * 10));
        RandomPositionGenerator randomPositionGenerator = new RandomPositionGenerator(grassUpperRange, grassUpperRange, grassNumber);
        for (Vector2d grassPosition : randomPositionGenerator) {
            grass.put(grassPosition, new Grass(grassPosition));
        }
    }

    @Override
    public Optional<WorldElement> objectAt(Vector2d position) {
        if (super.isOccupied(position)) {
            return super.objectAt(position);
        } else {
            return Optional.ofNullable(grass.get(position));
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return super.isOccupied(position) || grass.containsKey(position);
    }

    private Vector2d getLowerLeft() {
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;

        for (Vector2d position : grass.keySet()) {
            minX = Math.min(minX, position.getX());
            minY = Math.min(minY, position.getY());
        }

        for (Vector2d position : animals.keySet()) {
            minX = Math.min(minX, position.getX());
            minY = Math.min(minY, position.getY());
        }

        return new Vector2d(minX, minY);
    }

    private Vector2d getUpperRight() {
        int maxX = 0;
        int maxY = 0;

        for (Vector2d position : grass.keySet()) {
            maxX = Math.max(maxX, position.getX());
            maxY = Math.max(maxY, position.getY());
        }

        for (Vector2d position : animals.keySet()) {
            maxX = Math.max(maxX, position.getX());
            maxY = Math.max(maxY, position.getY());
        }

        return new Vector2d(maxX, maxY);
    }

    public HashMap<Vector2d, Grass> getGrass() {
        return grass;
    }

    @Override
    public Set<WorldElement> getElements() {
        return Stream.concat(super.getElements().stream(), grass.values().stream()).collect(Collectors.toSet());
    }

    @Override
    public Boundary getCurrentBounds() {
        return new Boundary(getLowerLeft(), getUpperRight());
    }
}
