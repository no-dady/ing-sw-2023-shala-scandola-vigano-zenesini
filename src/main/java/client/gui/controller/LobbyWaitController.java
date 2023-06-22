package client.gui.controller;

import client.gui.GUI;
import client.network.State;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class LobbyWaitController implements GenericInterface, Initializable {
    @FXML
    public ImageView spinning_cat;
    private GUI gui;
    public Label dialog_label;
    public Label player_1;
    public Label player_2;
    public Label player_3;
    public Label player_4;
    public static final String name ="lobby-wait";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        RotateTransition spinning = new RotateTransition();

        spinning.setNode(spinning_cat);
        spinning.setAxis(Rotate.Z_AXIS);
        spinning.setByAngle(360);
        spinning.setCycleCount(50000);
        spinning.setDuration(Duration.millis(1000));

        spinning.play();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setGUI(GUI gui) {
        this.gui = gui;
    }

    @Override
    public void update() {

        player_1.setText("primo");
        player_2.setText("secondo");
        player_3.setText("terzo");
        player_4.setText("quarto");

    }
}
