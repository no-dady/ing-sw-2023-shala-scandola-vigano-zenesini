package client.gui.controller;

import client.gui.GUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import util.Messages.CreateLobbyMessage;
import util.Parser;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import static java.lang.Thread.sleep;

public class LobbySetController implements GenericInterface, Initializable {

    Integer selectedNumber = null;
    private GUI gui;
    public static final String name ="lobby-set";
    @FXML
    public ChoiceBox<Integer> playersNumberBox;
    @FXML
    public AnchorPane anchor;
    @FXML
    public ImageView background;
    @FXML
    public Button play_button;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        playersNumberBox.getItems().add(2);
        playersNumberBox.getItems().add(3);
        playersNumberBox.getItems().add(4);
    }
    @FXML
    public void onNumChoice(ActionEvent event) {
        selectedNumber = playersNumberBox.getValue();
    }


    public void onSubmitClick(ActionEvent event) throws IOException, InterruptedException {
        selectedNumber = playersNumberBox.getValue();
        if (!(selectedNumber < 2 || selectedNumber > 4)) {
            CreateLobbyMessage createLobbyMessage = new CreateLobbyMessage(gui.getNickname(), selectedNumber);
            String messageParsed = Parser.toJson(createLobbyMessage, CreateLobbyMessage.class);
            try {
                gui.getClient().sendToServer(messageParsed);
                gui.update();
            } catch (RemoteException e) {
            }
        }
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
