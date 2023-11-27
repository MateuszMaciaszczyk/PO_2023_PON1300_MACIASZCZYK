package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.*;

public abstract class AbstractWorldMap implements WorldMap {
    protected Map<Vector2d, Animal> animals = new HashMap<>();
    protected List<MapChangeListener> mapChangeListeners = new ArrayList<>();
    protected final MapVisualizer mapVisualizer;
    protected Vector2d lowerLeft;
    protected Vector2d upperRight;

    public AbstractWorldMap() {
        this.mapVisualizer = new MapVisualizer(this);
    }

    @Override
    public void place(Animal animal) throws PositionAlreadyOccupiedException{
        if (!canMoveTo(animal.getPosition())) {
            throw new PositionAlreadyOccupiedException(animal.getPosition());
        }
        animals.put(animal.getPosition(), animal);
        mapChanged("Animal placed at: " + animal.getPosition());
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        animals.remove(animal.getPosition());
        animal.move(direction, this);
        animals.put(animal.getPosition(), animal);
        mapChanged("Animal moved to: " + animal.getPosition());
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        boolean canMove = position.follows(lowerLeft) && position.precedes(upperRight) && !isOccupied(position);
        if (!canMove) {
            mapChanged("Animal cannot move to: " + position);
        }
        return canMove;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return animals.containsKey(position);
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        return animals.get(position);
    }

    public String toString() {
        Boundary boundary = getCurrentBounds();
        return mapVisualizer.draw(boundary.lowerLeft(), boundary.upperRight());
    }

    public Set<WorldElement> getElements() { return new HashSet<>(animals.values()); }

    public abstract Boundary getCurrentBounds();

    public void addMapChangeListener(MapChangeListener listener) {
        mapChangeListeners.add(listener);
    }

    public void removeMapChangeListener(MapChangeListener listener) {
        mapChangeListeners.remove(listener);
    }

    private void mapChanged(String message) {
        for (MapChangeListener listener : mapChangeListeners) {
            listener.mapChanged(this, message);
        }
    }
}
