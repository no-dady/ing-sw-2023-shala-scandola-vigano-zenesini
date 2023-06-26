package client.gui.controller;

import client.gui.GUI;
import client.network.State;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import server.model.Player;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LobbyWaitController implements GenericInterface, Initializable {
    @FXML
    public ImageView spinning_cat;
    private GUI gui;
    public final String dim = "1000x830";
    public Label dialog_label;
    public Label player_1 = null;
    public Label player_2 = null;
    public Label player_3 = null;
    public Label player_4 = null;
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
    public String getDimensions() {
        return  this.dim;
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
        if(gui!=null){
            List<Label> players = List.of(player_1,player_2,player_3,player_4);
            for(int i = 0; i<gui.getClient().getPlayerInLobby().size();i++){
                players.get(i).setText(gui.getClient().getPlayerInLobby().get(i));
                }}}
}
