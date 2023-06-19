package client.gui;

import client.UI;
//import network.ConnectionType;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import util.Messages.Message;


import java.io.IOException;

public class GUI extends Application implements UI {
    @Override
    public void update() {}

    @Override
    public void setActive() {}

    @Override
    public void printConnectionMessage(Message message) {}

    public static void main(String[] args)
    {
        launch(args);
    }

    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GUI.class.getResource("/fxml/board.fxml")); //init-screen.fxml
        Scene scene = new Scene(fxmlLoader.load()); //1386,400 - 1000,800
        primaryStage.getIcons().add(new Image(this.getClass().getResource("/images/icon.png").toString()));
        primaryStage.setTitle("My Shelfie");
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        //primaryStage.setMaximized(true);
        primaryStage.show();

        primaryStage.setFullScreen(true);

    }

    @Override
    public void setNickname(String nickname) {

    }


    @Override
    public void setPlayerNumber(int playerNumber) {

    }

    @Override
    public void setSelectedTiles(String selectedTiles) {

    }

    @Override
    public void setSelectedColum(int selectedColum) {

    }

    @Override
    public void getInfoAboutOtherPlayers(String playerNickname) {

    }
}
