package org.example.demo.ui;

import org.example.demo.ScriptsExternes.LancementScript;

import java.nio.file.Paths;

public class EffetsBoutons {
    public static void CreerAlbum(String nomAlbum) {
        //sélectionne le dossier d'images à importer (par défaut, choisir AlbumTest/images
        LancementScript.CreerDossierAlbum(nomAlbum);
        AjouterPhotosAAlbum(nomAlbum);
    }

    public static void AjouterPhotosAAlbum(String nomAlbum) {
        String dossierSource = LancementScript.LancementGestionnaireFichiers();
        System.out.println(dossierSource);
        String dossierDestination = Paths.get(System.getProperty("user.dir"), "WorkingDirectory", nomAlbum, "images").toAbsolutePath().toString();
        System.out.println("dossier destination");
        System.out.println(dossierDestination);
        LancementScript.CopieDossierSourc_Dest(dossierSource, dossierDestination);
    }

}
