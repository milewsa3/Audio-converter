package org.example;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.List;
import java.util.Optional;

public class AlertPrinter {

    public static void showSetSettingsAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Configuration error");
        alert.setHeaderText("Configuration is not set");
        alert.setContentText("Configuration must be set before searching");
        alert.showAndWait();
    }

    public static boolean isUserWantToOverrideTracks(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("List is not empty");
        alert.setContentText("You have already added some tracks for conversion. \nDo you want to override them?");

        Optional<ButtonType> result = alert.showAndWait();
        return result.get() == ButtonType.OK;
    }

    public static void showNoSuchFormatAlert(AudioFormat format) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Search information");
        alert.setHeaderText("No such files of the desired format " + "\"" + format.getName() + "\"");
        alert.setContentText("You have to choose another format or folder\nfor searching purposes");
        alert.showAndWait();
    }

    public static boolean showConfirmationAlert() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Are you sure?");
        alert.setContentText("You won't be able to undo this action!");

        Optional<ButtonType> result = alert.showAndWait();
        return result.get() == ButtonType.OK;
    }

    public static void showAudioInformation(AudioFile af) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setResizable(true);
        alert.setHeight(400f);
        alert.setTitle("Music information");
        alert.setHeaderText("Information about audio file");
        alert.setContentText(af.toString());
        alert.showAndWait();
    }

    public static void showNoFilesForConversionAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Conversion error");
        alert.setHeaderText("No files for conversion");
        alert.setContentText("First, you have to add tracks for conversion");
        alert.showAndWait();
    }

    public static void showAlreadyConvertedFiles(List<AudioFile> audioFiles) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Conversion info");
        alert.setHeaderText("These files are already converted");
        StringBuilder sb = new StringBuilder();
        for(AudioFile af : audioFiles)
            sb.append(af).append("\n\n");
        alert.setContentText(sb.toString());
        alert.showAndWait();
    }
}
