package agh.ics.oop.model;

public class Animal {
    private MapDirection orientation;
    private Vector2d position;

    public Animal() {
        new Animal(new Vector2d(2, 2));
    }

    public Animal(Vector2d position) {
        this.position = position;
        this.orientation = MapDirection.NORTH;
    }

    public void move(MoveDirection direction, MoveValidator validator) {
        switch(direction) {
            case RIGHT -> this.orientation = this.orientation.next();
            case LEFT -> this.orientation = this.orientation.previous();
            case FORWARD -> moveHelper(validator, this.orientation.toUnitVector());
            case BACKWARD -> moveHelper(validator, this.orientation.toUnitVector().opposite());
        }
    }

    private void moveHelper(MoveValidator validator, Vector2d unitVector) {
        Vector2d new_position = this.position.add(unitVector);
        if(validator.canMoveTo(new_position)) {
            this.position = new_position;
        }
    }

    @Override
    public String toString(){
        return switch (this.orientation) {
            case NORTH -> "N";
            case EAST -> "E";
            case SOUTH -> "S";
            case WEST -> "W";
        };
    }


    public boolean isAt(Vector2d position) { return this.position.equals(position); }

    public MapDirection getOrientation() { return orientation; }

    public Vector2d getPosition() { return position; }
}
