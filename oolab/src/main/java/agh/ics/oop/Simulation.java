package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private List<Animal> animals = new ArrayList<>();
    private List<MoveDirection> moves;
    public Simulation(List<MoveDirection> moves_list, List<Vector2d>vector_list) {
        this.set_animals(vector_list);
        this.moves = moves_list;
    }

    private void set_animals(List<Vector2d> vector_list) {
        for (Vector2d vector : vector_list) {
            animals.add(new Animal(vector));
        }
    }

    public void run() {
        int i = 0;
        for (MoveDirection move : moves) {
            animals.get(i % animals.size()).move(move);
            System.out.println("Zwierze " + i % animals.size() + " : " + animals.get(i % animals.size()).toString());
            i += 1;
        }
    }

    public List<Animal> getAnimals() {
        return animals;
    }
}
