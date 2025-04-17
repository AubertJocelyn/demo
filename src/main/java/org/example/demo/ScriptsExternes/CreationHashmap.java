package org.example.demo.ScriptsExternes;

import java.io.File;
import java.util.HashMap;

public class CreationHashmap {
    public static HashMap<String, String> main(String CheminDossier) {
        HashMap<String, String> Table = new HashMap<>();

        File dossier = new File(CheminDossier);
        File[] fichiers = dossier.listFiles();
        for (File f : fichiers) {
            Table.put(f.getName(), f.getAbsolutePath());
        }

        return Table;
    }
}