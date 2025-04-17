package org.example.demo.ScriptsExternes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class getList {

    static String cheminDepotGit = System.getProperty("user.dir");;

    public static String[] albums() {
        String chemin = Paths.get(cheminDepotGit, "WorkingDirectory").toString();
        File dossier = new File(chemin);
        File[] fichiers = dossier.listFiles();
        int taille = fichiers.length;
        String[] sortie = new String[taille];

        for (int i = 0; i < taille; i+=1) {
            sortie[i] = fichiers[i].getName();
        }

        return sortie;
    }

    public static String[] categories() throws IOException {
        String chemin = Paths.get(cheminDepotGit, "ListeCategoriesEtEtiquettes.txt").toString();
        BufferedReader lecteur = new BufferedReader(new FileReader(chemin));

        String ligne;
        List<String> liste = new ArrayList<>();
        while ((ligne = lecteur.readLine()) != null) {liste.add(ligne.split(" ")[0]);}

        return liste.toArray(new String[0]);
    }

    public static String[] etiquettes(String nomCategorie) throws IOException {
        String chemin = Paths.get(cheminDepotGit, "ListeCategoriesEtEtiquettes.txt").toString();
        BufferedReader lecteur = new BufferedReader(new FileReader(chemin));

        String ligne;
        String[] sortie = new String[0];
        while ((ligne = lecteur.readLine()) != null) {
            if (ligne.split(" ")[0].equals(nomCategorie)) {
                sortie = Arrays.copyOfRange(ligne.split(" "), 1, ligne.split(" ").length);
            }
        }
        return sortie;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(Arrays.toString(getList.etiquettes("Couleurs")));
    }
}
