package it.polimi.client.gui;

import it.polimi.client.Client;
import it.polimi.client.UI;
import it.polimi.client.gui.controller.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import it.polimi.server.model.Game;

import java.util.ArrayList;
import java.util.HashMap;

public class GUI extends Application implements UI {
    HashMap<String, FXMLLoader> loaderMap = new HashMap<>();
    HashMap<String, String> sizeMap = new HashMap<>();
    private Scene main;
    private Stage stage;
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
    public void update(){
        Platform.runLater(this::intUpdate);
    }
    public static void entry(Client client) {
        GUI.client = client;
        launch("");
    }

    @Override
    public void printServerMessage(String message) {}

    public void start(Stage primaryStage) throws Exception {
        client.setUi(this);
        primaryStage.getIcons().add(new Image(this.getClass().getResource("/images/icon.png").toString()));
        primaryStage.setTitle("My Shelfie");
        Pane root = new Pane();
        this.main = new Scene(root);
        primaryStage.setScene(this.main);
        this.stage = primaryStage;

        ArrayList<FXMLLoader> loaders = new ArrayList<>();
        loaders.add(new FXMLLoader(getClass().getResource("/fxml/board.fxml")));
        loaders.add(new FXMLLoader(getClass().getResource("/fxml/init-error.fxml")));
        loaders.add(new FXMLLoader(getClass().getResource("/fxml/init-nickname.fxml")));
        loaders.add(new FXMLLoader(getClass().getResource("/fxml/init-screen.fxml")));
        loaders.add(new FXMLLoader(getClass().getResource("/fxml/lobby-set-nickname.fxml")));
        loaders.add(new FXMLLoader(getClass().getResource("/fxml/lobby-set.fxml")));
        loaders.add(new FXMLLoader(getClass().getResource("/fxml/lobby-waiting.fxml")));
        loaders.add(new FXMLLoader(getClass().getResource("/fxml/menu-screen.fxml")));
        loaders.add(new FXMLLoader(getClass().getResource("/fxml/victory-screen.fxml")));

        for (FXMLLoader loader : loaders) {
            try {
                Pane pane = loader.load();
                GenericInterface controller = loader.getController();
                controller.setGUI(this);
                this.loaderMap.put(controller.getName(), loader);
                this.sizeMap.put(controller.getName(),controller.getDimensions());
            }
            catch (Exception e){
                System.out.println(e);
            }
        }
        this.activate(InitController.name);
        primaryStage.setResizable(true);
        primaryStage.show();
        primaryStage.setFullScreen(false);
    }

    private void intUpdate(){
        switch (client.getState()){
            case SETUP ->{
                    activate(InitErrorController.name);
            break;
            }
            case SETUPFIRST ->{
                    activate(LobbySetNicknameController.name);
            break;
            }
            case WAITINGINLOBBY ->{
                    activate(LobbyWaitController.name);
            break;
            }
            case WAITINGFORMYTURN -> {
                activate(BoardController.name);
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
            /*case PLAYERSQUIT -> {

            }*/
        }
    }


    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public void activate(String name) {
        FXMLLoader loader = this.loaderMap.get(name);
        String[] Dimensions = this.sizeMap.get(name).split("x");
        stage.setWidth(Double.parseDouble(Dimensions[0]));
        stage.setHeight(Double.parseDouble(Dimensions[1]));
        this.current = loader.getController();
        current.update();
        main.setRoot(loader.getRoot());

    }

    public Game getGame(){
        return client.getGame();
    }
    public void stop() {
        Platform.exit();
        System.exit(0);
    }
}
