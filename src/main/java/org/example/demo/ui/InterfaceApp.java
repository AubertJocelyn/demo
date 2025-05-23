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
import org.example.demo.ScriptsExternes.getTableauDes;

import java.io.IOException;
import java.time.format.TextStyle;
import java.util.Arrays;


public class InterfaceApp extends Application {

    String[] albums = getTableauDes.albums(); //liste des noms des albums existants
    String current_album = null;
    Boolean isActionEnCours = Boolean.FALSE;



    /*private Parent first_half_content() {
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
    }*/ //méthode remplacée par le bouton importer de la barre en haut

    private Parent display_sort_content(String[] categories) {
        //créer la Pane de l'autre moitié de l'interface (où l'utilisateur pourra voir le tri et enregistrer les photos)

        //création de la pane principale


        BorderPane pane = new BorderPane();
        pane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        HBox.setMargin(pane,new Insets(20,20,20,20));
        //bouton du choix du tri
        MenuButton menu = new MenuButton("Trier par : ");

        //création bouton enregistrer tout
        Quick_Button enregistrer_tout = new Quick_Button("Enregistrer tout", Color.LIGHTGRAY, Color.WHITE);



        //ajoute les différentes options de tri

        for (int i=0;i<categories.length;i++) {
            final int j = i;
            MenuItem item = new MenuItem(categories[i]);
            menu.getItems().add(item);
            item.setOnAction(e -> {
                if (current_album != null) {
                    enregistrer_tout.setBackground(new Background(new BackgroundFill(Color.ORANGE, CornerRadii.EMPTY, Insets.EMPTY)));
                }
                try {
                    pane.setCenter(null);
                    pane.setCenter(show_all(categories[j]));
                } catch (IOException e2) {
                    System.out.println(e2.getMessage());

                }

            });
        }

        menu.setBackground(new Background(new BackgroundFill(Color.ORANGE,new CornerRadii(5), Insets.EMPTY)));
        menu.setTextFill(Color.WHITE);







        enregistrer_tout.setOnAction(e -> {
            if (current_album != null) {
                System.out.println("Enregistrer tout");
            }

        });


        HBox btns = new HBox();
        btns.setSpacing(10);
        btns.getChildren().addAll(menu,enregistrer_tout);
        pane.setTop(btns);

        BorderPane.setMargin(btns,new Insets(5,5,5,5));

        return pane;
    }

