package org.example.demo.ScriptsExternes;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
class getListTest {

    @Test
    void albums() {
        System.out.println(Arrays.toString(getList.albums()));
    }

    @Test
    void categories() throws IOException {
        System.out.println(Arrays.toString(getList.categories()));
    }

    @Test
    void etiquettes() throws IOException {
        System.out.println(Arrays.toString(getList.etiquettes("Couleurs")));
    }
}