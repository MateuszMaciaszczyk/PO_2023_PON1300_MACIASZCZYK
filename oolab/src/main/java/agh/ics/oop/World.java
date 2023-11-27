package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.List;

public class World {
    public static void main(String[] args) throws PositionAlreadyOccupiedException {
        List<MoveDirection> directions2 = OptionsParser.parse(new String[]{"l", "f", "f", "r", "f", "l", "b", "b", "r", "f", "f"});
        List<Vector2d> positions2 = List.of(new Vector2d(1, 1), new Vector2d(1, 1));
        WorldMap map = new GrassField(10);
        ConsoleMapDisplay consoleMapDisplay = new ConsoleMapDisplay();
        map.addMapChangeListener(consoleMapDisplay);
        Simulation simulation2 = new Simulation(directions2, positions2, map);
        simulation2.run();
    }
}
