package org.example.demo;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class LancementGestionnaireFichiers {
    public static String main() {

        String line = "";

        // Le chemin du script Bash à exécuter
        String scriptPath = "src/main/java/org/example/demo/LancementGestionnaireFichiers.sh";

        try {
            // Créer un processus pour exécuter le script Bash
            ProcessBuilder processBuilder = new ProcessBuilder("bash", scriptPath);

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
}