package client.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuScreenController implements GenericInterface, Initializable {

    public Button B1;
    public Button B2;
    public StackPane mainContainer;
    public GridPane mainGrid;
    public Pane menuImage;
    public static final String name ="menu-screen";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void onQuitButtonPress(ActionEvent event) {
    }

    public void onBackButtonPress(ActionEvent event) {
    }
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void update() {

    }
}