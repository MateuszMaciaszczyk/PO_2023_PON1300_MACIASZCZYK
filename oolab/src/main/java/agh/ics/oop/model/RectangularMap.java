package agh.ics.oop.model;

public class RectangularMap extends AbstractWorldMap {
    public RectangularMap(int height, int width) {
        super();
        super.lowerLeft = new Vector2d(0, 0);
        super.upperRight = new Vector2d(width, height);
    }

    @Override
    public Boundary getCurrentBounds() {
        return new Boundary(lowerLeft, upperRight);
    }
}
