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

    public static String racineProjet = System.getProperty("user.dir");

    public static String main(String langage, String scriptPath, String... args) {

        // Construire la commande complète
        List<String> commande = new ArrayList<>();
        commande.add(langage);
        commande.add(scriptPath);
        commande.addAll(Arrays.asList(args));

        String line = "";

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
                System.out.println(line); // Afficher la sortie ligne par ligne
            }

            // Attendre que le processus se termine et obtenir le code de sortie
            int exitCode = process.waitFor();
            System.out.println("Le script s'est terminé avec le code de sortie : " + exitCode);
        } catch (IOException | InterruptedException e) {
            //e.printStackTrace();
        }
        return line;
    }

    public String LancementGestionnaireFichiers() {
        //Le script bash a été testé dans une version précédente
        Path chemin = Paths.get(System.getProperty("user.dir"), "src", "main", "java", "org.example.demo", "ScriptsExternes", "LancementGestionnaireFichiers.sh");
        return LancementScript.main("bash", chemin.toAbsolutePath().toString());
    }

    public void CopieDossierSourc_Dest(String PathSourc, String PathDest) {
        //le script bash a été testé dans le terminal
        LancementScript.main("bash", "demo/src/ScriptsExternes/CopieDossierSourc_Dest.sh", PathSourc, PathDest);
    }

    public static void TrierAlbum(String nomAlbum) {
        Path chemin = Paths.get(System.getProperty("user.dir"), "src", "main", "python", "continuerJson.py");
        LancementScript.main("python3", chemin.toAbsolutePath().toString(), nomAlbum);
        }
}

