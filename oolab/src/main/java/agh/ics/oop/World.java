package agh.ics.oop;
import agh.ics.oop.model.MoveDirection;

public class World {
    public static void run(MoveDirection[] directions) {
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
        MoveDirection[] directions = OptionsParser.parse(args);
        run(directions);
    }
}
