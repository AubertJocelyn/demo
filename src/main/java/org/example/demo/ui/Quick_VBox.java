package org.example.demo.ui;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.awt.*;

public class Quick_VBox extends VBox {
    //création rapide d'une VBox pour coder plus rapidement
    public Quick_VBox(int largeur, int hauteur, Color background_color) {
        super();
        setPrefSize(largeur,hauteur);
        setBackground(new Background(new BackgroundFill(background_color,null,null)));
    }

    public void setLayout(int x,int y) {
        setLayoutX(x);
        setLayoutY(y);
    }


}
