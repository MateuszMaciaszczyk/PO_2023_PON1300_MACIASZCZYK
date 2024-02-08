package agh.ics.oop;
import agh.ics.oop.model.MoveDirection;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OptionsParser {
    public static List<MoveDirection> parse(String[] args) {
        return Stream.of(args).map(OptionsParser::parseOne).collect(Collectors.toList());
    }

    private static MoveDirection parseOne(String s) {
        return switch (s) {
            case "f", "forward" -> MoveDirection.FORWARD;
            case "b", "backward" -> MoveDirection.BACKWARD;
            case "r", "right" -> MoveDirection.RIGHT;
            case "l", "left" -> MoveDirection.LEFT;
            default -> throw new IllegalArgumentException(s + " is not legal move specification");
        };
    }
}
