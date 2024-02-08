package agh.ics.oop;

import agh.ics.oop.model.GrassField;
import agh.ics.oop.presenter.SimulationPresenter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SimulationApp extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("simulation.fxml"));
        BorderPane viewRoot = loader.load();
        SimulationPresenter presenter = loader.getController();
        GrassField grassField = new GrassField(7);
        presenter.setWorldMap(grassField);
        grassField.addMapChangeListener(presenter);

        grassField.addMapChangeListener((worldMap, message) -> {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            System.out.println(now.format(formatter) + " " + message);
        });

        grassField.addMapChangeListener((worldMap, message) -> {
            String fileName = "map_" + worldMap.getId() + ".log";
            try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))) {
                writer.println(message);
                writer.println(worldMap.toString());
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        configureStage(primaryStage, viewRoot);
        primaryStage.show();
    }

    private void configureStage(Stage primaryStage, BorderPane viewRoot) {
        var scene = new Scene(viewRoot);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Simulation app");
        primaryStage.minWidthProperty().bind(viewRoot.minWidthProperty());
        primaryStage.minHeightProperty().bind(viewRoot.minHeightProperty());
    }
}
