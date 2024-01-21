package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.ArrayList;
import java.util.List;

public class World {
    public static void main(String[] args) throws PositionAlreadyOccupiedException {
        System.out.println("System rozpoczął działanie");
        List<MoveDirection> directions = OptionsParser.parse(new String[]{"l", "f", "f", "r", "f", "l", "b", "b", "r", "f", "f", "r", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f"});
        List<Vector2d> positions = List.of(new Vector2d(1, 1), new Vector2d(2, 1));

        ArrayList<Simulation> simulations = new ArrayList<>();

        for(int i=0;i<20;i++){
            AbstractWorldMap map = new GrassField(10);
            AbstractWorldMap map2 = new RectangularMap(10,10);
            map.addMapChangeListener(new ConsoleMapDisplay());
            map2.addMapChangeListener(new ConsoleMapDisplay());
            simulations.add(new Simulation(directions, positions, map));
            simulations.add(new Simulation(directions, positions, map2));
        }

        SimulationEngine engine = new SimulationEngine(simulations);
        engine.runAsync();

        try {
            engine.awaitSimulationsEnd();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("System zakończył działanie");
    }
}
