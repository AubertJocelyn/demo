package org.example.demo.ScriptsExternes;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;

class getTableauDesTest {

    @Test
    void albums() {
        System.out.println(Arrays.toString(getTableauDes.albums()));
    }

    @Test
    void categories() throws IOException {
        System.out.println(Arrays.toString(getTableauDes.categories()));
    }

    @Test
    void etiquettes() throws IOException {
        System.out.println(Arrays.toString(getTableauDes.etiquettes("Couleurs")));
    }
    @Test
    void images() {
        System.out.println(Arrays.toString(getTableauDes.images("TestNouvelAlbum","Couleurs", "noir")));
    }
}