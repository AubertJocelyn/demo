package org.example.demo.ui;


import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import java.awt.*;

public class Quick_HBox extends HBox {
    //création rapide d'une HBox

    public Quick_HBox(int largeur, int hauteur, Color background_color) {
        super();
        setPrefSize(largeur,hauteur);
        setBackground(new Background(new BackgroundFill(background_color,null,null)));
    }

    public void setLayout(int x, int y) {
        setLayoutX(x);
        setLayoutY(y);
    }
}
