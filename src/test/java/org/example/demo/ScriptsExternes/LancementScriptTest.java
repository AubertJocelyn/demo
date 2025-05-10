package org.example.demo.ScriptsExternes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class LancementScriptTest {

    @Test
    void main() {
    }

    @Test
    void lancementGestionnaireFichiers() {
    }

    @Test
    void copieDossierSourc_Dest() {
    }

    @Test
    void trierAlbum() {
        LancementScript.TrierAlbum("TestNouvelAlbum");
    }

    /*@Test
    void CreerDossierAlbum() {
        LancementScript.CreerDossierAlbum("AlbumCree");
    }*/

    @Test
    void TestCreerFichierVide() {LancementScript.TestCreerFichierVide();}

    @Test
    void AfficherImages() {
        String cheminImage1 = LancementScript.getCheminImage("AlbumExemple", "blanc.png");
        String cheminImage2 = LancementScript.getCheminImage("AlbumExemple", "bleu foncer.png");
        String[] cheminsImages = {cheminImage1, cheminImage2};
        LancementScript.AfficherImages(cheminsImages);}
}
