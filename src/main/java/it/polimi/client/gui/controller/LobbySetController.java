package it.polimi.client.gui.controller;

import it.polimi.client.gui.GUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import it.polimi.util.Messages.CreateLobbyMessage;
import it.polimi.util.Parser;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

/**
 * <p>LobbySetController class.</p>
 *
 * @author daniel
 * @version $Id: $Id
 */
public class LobbySetController implements GenericInterface, Initializable {

    Integer selectedNumber = null;
    public final String dim = "1000x830";

    private GUI gui;
    /** Constant <code>name="lobby-set"</code> */
    public static final String name ="lobby-set";
    @FXML
    public ChoiceBox<Integer> playersNumberBox;
    @FXML
    public AnchorPane anchor;
    @FXML
    public ImageView background;
    @FXML
    public Button play_button;

    /** {@inheritDoc} */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        playersNumberBox.getItems().add(2);
        playersNumberBox.getItems().add(3);
        playersNumberBox.getItems().add(4);
    }
    /** {@inheritDoc} */
    @Override
    public String getDimensions() {
        return  this.dim;
    }
    /**
     * <p>onNumChoice.</p>
     *
     * @param event a {@link javafx.event.ActionEvent} object
     */
    @FXML
    public void onNumChoice(ActionEvent event) {
        selectedNumber = playersNumberBox.getValue();
    }

    /**
     * <p>onSubmitClick.</p>
     *
     * @param event a {@link javafx.event.ActionEvent} object
     */
    public void onSubmitClick(ActionEvent event) {
        selectedNumber = playersNumberBox.getValue();
        if (!(selectedNumber < 2 || selectedNumber > 4)) {
            CreateLobbyMessage createLobbyMessage = new CreateLobbyMessage(gui.getNickname(), selectedNumber);
            String messageParsed = Parser.toJson(createLobbyMessage, CreateLobbyMessage.class);
            try {
                gui.getClient().sendToServer(messageParsed);
            } catch (RemoteException ignored) {
            }
        }
    }
    /** {@inheritDoc} */
    @Override
    public String getName() {
        return name;
    }

    /** {@inheritDoc} */
    @Override
    public void update() {

    }
    /** {@inheritDoc} */
    @Override
    public void setStage(Stage stage) {
    }

    /** {@inheritDoc} */
    @Override
    public void setGUI(GUI gui) {
        this.gui = gui;
    }
}
