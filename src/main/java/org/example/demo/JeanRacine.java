package org.example.demo;
import java.nio.file.*;

public class JeanRacine {
    public static void main(String[] args) {
        Path path = Paths.get("demo");
        System.out.println(path.toString());
        String racineProjet = System.getProperty("user.dir");
        System.out.println("Racine du projet : " + racineProjet);
    }
}
