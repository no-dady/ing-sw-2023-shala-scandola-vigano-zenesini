package client.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class ThreePlayersScreenController implements GenericInterface, Initializable {
    public static final String name ="three-players-screen";
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void onBackPress(ActionEvent event) {
    }
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void update() {

    }
}