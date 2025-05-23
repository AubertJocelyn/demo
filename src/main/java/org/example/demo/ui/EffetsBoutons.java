package org.example.demo.ui;

import org.example.demo.ScriptsExternes.LancementScript;

import java.nio.file.Paths;
import java.util.Arrays;

public class EffetsBoutons {
    public static void CreerAlbum(String nomAlbum) {
        //sélectionne le dossier d'images à importer (par défaut, choisir AlbumTest/images
        LancementScript.CreerDossierAlbum(nomAlbum);
        AjouterPhotosAAlbum(nomAlbum);
    }

    public static String AjouterPhotosAAlbum(String nomAlbum) {
        String dossierSource = LancementScript.LancementGestionnaireFichiers();
        String dossierDestination = Paths.get(System.getProperty("user.dir"), "WorkingDirectory", nomAlbum, "images").toAbsolutePath().toString();
        return LancementScript.CopieDossierSourc_Dest(dossierSource, dossierDestination);
    }

    public static void VisualiserImages(String nomAlbum, String[] nomImages) {
        LancementScript.AfficherImages(nomAlbum, nomImages);
    }

    public static void exporterImagesInDossier(String nomAlbum, String[] nomsImages) {
        String dossierDestination = LancementScript.LancementGestionnaireFichiers();
        LancementScript.ExporterImages(dossierDestination, nomAlbum, nomsImages);
    }
}
