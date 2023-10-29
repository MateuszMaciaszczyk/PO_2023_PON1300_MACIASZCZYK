package agh.ics.oop.model;

public class Animal {
    private MapDirection orientation;
    private Vector2d position;
    public Animal() {
        this.position = new Vector2d(2, 2);
        this.orientation = MapDirection.NORTH;
    }
    public Animal(Vector2d position) {
        this.position = position;
        this.orientation = MapDirection.NORTH;
    }

    public void move(MoveDirection direction) {
        switch(direction) {
            case RIGHT -> this.orientation = this.orientation.next();
            case LEFT -> this.orientation = this.orientation.previous();
            case FORWARD -> {
                Vector2d new_position = this.position.add(this.orientation.toUnitVector());
                if(new_position.follows(new Vector2d(0, 0)) && new_position.precedes(new Vector2d(4, 4))) {
                        this.position = new_position;
                    }
            }
            case BACKWARD -> {
                Vector2d new_position = this.position.subtract(this.orientation.toUnitVector());
                if(new_position.follows(new Vector2d(0, 0)) && new_position.precedes(new Vector2d(4, 4))) {
                    this.position = new_position;
                }
            }
        }
    }

    @Override
    public String toString() { return (this.position.toString() + ", " + this.orientation); }

    public boolean isAt(Vector2d position) { return this.position.equals(position); }

    public MapDirection getOrientation() {
        return orientation;
    }
}
