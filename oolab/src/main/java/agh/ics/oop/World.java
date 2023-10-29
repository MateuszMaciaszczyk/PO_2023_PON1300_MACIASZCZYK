package agh.ics.oop;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.util.List;

public class World {
    public static void run(List<MoveDirection> directions) {
        System.out.println("Start");
        for (MoveDirection direction : directions) {
            switch (direction) {
                case FORWARD -> System.out.println("Zwierzak idzie do przodu");
                case BACKWARD -> System.out.println("Zwierzak idzie do tyłu");
                case RIGHT -> System.out.println("Zwierzak idzie w prawo");
                case LEFT -> System.out.println("Zwierzak idzie w lewo");
            }
        }
        System.out.println("Stop");
    }

    public static void main(String[] args) {
        System.out.println("hej");
        List<MoveDirection> directions = OptionsParser.parse(args);
        List<Vector2d> positions = List.of(new Vector2d(1, 1), new Vector2d(2, 2));
        Simulation simulation = new Simulation(directions, positions);
        simulation.run();
    }
}
