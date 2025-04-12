package org.example.demo.ui;

import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.stage.*;
import javafx.scene.control.*;

public class Quick_Button extends Button {
    //Classe servant à créer un bouton plus rapidement

    public Quick_Button(String name, Color couleur_fond, Color couleur_texte) {
        super(name);
        setBackground(new Background(new BackgroundFill(couleur_fond,new CornerRadii(5), Insets.EMPTY)));
        setTextFill(couleur_texte);

    }

}
