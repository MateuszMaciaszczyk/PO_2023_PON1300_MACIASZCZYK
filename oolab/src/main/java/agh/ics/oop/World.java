package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.List;

public class World {
    public static void main(String[] args) {
        List<MoveDirection> directions2 = OptionsParser.parse(new String[]{"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"});
        List<Vector2d> positions2 = List.of(new Vector2d(2,3), new Vector2d(3,1));
        WorldMap<Animal, Vector2d> map = new RectangularMap(4, 4);
        Simulation simulation2 = new Simulation(directions2, positions2, map);
        simulation2.run();
    }
}
