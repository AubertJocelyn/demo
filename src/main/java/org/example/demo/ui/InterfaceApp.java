package org.example.demo.ui;

import javafx.application.*;
import javafx.geometry.Pos;
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
import javafx.scene.text.*;
import org.example.demo.ui.EffetsBoutons;
import org.example.demo.ScriptsExternes.getList;

import java.io.IOException;
import java.time.format.TextStyle;


public class InterfaceApp extends Application {

    String[] albums = getList.albums(); //liste des noms des albums existants
    String current_album = albums[0];



    private Parent first_half_content() {
        //créer la Pane de la moitié de l'interface (où l'utilisateur pourra importer son dossier de photos)

        Button importer = new Quick_Button("Importer", Color.ORANGE, Color.WHITE);

        importer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                //System.out.println(LancementGestionnaireFichiers.main());
                EffetsBoutons.AjouterPhotosAAlbum(current_album);
            }
        });

        Quick_VBox pane = new Quick_VBox(350,500,Color.WHITE); //création de la pane racine de ce noeud
        pane.setLayout(50,50);

        Quick_VBox btn_pane = new Quick_VBox(200,100,null); //création de la pane où sera le bouton importer
        btn_pane.setLayout(150,400);
        btn_pane.getChildren().add(importer);

        btn_pane.setAlignment(Pos.CENTER);
        pane.setAlignment(Pos.CENTER_LEFT);

        HBox.setMargin(pane,new Insets(20,20,20,20));


        pane.getChildren().add(btn_pane);
        pane.setAlignment(Pos.CENTER_LEFT);

        return pane;
    }

    private Parent display_sort_content(String[] categories) {
        //créer la Pane de l'autre moitié de l'interface (où l'utilisateur pourra voir le tri et enregistrer les photos)

        //création de la pane principale

        Quick_HBox pane = new Quick_HBox(350,500,Color.WHITE);
        HBox.setMargin(pane,new Insets(20,20,20,20));
        pane.setAlignment(Pos.CENTER_RIGHT);
        //bouton du choix du tri
        MenuButton menu = new MenuButton("Trier par : ");

        //ajoute les différentes options de tri

        for (int i=0;i<categories.length;i++) {
            final int j = i;
            MenuItem item = new MenuItem(categories[i]);
            menu.getItems().add(item);
            item.setOnAction(e -> {
                try {
                    pane.getChildren().add(show_all(categories[j]));
                } catch (IOException e2) {
                    System.out.println(e2.getMessage());
                }
            });
        }

        menu.setBackground(new Background(new BackgroundFill(Color.ORANGE,new CornerRadii(5), Insets.EMPTY)));
        menu.setTextFill(Color.WHITE);

        //création de la pane pour afficher le bouton
        Quick_Pane choix_tri = new Quick_Pane(200,100,null);
        choix_tri.getChildren().add(menu);
        choix_tri.setLayout(5,5);


        pane.getChildren().add(choix_tri);

        //création bouton enregistrer tout
        Quick_Button enregistrer_tout = new Quick_Button("Enregistrer tout", Color.ORANGE, Color.WHITE);

        enregistrer_tout.setOnAction(e -> {
            System.out.println("Enregistrer tout");
        });

        Quick_Pane pane_button = new Quick_Pane(200,100,null);
        pane_button.setLayout(100,5);
        pane_button.getChildren().add(enregistrer_tout);
        pane.getChildren().add(pane_button);

        return pane;
    }

    private HBox top_bar_content() {
        //créer la Pane avec les boutons d'options et de choix de l'album

        Quick_HBox barre = new Quick_HBox(800,35,Color.DARKGRAY); //création de la barre où seront les boutons

        barre.setSpacing(1);

        //Création des boutons
        //Button album = new Quick_Button("Album",Color.GREY,Color.WHITE);

        Button aide = new Quick_Button("Aide", Color.GREY,Color.WHITE);

        MenuButton album = new MenuButton("Choix de l'album"); //menu déroulant pour choisir l'album de tri

        //il me faudrait une méthode pour obtenir la liste des albums existants, en attendant je mets un album défaut
        MenuItem nouv_alb = new MenuItem("Créer un nouvel album");

        nouv_alb.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                get_album_name();

                }

            });

        //Ajout albums existants
        for (int i=0;i<albums.length;i++) {
            MenuItem a = new MenuItem(albums[i]);
            final int j = i;
            a.setOnAction(e-> {
                album.setText("Album : " + albums[j]);
                current_album = albums[j];
            });
            album.getItems().add(a);
        }
        //Ajout des options au menu déroulant
        album.getItems().addAll(nouv_alb);

        //esthétique du bouton d'album
        album.setBackground(new Background(new BackgroundFill(Color.GRAY,new CornerRadii(5), Insets.EMPTY)));
        album.setTextFill(Color.WHITE);

        //Définition des actions des boutons

		/*album.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent e) {
		    	System.out.println("album");
		    }
		}); */



        aide.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("aide");
            }
        });

        //Création des panes pour les boutons
        Quick_HBox alb = new Quick_HBox(200,100,null);
        Quick_HBox aid = new Quick_HBox(200,100,null);

        alb.setAlignment(Pos.CENTER_LEFT);
        aid.setAlignment(Pos.CENTER_LEFT);
        HBox.setMargin(alb,new Insets(5,5,5,5));
        HBox.setMargin(aid,new Insets(5,5,5,5));
        //Association bouton et pane
        alb.getChildren().add(album);
        aid.getChildren().add(aide);



        //Association panes sur la barre
        barre.getChildren().add(alb);
        barre.getChildren().add(aid);

        return barre;

    }

    private void get_album_name() {
        //permet de créer un nouvel album en ouvrant une nouvelle fenêtre pour remplir le nom du nouvel album
        Stage window = new Stage();

        final String[] name= {null};

        TextField textfield = new TextField("Nouvel album");
        Quick_Button validation = new Quick_Button("Valider",Color.ORANGE,Color.WHITE);
        validation.setOnAction(e-> { //récupère le texte dans le textfield et crée un nouvel album
            name[0] = textfield.getText();
            System.out.println(name[0]); //débug
            EffetsBoutons.CreerAlbum(name[0]);
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

    private ScrollPane show_all(String categorie) throws IOException {
        //Renvoie tous les panes de show catégory dans un menu déroulant
        ScrollPane pane = new ScrollPane();
        pane.setPrefSize(350,500);

        String[] etiquettes = getList.etiquettes(categorie);
        //Quick_Pane long_pane = new Quick_Pane(350,800,null);
        VBox long_pane = new VBox();


        for (int i=0;i<etiquettes.length;i++) {
            Pane pane1 = show_category(etiquettes[i]);
            long_pane.getChildren().add(pane1);
            System.out.println(pane1.getLayoutY());
        }

        pane.setContent(long_pane);
        pane.setLayoutX(0);
        pane.setLayoutY(50);
        pane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        long_pane.setSpacing(50);
        return pane;
    }

    private Pane show_category(String tag) {
        //Renvoie un Pane de toutes les images de la catégorie tag et du bouton pour les enregistrer
        Quick_HBox pane = new Quick_HBox(400,100,Color.DARKGRAY);
        pane.setLayout(5,0);
        Text nom_tag = new Text(tag);
        nom_tag.setFont(Font.font("Verdana", FontWeight.NORMAL, 10));
        nom_tag.setLayoutX(5);
        nom_tag.setLayoutY(5);
        nom_tag.setFill(Color.WHITE);
        pane.getChildren().add(nom_tag);

        Quick_Button enregistrer = new Quick_Button("Enregistrer sous ...", Color.ORANGE,Color.WHITE);
        enregistrer.setOnAction(e -> {System.out.println("Enregistrer");});
        pane.getChildren().add(enregistrer);
        HBox.setMargin(enregistrer,new Insets(5 ,5,5,5));
        HBox.setMargin(nom_tag,new Insets(5,5,5,5));

        pane.setSpacing(10);
        return pane;
    }


    private Pane show_image(String name) {
        //Renvoie un bouton du nom de l'image, qui l'affiche si on clique dessus
        return null;
    }

    private void show_error() {

    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        //Fenêtre principale de l'application

        String[] tri = getList.categories(); //liste de toutes les catégories de tri

        VBox root = new VBox();	//pane racine de l'interface
        root.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));
        HBox top_bar = top_bar_content();
        HBox content = new HBox();
        content.getChildren().add(first_half_content());
        content.getChildren().add(display_sort_content(tri));

        content.setSpacing(50);

        root.getChildren().addAll(top_bar,content);

        primaryStage.setTitle("Logiciel Tri Photos");
        primaryStage.setScene(new Scene(root,800,600));
        primaryStage.show();
        primaryStage.centerOnScreen();

    }


}
