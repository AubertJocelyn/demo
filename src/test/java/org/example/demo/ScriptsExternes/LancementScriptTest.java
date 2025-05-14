package org.example.demo.ScriptsExternes;

import org.junit.jupiter.api.Test;

import static org.example.demo.ScriptsExternes.AutresMethodes.getCheminImage;
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
        String[] nomsImages = {"blanc.png", "bleu foncer.png"};
        LancementScript.AfficherImages("TestNouvelAlbum", nomsImages);}
}
