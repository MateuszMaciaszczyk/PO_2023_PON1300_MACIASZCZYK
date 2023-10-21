package agh.ics.oop.model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import agh.ics.oop.OptionsParser;

public class OptionsParserTest {
    @Test
    void parse() {
        String[] args = {"f", "b", "r", "l", "f", "f"};
        MoveDirection[] directions = OptionsParser.parse(args);
        MoveDirection[] expectedDirections = {MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.FORWARD, MoveDirection.FORWARD};
        assertArrayEquals(expectedDirections, directions);
    }
}