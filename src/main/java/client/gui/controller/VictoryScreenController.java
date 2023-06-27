package client.gui.controller;

import client.gui.GUI;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class VictoryScreenController implements GenericInterface, Initializable {
    public static final String name ="victory-screen";
    public StackPane mainController;
    private GUI gui;
    public final String dim = "750x900";
    @Override
    public void setGUI(GUI gui) {
        this.gui = gui;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void onQuitPress(ActionEvent event) {
    }
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void update() {

    }
    @Override
    public void setStage(Stage stage) {
    }
    @Override
    public String getDimensions() {
        return  this.dim;
    }
}
