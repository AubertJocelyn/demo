package org.example.demo.ui;

import javafx.application.*;
import javafx.scene.control.MenuItem;
import javafx.scene.input.ContextMenuEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.stage.*;
import javafx.scene.control.*;




public class InterfaceApp extends Application {

    String[] tri = {"Couleurs", "Date"}; //liste des différentes catégories de tri (à changer pour l'obtenir depuis un fichier txt ou autre)


    private Parent first_half_content() {
        //créer la Pane de la moitié de l'interface (où l'utilisateur pourra importer son dossier de photos)

        Button importer = new Quick_Button("Importer", Color.ORANGE, Color.WHITE);

        importer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                //System.out.println(LancementGestionnaireFichiers.main());
                System.out.println("importer");
            }
        });

        Quick_Pane pane = new Quick_Pane(400,500,Color.WHITE); //création de la pane racine de ce noeud
        pane.setLayout(50,50);

        Quick_Pane btn_pane = new Quick_Pane(200,100,null); //création de la pane où sera le bouton importer
        btn_pane.setLayout(150,400);
        btn_pane.getChildren().add(importer);



        pane.getChildren().add(btn_pane);

        return pane;
    }

    private Parent display_sort_content() {
        //créer la Pane de l'autre moitié de l'interface (où l'utilisateur pourra voir le tri et enregistrer les photos)



        return null;
    }

    private Parent top_bar_content() {
        //créer la Pane avec les boutons d'options et de choix de l'album

        Quick_Pane barre = new Quick_Pane(800,35,Color.DARKGRAY); //création de la barre où seront les boutons


        //Création des boutons
        //Button album = new Quick_Button("Album",Color.GREY,Color.WHITE);
        Button option = new Quick_Button("Options", Color.GREY, Color.WHITE);
        Button aide = new Quick_Button("Aide", Color.GREY,Color.WHITE);

        MenuButton album = new MenuButton("Choix de l'album"); //menu déroulant pour choisir l'album de tri

        //il me faudrait une méthode pour obtenir la liste des albums existants, en attendant je mets un album défaut
        MenuItem nouv_alb = new MenuItem("Créer un nouvel album");

        nouv_alb.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("Création nouvel album");
                get_album_name();

                }

            });
        //Ajout des options au menu déroulant
        album.getItems().addAll(nouv_alb);

        //esthétique du bouton d'album
        album.setBackground(new Background(new BackgroundFill(Color.DARKGRAY,new CornerRadii(5), Insets.EMPTY)));
        album.setTextFill(Color.WHITE);

        //Définition des actions des boutons

		/*album.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent e) {
		    	System.out.println("album");
		    }
		}); */



        option.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("option");
            }
        });

        aide.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("aide");
            }
        });

        //Création des panes pour les boutons
        Quick_Pane alb = new Quick_Pane(200,100,null);
        Quick_Pane opt = new Quick_Pane(200,100,null);
        Quick_Pane aid = new Quick_Pane(200,100,null);

        //Association bouton et pane
        alb.getChildren().add(album);
        opt.getChildren().add(option);
        aid.getChildren().add(aide);

        //Position des panes
        alb.setLayout(20,5);
        opt.setLayout(150,5);
        aid.setLayout(220,5);

        //Association panes sur la barre
        barre.getChildren().add(alb);
        barre.getChildren().add(opt);
        barre.getChildren().add(aid);

        return barre;

    }

    private void get_album_name() {
        //permet des créer un nouvel album en ouvrant une nouvelle fenêtre pour remplir le nom du nouvel album
        Stage window = new Stage();

        final String[] name= {null};

        TextField textfield = new TextField("Nouvel album");
        Quick_Button validation = new Quick_Button("Valider",Color.ORANGE,Color.WHITE);
        validation.setOnAction(e-> {
            name[0] = textfield.getText();
            System.out.println(name[0]);
            //appeler la fonction de Jocelyn avec name[0] en paramètre
            window.close();

        });
        Pane root = new Pane();
        Quick_Pane button = new Quick_Pane(200,100,null);
        button.getChildren().add(validation);
        button.setLayout(150, 250);
        Quick_Pane textpane = new Quick_Pane(250,20,null);
        textpane.getChildren().add(textfield);
        textpane.setLayout(150, 150);
        root.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));
        root.getChildren().addAll(button,textpane);


        window.setScene(new Scene(root,400,300));
        window.show();



    }

    private Pane show_category(String tag) {
        //Renvoie un Pane de toutes les images de la catégorie tag et du bouton pour les enregistrer
        return null;
    }


    private Pane show_image(String name) {
        //Renvoie un pane de l'image nommée name miniature avec son nom en dessous
        return null;
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        //Fenêtre principale de l'application

        Pane root = new Pane(); 	//pane racine de l'interface
        root.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));
        root.getChildren().add(first_half_content());
        root.getChildren().add(top_bar_content());
        primaryStage.setTitle("Logiciel Tri Photos");
        primaryStage.setScene(new Scene(root,800,600));
        primaryStage.show();
        primaryStage.centerOnScreen();

    }


}
