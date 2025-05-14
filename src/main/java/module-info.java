module org.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires json.simple;


    opens org.example.demo to javafx.fxml;
    exports org.example.demo.ui;
    opens org.example.demo.ui to javafx.fxml;
}