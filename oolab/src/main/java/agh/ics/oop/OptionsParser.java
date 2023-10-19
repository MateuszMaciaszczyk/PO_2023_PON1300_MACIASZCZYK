package agh.ics.oop;
import agh.ics.oop.model.MoveDirection;

public class OptionsParser {
    public static MoveDirection[] parse(String[] args) {
        int size = 0;
        for (String arg : args) {
            if (arg.equals("f") || arg.equals("b") || arg.equals("r") || arg.equals("l")) {
                size++;
            }
        }

        MoveDirection[] directions = new MoveDirection[size];
        int i = 0;
        for (String arg : args) {
            switch (arg) {
                case "f" -> directions[i++] = MoveDirection.FORWARD;
                case "b" -> directions[i++] = MoveDirection.BACKWARD;
                case "r" -> directions[i++] = MoveDirection.RIGHT;
                case "l" -> directions[i++] = MoveDirection.LEFT;
            }
        }
        return directions;
    }
}
