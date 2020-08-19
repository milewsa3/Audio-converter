package org.example.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class AddController {

    @FXML
    private JFXButton addBt;

    @FXML
    private JFXTextField textField;

    @FXML
    void add(ActionEvent event) {
        System.out.println("ADDED");
    }
}
