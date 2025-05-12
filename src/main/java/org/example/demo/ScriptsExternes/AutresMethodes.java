package org.example.demo.ScriptsExternes;

import java.nio.file.Paths;

public class AutresMethodes {
    public static String getCheminAlbum(String nomAlbum) {
        return Paths.get(System.getProperty("user.dir"), "WorkingDirectory", nomAlbum).toString();
    }

    public static String getCheminImage(String nomAlbum, String nomImage) {
        return Paths.get(System.getProperty("user.dir"), "WorkingDirectory", nomAlbum, "images", nomImage).toString();
    }

    public static String[] getCheminImage(String nomAlbum, String[] nomImages) {
        String[] sortie = new String[nomImages.length];
        for (int i = 0; i < nomImages.length; i++) {
            sortie[i] = getCheminImage(nomAlbum, nomImages[i]);
        }
        return sortie;
    }
}
