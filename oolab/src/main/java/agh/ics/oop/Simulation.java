package agh.ics.oop;
import agh.ics.oop.model.*;
import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private final List<Animal> animals = new ArrayList<>();
    private final List<MoveDirection> moves;
    private final WorldMap map;

    public Simulation(List<MoveDirection> moves_list, List<Vector2d> vector_list, WorldMap map) {
        this.map = map;
        this.initializeAnimals(vector_list);
        this.moves = moves_list;
    }

    private void initializeAnimals(List<Vector2d> positionList) {
        for (Vector2d vector : positionList) {
            if (map.canMoveTo(vector)) {
                Animal animal = new Animal(vector);
                map.place(animal);
                animals.add(animal);
            }
        }
    }

    public void run() {
        int i = 0;
        for (MoveDirection move : moves) {
            map.move(animals.get(i++ % animals.size()), move);
            System.out.println(map.toString());
        }
    }

    public List<Animal> getAnimals() { return animals; }
}
