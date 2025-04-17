package org.example.demo;

import java.util.HashMap;

public class Test {
    public static void main(String[] args) {
        HashMap<String, String> Table = new HashMap<>();
        //Table = CreationHashmap.main("/home/jocelynaubert/test/dest");

        for (String cle : Table.keySet()) {
            System.out.println(cle + " => " + Table.get(cle));
        }
    }
}
