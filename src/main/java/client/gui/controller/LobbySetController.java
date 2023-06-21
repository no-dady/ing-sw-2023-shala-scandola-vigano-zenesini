package client.gui.controller;

import client.gui.GUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.Thread.sleep;

public class LobbySetController implements GenericInterface, Initializable {

    Integer selected_number = null;
    private GUI gui;
    public static final String name ="lobby-set";
    @FXML
    public ChoiceBox<Integer> players_number_box;
    @FXML
    public AnchorPane anchor;
    @FXML
    public ImageView background;
    @FXML
    public Button play_button;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        players_number_box.getItems().add(2);
        players_number_box.getItems().add(3);
        players_number_box.getItems().add(4);
    }
    @FXML
    public void onNumChoice(ActionEvent event) {
        selected_number = players_number_box.getValue();
        System.out.println(selected_number);
    }


    public void onSubmitClick(ActionEvent event) throws IOException, InterruptedException {
        if(selected_number != null){


            sleep(2000);
        }
        else{
            System.out.println("Select number of player");
            Alert alert = alertCreator();
            alert.setContentText("Select number of player");
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
