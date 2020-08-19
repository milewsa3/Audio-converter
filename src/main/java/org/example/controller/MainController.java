package org.example.controller;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import org.example.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class MainController {
    private Settings settings = Settings.getInstance();
    private MusicFileLibrary library = new MusicFileLibrary();

    @FXML
    private JFXButton settingsBt;

    @FXML
    private JFXButton selectBt;

    @FXML
    private JFXButton startBt;

    @FXML
    private JFXListView<MusicFile> trackForConversion;

    @FXML
    private JFXButton addBt;

    @FXML
    private JFXButton rmBt;

    @FXML
    private JFXButton clearBt;

    @FXML
    private JFXListView<MusicFile> resultOfConversion;

    @FXML
    private JFXButton saveToFileBt;

    @FXML
    void openSettings(ActionEvent event) throws IOException {
        App.openInNewWindowAndWait("settings","Configuration");
    }

    @FXML
    void chooseFolder(ActionEvent event) {
        File folder = getUserFolder();

        if(folder == null)
            return;

        if(!areSettingsSet()) {
            showSetSettingsAlert();
            return;
        }

        if(!library.isEmpty() && isUserWantToOverrideTracks()) {
            library.clear();
        }

        addMusicFiles(folder);

    }

    private File getUserFolder() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        return directoryChooser.showDialog(selectBt.getScene().getWindow());
    }

    private boolean areSettingsSet() {
        return settings.getFrom() != null && settings.getTo() != null;
    }

    private void showSetSettingsAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Configuration error");
        alert.setHeaderText("Configuration is not set");
        alert.setContentText("Configuration must be set before choosing folder for search");
        alert.showAndWait();
    }

    private boolean isUserWantToOverrideTracks(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("List is not empty");
        alert.setContentText("You have already added some tracks for conversion. \nDo you want to override them?");

        Optional<ButtonType> result = alert.showAndWait();
        return result.get() == ButtonType.OK;
    }


    private void addMusicFiles(File folder) {
        List<File> musicFiles = Searcher.searchForExtensions(folder, settings.getFrom(), settings.isRecursively());
        library.addAllSongs(musicFiles);
    }

    @FXML
    void addTrackForConv(ActionEvent event) throws IOException {
        App.openInNewWindowAndWait("add","Adding Window");
    }

    @FXML
    void removeTrackForConv(ActionEvent event) {

        final int selectedIdx = trackForConversion.getSelectionModel().getSelectedIndex();
        if (selectedIdx != -1) {
            MusicFile itemToRemove = trackForConversion.getSelectionModel().getSelectedItem();

            final int newSelectedIdx =
                    (selectedIdx == trackForConversion.getItems().size() - 1)
                            ? selectedIdx - 1
                            : selectedIdx;

            library.remove(itemToRemove);
            trackForConversion.getSelectionModel().select(newSelectedIdx);
        }
    }

    @FXML
    void clearTracksForConversionLV(ActionEvent event) {
        library.clear();
    }

    @FXML
    void saveResults(ActionEvent event) {

    }

    @FXML
    void startConversion(ActionEvent event) {
        boolean result = showConfirmationAlert();
        if(!result)
            return;

        System.out.println("START");
    }

    private boolean showConfirmationAlert() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Are you sure?");
        alert.setContentText("You won't be able to undo this action!");

        Optional<ButtonType> result = alert.showAndWait();
        return result.get() == ButtonType.OK;
    }

    @FXML
    public void initialize() {
        initTracksForConversionLV();
    }

    private void initTracksForConversionLV() {
        trackForConversion.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(MusicFile musicFile, boolean empty) {
                super.updateItem(musicFile, empty);

                if(empty || musicFile == null || musicFile.getName() == null) {
                    setText(null);
                } else {
                    setText(musicFile.getName());
                }
            }
        });

        trackForConversion.setItems(library.getMusicFiles());

        trackForConversion.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getClickCount() == 2 && trackForConversion.getItems().size() > 0) {
                    MusicFile currentItemSelected = trackForConversion.getSelectionModel().getSelectedItem();
                    showFormatInformation(currentItemSelected);
                }
            }
        });
    }

    private void showFormatInformation(MusicFile mf) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setResizable(true);
        alert.setHeight(400f);
        alert.setTitle("Music information");
        alert.setHeaderText("Music file: " + mf.getName());
        alert.setContentText(mf.getPath().toString());
        alert.showAndWait();
    }
}
