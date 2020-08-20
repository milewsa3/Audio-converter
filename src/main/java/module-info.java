module org.example {
    requires javafx.controls;
    requires javafx.base;
    requires javafx.fxml;
    requires com.jfoenix;
    requires jave.core;
    requires jave.nativebin.win64;

    opens org.example to javafx.fxml;
    opens org.example.controller to javafx.fxml;
    exports org.example;
    exports org.example.controller;
}