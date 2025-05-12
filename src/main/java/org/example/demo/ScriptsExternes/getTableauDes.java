package org.example.demo.ScriptsExternes;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import static org.example.demo.ScriptsExternes.AutresMethodes.getCheminAlbum;

// permet d'obtenir la liste de certains éléments sous la forme d'un tableau de chaînes de charactères

public class getTableauDes {

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


    public static String[] images(String nomAlbum, String categorie, String etiquette) {
        List<String> sortie = new ArrayList<>();
        String cheminJson = Paths.get(getCheminAlbum(nomAlbum), "data.json").toString();
        JSONParser jsonP = new JSONParser();
        try {
            JSONObject jsonO = (JSONObject)jsonP.parse(new FileReader(cheminJson));
            Set<String> keys = jsonO.keySet();
            for (String key : keys) {
                Object o = jsonO.get(key);
                JSONObject image = (JSONObject) o;
                if (image.get(categorie).equals(etiquette)) {
                    Object o1 = image.get("nom");
                    String nom = (String) o1;
                    sortie.add(nom);
                }
            }


        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } catch (ParseException e) {
        };
        return sortie.toArray(new String[0]);
    }
}
