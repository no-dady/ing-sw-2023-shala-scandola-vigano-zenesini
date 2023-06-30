package it.polimi.client.gui.controller;

import it.polimi.client.gui.GUI;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * <p>LobbyWaitController class.</p>
 *
 * @author daniel
 * @version $Id: $Id
 */
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
    /** Constant <code>name="lobby-wait"</code> */
    public static final String name ="lobby-wait";
    /** {@inheritDoc} */
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
    /** {@inheritDoc} */
    @Override
    public String getDimensions() {
        return  this.dim;
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return name;
    }

    /** {@inheritDoc} */
    @Override
    public void setGUI(GUI gui) {
        this.gui = gui;
    }
    /** {@inheritDoc} */
    @Override
    public void setStage(Stage stage) {
    }

    /** {@inheritDoc} */
    @Override
    public void update() {
        if(gui!=null){
            List<Label> players = List.of(player_1,player_2,player_3,player_4);
            for(int i = 0; i<gui.getClient().getPlayerInLobby().size();i++){
                players.get(i).setText(gui.getClient().getPlayerInLobby().get(i));
                }}
    }
}
