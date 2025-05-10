package org.example.demo.ui;

import javafx.stage.Stage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InterfaceAppTest {

    @Test
    void start() throws Exception {
        new InterfaceApp().start(new Stage());
    }
}