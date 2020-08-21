package org.example.controller;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import org.example.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MainController {
    private Settings settings = Settings.getInstance();
    private AudioFilesLibrary library = new AudioFilesLibrary();
    private AudioFilesLibrary convertedFiles = new AudioFilesLibrary();

    @FXML
    private JFXButton settingsBt;

    @FXML
    private JFXButton selectBt;

    @FXML
    private JFXButton startBt;

    @FXML
    private JFXListView<AudioFile> trackForConversion;

    @FXML
    private JFXButton addBt;

    @FXML
    private JFXButton rmBt;

    @FXML
    private JFXButton clearBt;

    @FXML
    private JFXListView<AudioFile> resultOfConversion;

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
            AlertPrinter.showSetSettingsAlert();
            return;
        }

        if(!library.isEmpty() && AlertPrinter.isUserWantToOverrideTracks()) {
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


    private void addMusicFiles(File folder) {
        Finder finder = new SimpleFinder();
        List<File> musicFiles = finder.searchWithExtension(settings.getFrom(), folder, settings.isRecursively());
        if(musicFiles.size() == 0)
            AlertPrinter.showNoSuchFormatAlert(settings.getFrom());
        library.addAllSongs(musicFiles);
    }

    @FXML
    void addTrackForConv(ActionEvent event) throws IOException {
        if(!areSettingsSet()) {
            AlertPrinter.showSetSettingsAlert();
            return;
        }

        File musicFile = getUserFile();
        if(musicFile == null)
            return;

        library.addSong(musicFile);
    }

    private File getUserFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Music Files", "*." + settings.getFrom().getName()));
        return fileChooser.showOpenDialog(addBt.getScene().getWindow());
    }

    @FXML
    void removeTrackForConv(ActionEvent event) {

        final int selectedIdx = trackForConversion.getSelectionModel().getSelectedIndex();
        if (selectedIdx != -1) {
            AudioFile itemToRemove = trackForConversion.getSelectionModel().getSelectedItem();

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
        boolean result = AlertPrinter.showConfirmationAlert();
        if(!result)
            return;
        if(library.isEmpty()) {
            AlertPrinter.showNoFilesForConversionAlert();
            return;
        }

        System.out.println("START");
        Platform.runLater(() -> {
            new SimpleConverter(library,convertedFiles,settings.getFrom(),settings.getTo()).convert();
            System.out.println("END");
        });

        //new SimpleConverter(library,settings.getFrom(),settings.getTo()).convert();
    }

    @FXML
    public void initialize() {
        initLV(trackForConversion,library);
        initLV(resultOfConversion,convertedFiles);

        //when files are in the lv, you cant edit configuration
        library.getMusicFiles().addListener(new ListChangeListener<AudioFile>() {
            @Override
            public void onChanged(Change<? extends AudioFile> change) {
                settingsBt.setDisable(change.getList().size() > 0);
            }
        });
    }

    private void initLV(JFXListView<AudioFile> listView, AudioFilesLibrary library) {
        listView.setCellFactory(param -> new JFXListCell<>() {
            @Override
            protected void updateItem(AudioFile item, boolean empty) {
                super.updateItem(item, empty);

                if(empty || item == null || item.getName() == null) {
                    setText(null);
                } else {
                    setText(item.getName());
                }
            }
        });

        listView.setItems(library.getMusicFiles());

        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getClickCount() == 2 && listView.getItems().size() > 0) {
                    AudioFile currentItemSelected = listView.getSelectionModel().getSelectedItem();
                    AlertPrinter.showAudioInformation(currentItemSelected);
                }
            }
        });
    }
}
