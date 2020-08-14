module org.example {
    requires javafx.controls;
    requires javafx.base;
    requires javafx.fxml;
    requires com.jfoenix;

    opens org.example to javafx.fxml;
    opens org.example.controller to javafx.fxml;
    exports org.example;
    exports org.example.controller;
}