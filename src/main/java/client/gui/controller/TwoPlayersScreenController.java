package client.gui.controller;

import client.gui.GUI;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class TwoPlayersScreenController implements GenericInterface, Initializable {
    public static final String name ="two-players-screen";
    private GUI gui;
    @Override
    public void setGUI(GUI gui) {
        this.gui = gui;
    }
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