package org.example.demo;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class InterfaceApp extends Application {

    private Parent createcontent() {


        Button bouton = new Button("Importer");

        bouton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println(LancementGestionnaireFichiers.main());
            }
        });

        bouton.setBackground(new Background(new BackgroundFill(Color.ORANGE,new CornerRadii(5), Insets.EMPTY)));
        bouton.setTextFill(Color.WHITE);

        Pane pane = new Pane();
        pane.setPrefSize(200,100);

        pane.getChildren().add(bouton);

        return pane;

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Logiciel Tri Photos");
        primaryStage.setScene(new Scene(createcontent(),800,600,Color.LIGHTGREY));
        primaryStage.show();
        primaryStage.centerOnScreen();

    }


}