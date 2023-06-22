package client.gui;

import client.Client;
import client.UI;
import client.gui.controller.*;
import client.network.State;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import util.Messages.Message;
import java.util.ArrayList;
import java.util.HashMap;

public class GUI extends Application implements UI {
    HashMap<String, FXMLLoader> loaderMap = new HashMap<>();;
    private Scene main;
    private GenericInterface current;
    private String nickname = "";

    public String getNickname() {
        return nickname;
    }

    public Client getClient() {
        return client;
    }

    private static Client client;

    public GUI() {
        super();
    }

    @Override
    public void update() {
        Platform.runLater(this::intUpdate);
    }
    public static void entry(Client client) {
        GUI.client = client;
        launch("");
    }

    @Override
    public void setActive() {}

    @Override
    public void printConnectionMessage(Message message) {}

    public void start(Stage primaryStage) throws Exception {
        primaryStage.getIcons().add(new Image(this.getClass().getResource("/images/icon.png").toString()));
        primaryStage.setTitle("My Shelfie");
        Pane root = new Pane();
        this.main = new Scene(root, 1280, 692);
        primaryStage.setScene(this.main);

        ArrayList<FXMLLoader> loaders = new ArrayList<>();
        loaders.add(new FXMLLoader(getClass().getResource("/fxml/board.fxml")));
        loaders.add(new FXMLLoader(getClass().getResource("/fxml/four-players-screen.fxml")));
        loaders.add(new FXMLLoader(getClass().getResource("/fxml/init-error.fxml")));
        loaders.add(new FXMLLoader(getClass().getResource("/fxml/init-nickname.fxml")));
        loaders.add(new FXMLLoader(getClass().getResource("/fxml/init-screen.fxml")));
        loaders.add(new FXMLLoader(getClass().getResource("/fxml/lobby-set.fxml")));
        loaders.add(new FXMLLoader(getClass().getResource("/fxml/lobby-waiting.fxml")));
        loaders.add(new FXMLLoader(getClass().getResource("/fxml/menu-screen.fxml")));
        loaders.add(new FXMLLoader(getClass().getResource("/fxml/three-players-screen.fxml")));
        loaders.add(new FXMLLoader(getClass().getResource("/fxml/two-players-screen.fxml")));
        loaders.add(new FXMLLoader(getClass().getResource("/fxml/victory-screen.fxml")));

        for (FXMLLoader loader : loaders) {
            try {
                Pane pane = loader.load();
                GenericInterface controller = loader.getController();
                controller.setGUI(this);
                this.loaderMap.put(controller.getName(), loader);
            }
            catch (Exception e){
                System.out.println(e.toString());
            }
        }
        this.activate(InitController.name);
        primaryStage.setResizable(true);
        primaryStage.show();
        primaryStage.setFullScreen(false);
    }

    private void intUpdate(){
        switch (client.getState()){
            case SETTINGNICKNAME ->{
                    activate(InitErrorController.name);
            break;
            }
            case SETTINGPLAYERSNUMBER ->{
                    activate(LobbySetController.name);
            break;
            }
            case WAITINGINLOBBY ->{
                    activate(LobbyWaitController.name);
            break;
            }
            case WAITINGFORMYTURN -> {
                switch (client.getGame().getPlayers().size()) {
                    case 2 -> {
                        activate(TwoPlayersScreenController.name);
                        break;
                    }
                    case 3 -> {
                        activate(ThreePlayersScreenController.name);
                        break;
                    }
                    case 4 -> {
                        activate(FourPlayersScreenController.name);
                        break;
                    }
                }
                break;
            }

            case MYTURN ->{
                    activate(BoardController.name);
            break;
            }
            case GAMEENDED -> {
                activate(VictoryScreenController.name);
                break;
            }
        }
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public void activate(String name) {
        FXMLLoader loader = this.loaderMap.get(name);
        this.current = loader.getController();
        current.update();
        main.setRoot(loader.getRoot());
    }
}
