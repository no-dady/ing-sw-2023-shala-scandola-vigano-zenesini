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
import java.util.Objects;

/**
 * <p>GUI class.</p>
 *
 * @author daniel
 * @version $Id: $Id
 */
public class GUI extends Application implements UI {
    HashMap<String, FXMLLoader> loaderMap = new HashMap<>();
    HashMap<String, String> sizeMap = new HashMap<>();
    private Scene main;
    private Stage stage;
    private GenericInterface current;
    private String nickname = "";

    /**
     * <p>Getter for the field <code>nickname</code>.</p>
     *
     * @return a {@link java.lang.String} object
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * <p>Getter for the field <code>client</code>.</p>
     *
     * @return a {@link it.polimi.client.Client} object
     */
    public Client getClient() {
        return client;
    }

    private static Client client;

    /**
     * <p>Constructor for GUI.</p>
     */
    public GUI() {
        super();
    }

    /** {@inheritDoc} */
    @Override
    public void update(){
        Platform.runLater(this::intUpdate);
    }
    /**
     * <p>entry.</p>
     *
     * @param client a {@link it.polimi.client.Client} object
     */
    public static void entry(Client client) {
        GUI.client = client;
        launch("");
    }

    /** {@inheritDoc} */
    @Override
    public void printServerMessage(String message) {}

    /** {@inheritDoc} */
    public void start(Stage primaryStage) {
        client.setUi(this);
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(this.getClass().getResource("/images/icon.png")).toString()));
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
            catch (Exception ignored){
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
            }
            case SETUPFIRST ->{
                    activate(LobbySetNicknameController.name);
            }
            case WAITINGINLOBBY ->{
                    activate(LobbyWaitController.name);
            }
            case WAITINGFORMYTURN, MYTURN -> {
                activate(BoardController.name);
            }
            case GAMEENDED -> {
                activate(VictoryScreenController.name);
            }
        }
    }


    /**
     * <p>Setter for the field <code>nickname</code>.</p>
     *
     * @param nickname a {@link java.lang.String} object
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    /**
     * <p>activate.</p>
     *
     * @param name a {@link java.lang.String} object
     */
    public void activate(String name) {
        FXMLLoader loader = this.loaderMap.get(name);
        String[] Dimensions = this.sizeMap.get(name).split("x");
        stage.setWidth(Double.parseDouble(Dimensions[0]));
        stage.setHeight(Double.parseDouble(Dimensions[1]));
        this.current = loader.getController();
        current.update();
        main.setRoot(loader.getRoot());

    }

    /**
     * <p>getGame.</p>
     *
     * @return a {@link it.polimi.server.model.Game} object
     */
    public Game getGame(){
        return client.getGame();
    }
    /**
     * <p>stop.</p>
     */
    public void stop() {
        Platform.exit();
        System.exit(0);
    }
}
