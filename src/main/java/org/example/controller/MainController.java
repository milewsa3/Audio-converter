package org.example.controller;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import org.example.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class MainController {
    private Settings settings = new Settings();
    private MusicFileLibrary library = new MusicFileLibrary();

    @FXML
    private JFXButton settingsBt;

    @FXML
    private JFXButton selectBt;

    @FXML
    private JFXButton startBt;

    @FXML
    private JFXListView<String> trackForConversion;

    @FXML
    private JFXButton addBt;

    @FXML
    private JFXButton rmBt;

    @FXML
    private JFXListView<String> resultOfConversion;

    @FXML
    private JFXButton saveToFileBt;

    @FXML
    void addTrackForConv(ActionEvent event) {

    }

    @FXML
    void chooseFolder(ActionEvent event) {
        File folder = getUserFolder();
        System.out.println(folder);
        if(folder == null)
            return;


        if(isTracksForConversionListViewEmpty()) {
            addMusicFilesToLibrary(folder);
            addTracksToListView();
        }
        else if(!isTracksForConversionListViewEmpty() && isUserWantToOverrideTracks()) {
            clearTracksForConversion();
            addMusicFilesToLibrary(folder);
            addTracksToListView();
        }
    }

    private void clearTracksForConversion() {
        library.clear();
        trackForConversion.getItems().clear();
    }

    private void addMusicFilesToLibrary(File folder) {
        List<File> musicFiles = Searcher.searchForExtensions(folder, AudioFormat.WAV, true);
        library.addAllSongs(musicFiles);
    }

    private File getUserFolder() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        return directoryChooser.showDialog(selectBt.getScene().getWindow());
    }

    private boolean isTracksForConversionListViewEmpty() {
        ObservableList<String> olist = trackForConversion.getItems();
        if(olist != null && olist.size() > 0)
            return false;
        return true;
    }

    private boolean isUserWantToOverrideTracks(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("List is not empty");
        alert.setContentText("You have already added some tracks for conversion. \nDo you want to override them?");

        Optional<ButtonType> result = alert.showAndWait();
        return result.get() == ButtonType.OK;
    }

    private void addTracksToListView() {
        ObservableList<String> items = FXCollections.observableArrayList(library.getMusicFilesNames());
        trackForConversion.setItems(items);
    }

    @FXML
    void openSettings(ActionEvent event) throws IOException {
        App.openSettings("settings",settings);
    }

    @FXML
    void removeTrackForConv(ActionEvent event) {

    }

    @FXML
    void saveResults(ActionEvent event) {

    }

    @FXML
    void startConversion(ActionEvent event) {

    }

    @FXML
    public void initialize() {
//        List<String> tracks = new ArrayList<>();
//        tracks.add("Track 1");
//        tracks.add("Track 2");
//        tracks.add("Track 3");
//        ObservableList<String> items = FXCollections.observableArrayList(tracks);
//        trackForConversion.setItems(items);
    }
}
