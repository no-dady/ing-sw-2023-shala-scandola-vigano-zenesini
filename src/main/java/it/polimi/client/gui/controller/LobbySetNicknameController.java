package it.polimi.client.gui.controller;

import it.polimi.client.gui.GUI;
import it.polimi.client.gui.guiMoves.GUISetupFirst;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import it.polimi.setup.SetupFirst;
import it.polimi.util.Parser;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LobbySetNicknameController implements GenericInterface, Initializable {

    Integer selectedNumber = null;
    public final String dim = "1000x830";
    private GUI gui;
    public static final String name ="lobby-set-nickname";
    @FXML
    public ChoiceBox<String> playersNumberBox;
    @FXML
    public AnchorPane anchor;
    @FXML
    public ImageView background;
    @FXML
    public Button play_button;
    @FXML
    public TextField nicknameField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        playersNumberBox.getItems().add("2");
        playersNumberBox.getItems().add("3");
        playersNumberBox.getItems().add("4");

    }
    @FXML
    public void onNumChoice(ActionEvent event) {
        selectedNumber = Integer.valueOf(playersNumberBox.getValue());
    }

    public void onSubmitClick(ActionEvent event) throws IOException {
        selectedNumber = Integer.parseInt(playersNumberBox.getValue());
        String nickname = nicknameField.getText();
        if (!(selectedNumber < 2 || selectedNumber > 4) && nickname != null) {
            gui.setNickname(nickname);
            gui.getClient().getClientConnection().getServerInterface().sendSetupFirst(Parser.toJson(new GUISetupFirst().create(nickname, playersNumberBox.getValue()), SetupFirst.class));
        }
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDimensions() {
        return this.dim;
    }

    @Override
    public void update() {

    }
    @Override
    public void setStage(Stage stage) {
    }

    @Override
    public void setGUI(GUI gui) {
        this.gui = gui;
    }
}
