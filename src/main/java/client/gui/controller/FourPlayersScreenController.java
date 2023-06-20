package client.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class FourPlayersScreenController implements GenericInterface, Initializable {
    public static final String name = "four-players-screen";

    public GridPane secondPlayer;
    public GridPane thirdPlayer;
    public GridPane fourthPlayer;

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
