package it.polimi.client.gui.controller;

import it.polimi.client.gui.GUI;
import it.polimi.client.gui.guiMoves.GUISetupAll;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import it.polimi.setup.SetupAll;
import it.polimi.util.Parser;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.Thread.sleep;

public class InitErrorController implements GenericInterface, Initializable {
    private GUI gui;
    public final String dim = "1386x430";

    public String nickname;
    public static final String name ="init-nickname";
    @FXML
    public TextField nickname_field;
    @FXML
    public Button submit;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @Override
    public String getDimensions() {
        return  this.dim;
    }
    public void onSubmitClick(ActionEvent event) throws IOException, InterruptedException {

        nickname = nickname_field.getText();

        if(nickname !=null){
            String Nickname = nickname;
            gui.setNickname(nickname);
            gui.getClient().getClientConnection().getServerInterface().sendSetupAll(Parser.toJson(new GUISetupAll().create(nickname, "0"), SetupAll.class));
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
    public void setStage(Stage stage) {
    }

    @Override
    public void setGUI(GUI gui) {
        this.gui = gui;
    }

}
