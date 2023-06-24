package client.gui.controller;

import client.gui.GUI;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class TwoPlayersScreenController implements GenericInterface, Initializable {
    public static final String name ="two-players-screen";
    public Pane t2_1_1, t2_3_1, t2_5_1, t2_7_1, t2_9_1, t2_11_1,
                t2_1_3, t2_3_3, t2_5_3, t2_7_3, t2_9_3, t2_11_3,
                t2_1_5, t2_3_5, t2_5_5, t2_7_5, t2_9_5, t2_11_5,
                t2_1_7, t2_3_7, t2_5_7, t2_7_7, t2_9_7, t2_11_7,
                t2_1_9, t2_3_9, t2_5_9, t2_7_9, t2_9_9, t2_11_9;
    private GUI gui;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        



    }


    public void onBackPress(ActionEvent event) {
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

    }
}