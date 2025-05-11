package org.example.demo.ScriptsExternes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LancementScript {

    public static String main(String langage, String scriptPath, String... args) {

        // Construire la commande complète
        List<String> commande = new ArrayList<>();
        commande.add(langage);
        commande.add(scriptPath);
        commande.addAll(Arrays.asList(args));

        String line = "";
        String sortie = "";

        try {
            // Créer un processus pour exécuter le script Bash
            ProcessBuilder processBuilder = new ProcessBuilder(commande);

            // Rediriger la sortie du processus
            processBuilder.redirectErrorStream(true); // Combiner la sortie d'erreur et la sortie standard

            // Démarrer le processus
            Process process = processBuilder.start();

            // Lire la sortie du processus
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while ((line = reader.readLine()) != null) {
                sortie = line;
                System.out.println(sortie);// Afficher la sortie ligne par ligne
            }

            // Attendre que le processus se termine et obtenir le code de sortie
            int exitCode = process.waitFor();
            if (exitCode != 0) {return "false";}
            //System.out.println("Le script s'est terminé avec le code de sortie : " + exitCode);
        } catch (IOException | InterruptedException e) {
            //e.printStackTrace();
        }
        //System.out.println("balise");
        //System.out.println(line);
        return sortie;
    }

    private static String getCheminScript(String typeLangage, String nomScript) {
        return Paths.get(System.getProperty("user.dir"), "src", "main", typeLangage, nomScript).toString();
    }

    private static String getString(String[] strings) {
        String sortie = "";
        for (int i = 0; i < strings.length; i++) {
            sortie = sortie + strings[i] + "§";
        }
        System.out.println(sortie);
        return sortie;
    }

    public static String getCheminImage(String nomAlbum, String nomImage) {
        return Paths.get(System.getProperty("user.dir"), "WorkingDirectory", nomAlbum, "images", nomImage).toString();
    }

    public static String LancementGestionnaireFichiers() {
        //Le script bash a été testé dans une version précédente
        String cheminScript = getCheminScript("bash", "LancementGestionnaireFichiers.sh");
        return LancementScript.main("bash", cheminScript);
    }

    public static String CopieDossierSourc_Dest(String PathSourc, String PathDest) {
        //le script bash a été testé dans le terminal
        String cheminScript = getCheminScript("bash", "CopieDossierSourc_Dest.sh");
        return LancementScript.main("bash", cheminScript, PathSourc, PathDest);
    }

    public static String TrierAlbum(String nomAlbum) {
        String cheminScript = getCheminScript("python", "continuerJson.py");
        return LancementScript.main("python3", cheminScript, nomAlbum);
    }

    public static void CreerDossierAlbum(String nomAlbum) {
        String cheminScript = getCheminScript("bash", "CreerDossierAlbum.sh");
        LancementScript.main("bash", cheminScript, nomAlbum);
    }

    public static String SupprimerAlbum(String nomAlbum) {
        String cheminScript = getCheminScript("bash", "SupprimerAlbum.sh");
        return LancementScript.main("bash", cheminScript, nomAlbum);
    }

    public static void TestCreerFichierVide() {
        String cheminScript = getCheminScript( "python", "fichiertxt.py");
        System.out.println(LancementScript.main("python3", cheminScript));
    }

    public static void AfficherImages(String largeur, String hauteur, String x, String y, String[] cheminsImages) {
        String cheminScript =  getCheminScript( "bash", "AfficherImages.sh");
        LancementScript.main("bash", cheminScript, largeur, hauteur, x, y, getString(cheminsImages));
    }

    public static void AfficherImages(String[] cheminsImages) {
        String largeur = "640";
        String hauteur = "480";
        String x = "640";
        String y = "360";
        AfficherImages(largeur, hauteur, x, y, cheminsImages);
    }
}