    private HBox top_bar_content() {
        //créer la Pane avec les boutons d'options et de choix de l'album

        Quick_HBox barre = new Quick_HBox(800,35,Color.DARKGRAY); //création de la barre où seront les boutons


        barre.setSpacing(1);

        //Création des boutons
        //Button album = new Quick_Button("Album",Color.GREY,Color.WHITE);

        Button importer = new Quick_Button("Importer", Color.LIGHTGRAY, Color.GRAY);

        importer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (current_album != null) {
                    //System.out.println(LancementGestionnaireFichiers.main());
                    EffetsBoutons.AjouterPhotosAAlbum(current_album);
                }
            }
        });



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
                aide();
            }
        });



        album.setAlignment(Pos.CENTER_LEFT);
        aide.setAlignment(Pos.CENTER_LEFT);
        importer.setAlignment(Pos.CENTER_LEFT);
        HBox.setMargin(importer,new Insets(5,5,5,5));
        HBox.setMargin(album,new Insets(5,5,5,5));
        HBox.setMargin(aide,new Insets(5,5,5,5));



        //Association panes sur la barre
        barre.getChildren().add(importer);
        barre.getChildren().add(album);
        barre.getChildren().add(aide);
        barre.setSpacing(5);

        //Ajout albums existants
        for (int i=0;i<albums.length;i++) {
            MenuItem a = new MenuItem(albums[i]);
            final int j = i;
            a.setOnAction(e-> {
                album.setText("Album : " + albums[j]);
                current_album = albums[j];
                importer.setBackground(new Background(new BackgroundFill(Color.ORANGE, CornerRadii.EMPTY, Insets.EMPTY)));
                importer.setTextFill(Color.WHITE);
            });
            album.getItems().add(a);
        }
        //Ajout des options au menu déroulant
        album.getItems().addAll(nouv_alb);

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
        pane.setLayoutX(10);
        String[] etiquettes = getTableauDes.etiquettes(categorie);
        //Quick_Pane long_pane = new Quick_Pane(350,800,null);
        VBox long_pane = new VBox();


        for (int i=0;i<etiquettes.length;i++) {
            Pane pane1 = show_category(etiquettes[i],categorie);
            long_pane.getChildren().add(pane1);
        }

        pane.setContent(long_pane);
        pane.setLayoutX(0);
        pane.setLayoutY(50);
        pane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        long_pane.setSpacing(50);
        return pane;
    }

    private Pane show_category(String tag,String categorie) {
        //Renvoie un Pane de toutes les images de la catégorie tag et du bouton pour les enregistrer
        BorderPane pane = new BorderPane();
        pane.setLayoutX(5);
        pane.setPrefSize(1200,200);
        pane.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));

        HBox top = new HBox();

        Text nom_tag = new Text(tag);
        nom_tag.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        nom_tag.setFill(Color.GRAY);


        Quick_Button enregistrer = new Quick_Button("Enregistrer sous ...", Color.ORANGE,Color.WHITE);
        enregistrer.setOnAction(e -> {System.out.println("Enregistrer");});


        HBox.setMargin(enregistrer,new Insets(5 ,5,5,5));
        HBox.setMargin(nom_tag,new Insets(5,5,5,5));

        nom_tag.setTextAlignment(TextAlignment.CENTER);
        top.getChildren().addAll(nom_tag,enregistrer);
        pane.setTop(top);

        if (current_album != null) {
            String[] images = getTableauDes.images(current_album,categorie,tag);
            Quick_Button visu = new Quick_Button("Voir images", Color.ORANGE, Color.WHITE);
            visu.setOnAction(e -> {EffetsBoutons.VisualiserImages(current_album,images);});
            HBox.setMargin(visu,new Insets(5,5,5,5));
            top.getChildren().add(visu);
            for (int i=0;i<images.length;i++) {
                pane.setCenter(show_image(images[i]));
            }

        }

        return pane;
    }


    private Pane show_image(String image) {
        HBox ima = new HBox();
        Text im = new Text(image);
        im.setFont(Font.font("Verdana", FontWeight.NORMAL, 10));
        im.setFill(Color.WHITE);
        return ima;
    }

    private void show_error() {
        Stage window = new Stage();
        Quick_VBox root = new Quick_VBox(500,100,Color.LIGHTGRAY);
        Text text1 = new Text("Une erreur s'est produite");
        Quick_Button ok = new Quick_Button("OK",Color.ORANGE,Color.WHITE);
        ok.setOnAction(e -> {window.close();});

        text1.setFont(Font.font("Verdana", FontWeight.BOLD, 20));

        text1.setTextAlignment(TextAlignment.CENTER);
        ok.setAlignment(Pos.BOTTOM_CENTER);

        VBox.setMargin(ok,new Insets(20,20,10,20));

        root.getChildren().addAll(text1,ok);
        window.setTitle("Erreur");
        window.setScene(new Scene(root,600,100));
        window.show();
    }

    private void aide(){
        Stage window = new Stage();
        Quick_VBox root = new Quick_VBox(500,100,Color.LIGHTGRAY);
        Text text1 = new Text("Pour commencer, choisissez ou créez un album.");
        Text text2 = new Text("Dans cet album, importez les photos à trier (sélectionnez un dossier de photos) puis" +
                " choisissez le critère de tri.");
        Quick_Button ok = new Quick_Button("OK",Color.ORANGE,Color.WHITE);
        ok.setOnAction(e -> {window.close();});

        text1.setFont(Font.font("Verdana", FontWeight.NORMAL, 10));
        text2.setFont(Font.font("Verdana", FontWeight.NORMAL, 10));
        text1.setTextAlignment(TextAlignment.CENTER);
        text2.setTextAlignment(TextAlignment.CENTER);

        ok.setAlignment(Pos.BOTTOM_CENTER);

        VBox.setMargin(ok,new Insets(20,20,10,20));
        VBox.setMargin(text2,new Insets(5,5,5,5));
        VBox.setMargin(text1,new Insets(5,5,5,5));
        root.getChildren().addAll(text1,text2,ok);
        window.setTitle("Aide");
        window.setScene(new Scene(root,600,100));
        window.show();
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        //Fenêtre principale de l'application

        String[] tri = getTableauDes.categories(); //liste de toutes les catégories de tri

        BorderPane root = new BorderPane();
        root.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));
        HBox top_bar = top_bar_content();
        root.setTop(top_bar);
        Parent content = display_sort_content(tri);

        BorderPane.setMargin(content,new Insets(20,20,20,20));
        root.setCenter(content);

        primaryStage.setTitle("Logiciel Tri Photos");
        primaryStage.setScene(new Scene(root,800,600));
        primaryStage.show();
        primaryStage.centerOnScreen();

    }
}
