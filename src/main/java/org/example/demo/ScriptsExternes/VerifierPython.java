package org.example.demo.ScriptsExternes;

import java.io.*;

public class VerifierPython {
    public static void main(String[] args) {
        try {
            // Commande pour vérifier la version de Python3
            ProcessBuilder pb = new ProcessBuilder("python3", "--version");
            pb.redirectErrorStream(true);
            Process process = pb.start();

            // Lire la sortie du processus (version de Python3)
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);  // Affiche la version de Python3
            }

            // Attendre que le processus se termine
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("Python3 est accessible.");
            } else {
                System.out.println("Erreur lors de l'exécution de la commande Python3.");
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}