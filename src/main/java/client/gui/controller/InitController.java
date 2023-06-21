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
import java.util.ResourceBundle;

import static java.lang.Thread.sleep;

public class InitController implements GenericInterface, Initializable {
    private GUI gui;

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
    public TextField text_field;
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
        System.out.println(option);
    }


    @FXML
    public void onPlayButtonClick(ActionEvent event) throws IOException, InterruptedException {
        temp = text_field.getText();

        System.out.println(temp);

        if(!temp.equals("") && option!=null){

            port=port_field.getText();
            connection = option;
            Ip = temp;

            sleep(2000);

        }
        if(!temp.equals("") && option==null){
            System.out.println("Select connection type");
            Alert alert = alertCreator();
            alert.setContentText("Set the connection type");
            alert.showAndWait();
        }
        if(temp.equals("") && option!=null){
            System.out.println("Insert Ip");
            Alert alert = alertCreator();
            alert.setContentText("Set the Ip");
            alert.showAndWait();
        }
        if(temp.equals("") && option==null){
            System.out.println("Insert data");
            Alert alert = alertCreator();
            alert.setContentText("Insert data");
            alert.showAndWait();
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connection_box.getItems().addAll("RMI","Socket");
    }
}

