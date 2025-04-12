module org.example.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.demo to javafx.fxml;
    exports org.example.demo;
    exports org.example.demo.ui;
    opens org.example.demo.ui to javafx.fxml;
}