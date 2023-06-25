package client.gui.controller;

import client.gui.GUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.util.ResourceBundle;

import static java.lang.Thread.sleep;

public class InitController implements GenericInterface, Initializable {
    private GUI gui;
    public final String dim = "1386x400";

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

    public static final String name = "init-screen";

    @FXML
    public void onBoxChoiceClick(ActionEvent event) {
        option = (String) connection_box.getValue();
    }


    @FXML
    public void onPlayButtonClick(ActionEvent event) throws IOException, InterruptedException, NotBoundException {
        do {
            Ip = ip_field.getText();
            System.out.println(Ip);
            port = port_field.getText();
            System.out.println(port);
            connection = option;
            System.out.println(connection);
        }while (!Ip.matches("\\b\\d{1,3}(?:\\.\\d{1,3}){3}\\b") && port.equals("") && !(connection.equals("RMI") || connection.equals("SOCKET")));

            gui.getClient().setConnection(Ip,Integer.parseInt(port),option);
            gui.getClient().setOnline();
            gui.update();

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
    public void update() {

    }

    @Override
    public void setGUI(GUI gui) {
        this.gui = gui;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connection_box.getItems().addAll("RMI","SOCKET");
    }
}

