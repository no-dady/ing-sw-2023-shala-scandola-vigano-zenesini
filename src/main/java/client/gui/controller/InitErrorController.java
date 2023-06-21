package client.gui.controller;

import client.gui.GUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.Thread.sleep;

public class InitErrorController implements GenericInterface, Initializable {
    private GUI gui;
    public String temp;
    public static final String name ="init-nickname";
    @FXML
    public TextField text_field;
    @FXML
    public Button submit;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void onSubmitClick(ActionEvent event) throws IOException, InterruptedException {
        temp = text_field.getText();
        System.out.println(temp);

        if(temp!=null){
            String Nickname = temp;

            sleep(2000);
        }
        else{
            System.out.println("Insert Nickname");
            Alert alert = alertCreator();
            alert.setContentText("Set the Nickname");
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

}
