package agh.ics.oop.model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import agh.ics.oop.OptionsParser;
import java.util.ArrayList;
import java.util.List;

public class OptionsParserTest {
    @Test
    void parse() {
        String[] args = {"f", "b", "r", "l", "f", "f"};
        List<MoveDirection> directions = OptionsParser.parse(args);
        List<MoveDirection> expectedDirections = new ArrayList<>() {
            {
                add(MoveDirection.FORWARD);
                add(MoveDirection.BACKWARD);
                add(MoveDirection.RIGHT);
                add(MoveDirection.LEFT);
                add(MoveDirection.FORWARD);
                add(MoveDirection.FORWARD);
            }
        };
        assertEquals(expectedDirections, directions);
    }
}