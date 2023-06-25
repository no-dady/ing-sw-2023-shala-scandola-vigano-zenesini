package client.gui.controller;

import client.gui.GUI;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuScreenController implements GenericInterface, Initializable {
    private GUI gui;
    public final String dim = "500x600";

    public Button B1;
    public Button B2;
    public StackPane mainContainer;
    public GridPane mainGrid;
    public Pane menuImage;
    public static final String name ="menu-screen";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @Override
    public String getDimensions() {
        return  this.dim;
    }

    public void onQuitButtonPress(ActionEvent event) {

        System.exit(0);
    }

    public void onBackButtonPress(ActionEvent event) {
        gui.activate(BoardController.name);
    }
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void update() {

    }

    @Override
    public void setGUI(GUI gui) {
        this.gui = gui;
    }
}