package sample.controller;

import javafx.scene.control.Alert;

import javafx.event.ActionEvent;
/**
 * Created by George G.
 */
public class RootLayoutController {

    // /Exit the program
    public void handleExit(ActionEvent actionEvent) {
        System.exit(0);
    }

    // Help Menu Button behavior
    public void handleHelp(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Program Information");
        alert.setHeaderText("This is a JavaFX sample application");
        alert.setContentText("You can search, update, delete or create a new Employee.");
        alert.show();
    }

}
