package org.example.demo.ui;

import org.example.demo.ScriptsExternes.LancementScript;
import org.junit.jupiter.api.Test;

import static org.example.demo.ui.EffetsBoutons.*;
import static org.junit.jupiter.api.Assertions.*;

class EffetsBoutonsTest {

    @Test
    void creerAlbum() {
        /*
        LancementScript.SupprimerAlbum("TestNouvelAlbum");
        CreerAlbum("TestNouvelAlbum");
         */
    }

    @Test
    void ajouterPhotosAAlbum() {
        //String message = AjouterPhotosAAlbum("LralbumQuiN'existePas");
    }

    @Test
    void exporterImagesInDossier() {
        String[] nomsImages = {"blanc.png", "bleu foncer.png"};
        EffetsBoutons.exporterImagesInDossier("AlbumExemple", nomsImages);
    }
}