package it.polimi.client.gui.controller;

import it.polimi.client.gui.GUI;
import it.polimi.server.model.Player;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

/**
 * <p>VictoryScreenController class.</p>
 *
 * @author daniel
 * @version $Id: $Id
 */
public class VictoryScreenController implements GenericInterface, Initializable {
    /** Constant <code>name="victory-screen"</code> */
    public static final String name ="victory-screen";
    public Label FirstPlayer;
    public Label SecondPlayer;
    public Label ThirdPlayer;
    public Label FourthPlayer;
    public Label PointsOfFirst;
    public Label PointsOfSecond;
    public Label PointsOfThird;
    public Label PointsOfFourth;
    public StackPane mainController;
    private GUI gui;
    public final String dim = "750x900";
    /** {@inheritDoc} */
    @Override
    public void setGUI(GUI gui) {
        this.gui = gui;
    }

    /** {@inheritDoc} */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**
     * <p>onQuitPress.</p>
     *
     * @param event a {@link javafx.event.ActionEvent} object
     */
    public void onQuitPress(ActionEvent event) {
        System.exit(0);
    }
    /** {@inheritDoc} */
    @Override
    public String getName() {
        return name;
    }

    /** {@inheritDoc} */
    @Override
    public void update() {
        List<Label> names = List.of(FirstPlayer, SecondPlayer, ThirdPlayer, FourthPlayer);
        List<Label> points = List.of(PointsOfFirst, PointsOfSecond, PointsOfThird, PointsOfFourth);
        List<Player> ranking = gui.getClient().getGame().getPlayers();
        Collections.sort(ranking, (p1, p2) -> Integer.compare(p2.getScore(), p1.getScore()));
        for (int i = 0; i< gui.getGame().getNumPlayers(); i++){
            names.get(i).setText(ranking.get(i).getUserName());
            points.get(i).setText(String.valueOf(ranking.get(i).getScore()));
        }
    }
    /** {@inheritDoc} */
    @Override
    public void setStage(Stage stage) {
    }
    /** {@inheritDoc} */
    @Override
    public String getDimensions() {
        return  this.dim;
    }
}
