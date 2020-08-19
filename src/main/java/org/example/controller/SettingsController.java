package org.example.controller;

import com.jfoenix.controls.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import org.example.AudioFormat;
import org.example.Settings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SettingsController {
    private Settings settings = Settings.getInstance();

    @FXML
    private JFXComboBox<AudioFormat> fromComboBox;

    @FXML
    private JFXComboBox<AudioFormat> toComboBox;

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
        settings.setFrom(fromComboBox.getSelectionModel().getSelectedItem());
        settings.setTo(toComboBox.getSelectionModel().getSelectedItem());
        settings.setRecursively(recursionCB.isSelected());
        settings.setOmitRepeatingTracks(ommitReapetitionCB.isSelected());
    }

    @FXML
    public void initialize() {
        ObservableList<AudioFormat> allFormats = FXCollections.observableArrayList(AudioFormat.values());
        allFormats.remove(AudioFormat.ERROR);

        fromComboBox.setItems(allFormats);
        toComboBox.setItems(allFormats);

        fromComboBox.getSelectionModel().select(settings.getFrom());
        toComboBox.getSelectionModel().select(settings.getTo());

        recursionCB.setSelected(settings.isRecursively());
        ommitReapetitionCB.setSelected(settings.isOmitRepeatingTracks());
    }
}

