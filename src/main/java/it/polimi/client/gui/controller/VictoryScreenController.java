package it.polimi.client.gui.controller;

import it.polimi.client.gui.GUI;
import it.polimi.server.model.Player;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class VictoryScreenController implements GenericInterface, Initializable {
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
    @Override
    public void setGUI(GUI gui) {
        this.gui = gui;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void onQuitPress(ActionEvent event) {
        System.exit(0);
    }
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void update() {
        int i = 0;
        List<Label> names = List.of(FirstPlayer, SecondPlayer, ThirdPlayer, FourthPlayer);
        List<Label> points = List.of(PointsOfFirst, PointsOfSecond, PointsOfThird, PointsOfFourth);
        List<Player> ranking = gui.getClient().getGame().getPlayers();
        Collections.sort(ranking, (p1, p2) -> Integer.compare(p2.getScore(), p1.getScore()));
        for(Label x : names){
            x.setText(ranking.get(i).getUserName());
            points.get(i).setText(String.valueOf(ranking.get(i).getScore()));
            i++;
        }
    }
    @Override
    public void setStage(Stage stage) {
    }
    @Override
    public String getDimensions() {
        return  this.dim;
    }
}
