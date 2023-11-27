package agh.ics.oop.model;
import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SimulationTest {
    @Test
    void simRectangularMap() {
        List<Vector2d> vector = new ArrayList<>(){
            {
                add(new Vector2d(1, 1));
                add(new Vector2d(2, 2));
            }
        };

        List<MoveDirection> moves;
        moves = OptionsParser.parse(new String[]{"l", "f", "f", "r", "f", "l", "b", "b", "r", "f", "f"});
        WorldMap map = new RectangularMap(4, 4);
        Simulation sim = new Simulation(moves, vector, map);
        sim.run();

        assertTrue(sim.getAnimals().get(0).isAt(new Vector2d(1, 2)));
        assertTrue(sim.getAnimals().get(1).isAt(new Vector2d(2, 3)));
    }

    @Test
    void simGrassField() {
        List<Vector2d> vector = new ArrayList<>(){
            {
                add(new Vector2d(1, 1));
                add(new Vector2d(2, 2));
            }
        };

        List<MoveDirection> moves;
        moves = OptionsParser.parse(new String[]{"l", "f", "f", "r", "f", "l", "b", "b", "r", "f", "f"});
        WorldMap map = new GrassField(10);
        Simulation sim = new Simulation(moves, vector, map);
        sim.run();

        assertTrue(sim.getAnimals().get(0).isAt(new Vector2d(0, 2)));
        assertTrue(sim.getAnimals().get(1).isAt(new Vector2d(2, 3)));
    }
}
