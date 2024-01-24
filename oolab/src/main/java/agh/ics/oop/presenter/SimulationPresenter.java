package agh.ics.oop.presenter;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.model.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SimulationPresenter implements MapChangeListener {
    private WorldMap worldMap;

    public void setWorldMap(WorldMap map) {
        this.worldMap = map;
    }
    @FXML
    private Label infoLabel;
    @FXML
    private TextField movesListTextField;
    @FXML
    private Label moveInfoLabel;
    @FXML
    private Button startButton;

    private void clearGrid() {
        mapGrid.getChildren().retainAll(mapGrid.getChildren().get(0));
        mapGrid.getColumnConstraints().clear();
        mapGrid.getRowConstraints().clear();
    }

    private void drawAxes(Boundary boundary) {
        drawXAxis(boundary);
        drawYAxis(boundary);
        Label label = new Label("x/y");
        label.setMinWidth(50);
        label.setMinHeight(50);
        label.setAlignment(Pos.CENTER);
        mapGrid.add(label, 0, 0);


    }

    private void drawXAxis(Boundary boundary) {
        for (int j = boundary.lowerLeft().getX(); j <= boundary.upperRight().getX(); j++) {
            Label label = new Label(Integer.toString(j));
            label.setMinWidth(50);
            label.setMinHeight(50);
            label.setAlignment(Pos.CENTER);
            mapGrid.add(label, j + 1 - boundary.lowerLeft().getX(), 0); // Dodajemy etykiety osi X na górze siatki
        }
    }

    private void drawYAxis(Boundary boundary) {
        for (int i = boundary.lowerLeft().getY(); i <= boundary.upperRight().getY(); i++) {
            Label label = new Label(Integer.toString(i));
            label.setMinWidth(50);
            label.setMinHeight(50);
            label.setAlignment(Pos.CENTER);
            mapGrid.add(label, 0, boundary.upperRight().getY() - i + 1); // Dodajemy etykiety osi Y po lewej stronie siatki
        }
    }

    @FXML
    public void drawMap() {
        clearGrid();
        Boundary boundary = worldMap.getCurrentBounds();
        drawAxes(boundary);
        drawGrid(boundary);
        infoLabel.setText("");
    }

    private void drawGrid(Boundary boundary) {
        for (int i = boundary.lowerLeft().getY(); i <= boundary.upperRight().getY(); i++) {
            for (int j = boundary.lowerLeft().getX(); j <= boundary.upperRight().getX(); j++) {
                Vector2d position = new Vector2d(j, i);
                drawGridCell(position, j - boundary.lowerLeft().getX() + 1, boundary.upperRight().getY() - i + 1);
            }
        }
    }

    private void drawGridCell(Vector2d position, int column, int row) {
        Optional<WorldElement> element = worldMap.objectAt(position);

//        Label label = createLabelForElement(element);
        if (element.isPresent()) {
            WorldElementBox box = new WorldElementBox(element.get());
            mapGrid.add(box, column, row);
        }
    }

    private Label createLabelForElement(Optional<WorldElement> element) {
        Label label;
        label = element.map(worldElement -> new Label(worldElement.toString())).orElseGet(() -> new Label(" "));
        label.setMinWidth(50);
        label.setMinHeight(50);
        label.setAlignment(Pos.CENTER);
        return label;
    }

    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        Platform.runLater(() -> {
            this.drawMap();
            moveInfoLabel.setText(message);
        });
    }
    @FXML
    public void onSimulationStartClicked() {
        String movesString = movesListTextField.getText();
        try {
            List<MoveDirection> movesList = OptionsParser.parse(movesString.split(""));
            Simulation simulation = new Simulation(movesList, List.of(new Vector2d(2,2) , new Vector2d(5,5)), worldMap);
            SimulationEngine simulationEngine = new SimulationEngine(new ArrayList<>(List.of(simulation)));
            simulationEngine.runAsync();
            Platform.runLater(() -> startButton.setDisable(true));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @FXML
    private GridPane mapGrid;
}
