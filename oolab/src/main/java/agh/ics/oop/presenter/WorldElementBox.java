package agh.ics.oop.presenter;

import agh.ics.oop.model.WorldElement;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;


public class WorldElementBox extends VBox {
    private ImageView imageView;
    private Label label;

    public WorldElementBox(WorldElement element) {
        Image image = new Image(element.getImageName());
        this.imageView = new ImageView(image);
        this.imageView.setFitWidth(20);
        this.imageView.setFitHeight(20);

        if (this.label == null) {
            this.label = new Label(element.toString());
        }

        this.getChildren().addAll(this.imageView, this.label);
        this.setAlignment(Pos.CENTER);
    }
}
