package org.example.demo.ui;

import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;

public class Quick_Pane extends Pane {
    //Classe servant à créer une Pane plus rapidement

    public Quick_Pane(int largeur, int hauteur, Color background_color) {
        super();
        setPrefSize(largeur,hauteur);
        setBackground(new Background(new BackgroundFill(background_color, null, null)));

    }

    public void add_shape(Shape shape, Color color) {
        shape.setFill(color);
        getChildren().add(shape);
    }

    public void setLayout(int x, int y) {
        //défini la position de la pane par rapport à sa racine
        setLayoutX(x);
        setLayoutY(y);
    }

}