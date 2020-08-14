package org.example.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import org.example.AudioFormat;
import org.example.Settings;

import java.util.ArrayList;
import java.util.List;

public class SettingsController {
    private Settings settings;

    @FXML
    private JFXComboBox<String> fromComboBox;

    @FXML
    private JFXComboBox<String> toComboBox;

    @FXML
    private JFXCheckBox recursionCB;

    @FXML
    private JFXCheckBox ommitReapetitionCB;

    @FXML
    private JFXButton applyBt;

    @FXML
    void ommitRepetition(ActionEvent event) {

    }

    @FXML
    void recursionPressed(ActionEvent event) {

    }

    @FXML
    void apply(ActionEvent event) {

    }

    @FXML
    public void initialize() {
        Platform.runLater(this::init);

    }

    private void init() {
        addAudioFormatsToOptions();
        readSettingsClass();
    }

    private void readSettingsClass() {
        readFromFormat();
        readToFormat();
    }

    private void readFromFormat() {
        ObservableList<String> ol = fromComboBox.getItems();
        for(String s : ol) {
            if(s.compareTo(settings.getFrom().toString()) == 0) {
                System.out.println(s);
            }
        }
        fromComboBox.getSelectionModel().select(settings.getFrom().getName());
    }

    private void readToFormat() {
        ObservableList<String> ol = toComboBox.getItems();
        for(String s : ol) {
            if(s.compareTo(settings.getTo().toString()) == 0) {
                System.out.println(s);
            }
        }
        toComboBox.getSelectionModel().select(settings.getTo().getName());
    }

    private List<String> getAllAudioFormats(){
        List<String> allAudioFormats = new ArrayList<>();
        for(AudioFormat af : AudioFormat.values())
            allAudioFormats.add(af.getName());

        return allAudioFormats;
    }

    private void addAudioFormatsToOptions() {
        ObservableList<String> optionsFrom = FXCollections.observableArrayList(getAllAudioFormats());
        ObservableList<String> optionsTo = FXCollections.observableArrayList(getAllAudioFormats());
        fromComboBox.setItems(optionsFrom);
        toComboBox.setItems(optionsTo);
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }
}

