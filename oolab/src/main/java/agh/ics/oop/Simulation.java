package agh.ics.oop;
import agh.ics.oop.model.*;
import java.util.ArrayList;
import java.util.List;

public class Simulation extends Thread{
    private final List<Animal> animals = new ArrayList<>();
    private final List<MoveDirection> moves;
    private final WorldMap map;

    public Simulation(List<MoveDirection> moves_list, List<Vector2d> vector_list, WorldMap map) throws PositionAlreadyOccupiedException {
        this.map = map;
        this.initializeAnimals(vector_list);
        this.moves = moves_list;
    }

    private void initializeAnimals(List<Vector2d> positionList) throws PositionAlreadyOccupiedException {
        for (Vector2d vector : positionList) {
            Animal animal = new Animal(vector);
            try {
                map.place(animal);
                animals.add(animal);
            }
            catch (PositionAlreadyOccupiedException ignored) {
                System.out.println(ignored.getMessage());
            }
        }
    }

    public void run() {
        int i = 0;
        for (MoveDirection move : moves) {
            try {
                Thread.sleep(1000);
                map.move(animals.get(i++ % animals.size()), move);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Animal> getAnimals() { return animals; }
}
