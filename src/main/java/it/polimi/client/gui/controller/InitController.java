package it.polimi.client.gui.controller;

import it.polimi.client.gui.GUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.util.ResourceBundle;

/**
 * <p>InitController class.</p>
 *
 * @author daniel
 * @version $Id: $Id
 */
public class InitController implements GenericInterface, Initializable {
    private GUI gui;
    public final String dim = "1386x430";

    public String connection;
    public String option;
    public String Ip;
    public String temp;
    public String port;
    @FXML
    public AnchorPane anchor;
    @FXML
    public ImageView background;
    @FXML
    public TextField ip_field;
    @FXML
    public ChoiceBox<String> connection_box;
    @FXML
    public Button play_button;
    @FXML
    public TextField port_field;

    /** Constant <code>name="init-screen"</code> */
    public static final String name = "init-screen";

    /**
     * <p>onBoxChoiceClick.</p>
     *
     * @param event a {@link javafx.event.ActionEvent} object
     */
    @FXML
    public void onBoxChoiceClick(ActionEvent event) {
        option = connection_box.getValue();
    }


    /**
     * <p>onPlayButtonClick.</p>
     *
     * @param event a {@link javafx.event.ActionEvent} object
     * @throws java.io.IOException if any.
     * @throws java.rmi.NotBoundException if any.
     */
    @FXML
    public void onPlayButtonClick(ActionEvent event) throws IOException, NotBoundException {
        do {
            Ip = ip_field.getText();
            //System.out.println(Ip);
            port = port_field.getText();
            //System.out.println(port);
            connection = option;
            //System.out.println(connection);
        }while (!Ip.matches("\\b\\d{1,3}(?:\\.\\d{1,3}){3}\\b") && port.equals("") && !(connection.equals("RMI") || connection.equals("SOCKET")));

            gui.getClient().setConnection(Ip,Integer.parseInt(port),option);
            gui.getClient().setOnline();
            gui.getClient().setActive(true);

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
    public void update() {

    }

    /** {@inheritDoc} */
    @Override
    public void setGUI(GUI gui) {
        this.gui = gui;
    }

    /** {@inheritDoc} */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connection_box.getItems().addAll("RMI","SOCKET");
    }
    /** {@inheritDoc} */
    @Override
    public void setStage(Stage stage) {
    }
}

