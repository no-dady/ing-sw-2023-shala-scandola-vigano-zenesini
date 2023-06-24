package client.gui.controller;

import client.gui.GUI;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class FourPlayersScreenController implements GenericInterface, Initializable {
    public static final String name = "four-players-screen";
    public Label L3;
    public Label L4;
    public Label L5;
    private GUI gui;



    public Pane t2_1_1, t2_3_1, t2_5_1, t2_7_1, t2_9_1, t2_11_1,
                t2_1_3, t2_3_3, t2_5_3, t2_7_3, t2_9_3, t2_11_3,
                t2_1_5, t2_3_5, t2_5_5, t2_7_5, t2_9_5, t2_11_5,
                t2_1_7, t2_3_7, t2_5_7, t2_7_7, t2_9_7, t2_11_7,
                t2_1_9, t2_3_9, t2_5_9, t2_7_9, t2_9_9, t2_11_9;

    public Pane t3_1_1, t3_3_1, t3_5_1, t3_7_1, t3_9_1, t3_11_1,
                t3_1_3, t3_3_3, t3_5_3, t3_7_3, t3_9_3, t3_11_3,
                t3_1_5, t3_3_5, t3_5_5, t3_7_5, t3_9_5, t3_11_5,
                t3_1_7, t3_3_7, t3_5_7, t3_7_7, t3_9_7, t3_11_7,
                t3_1_9, t3_3_9, t3_5_9, t3_7_9, t3_9_9, t3_11_9;

    public Pane t4_1_1, t4_3_1, t4_5_1, t4_7_1, t4_9_1, t4_11_1,
                t4_1_3, t4_3_3, t4_5_3, t4_7_3, t4_9_3, t4_11_3,
                t4_1_5, t4_3_5, t4_5_5, t4_7_5, t4_9_5, t4_11_5,
                t4_1_7, t4_3_7, t4_5_7, t4_7_7, t4_9_7, t4_11_7,
                t4_1_9, t4_3_9, t4_5_9, t4_7_9, t4_9_9, t4_11_9;

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
        /*L3.setText(gui.getClient().getGame().getPlayers().get(0).getUserName());
        L4.setText(gui.getClient().getGame().getPlayers().get(1).getUserName());
        L5.setText(gui.getClient().getGame().getPlayers().get(2).getUserName());*/

    }

    @Override
    public void setGUI(GUI gui) {
        this.gui = gui;
    }
}
