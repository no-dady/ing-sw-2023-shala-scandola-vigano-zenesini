package client.gui;

import client.Client;
import client.UI;
import client.gui.controller.GenericInterface;
import client.gui.controller.InitController;
import javafx.application.Application;
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

    private Client client;

    public GUI(Client client) {
        this.client = client;
    }

    @Override
    public void update() {
        //Platform.runLater(this::intUpdate);
    }

    @Override
    public void setActive() {}

    @Override
    public void printConnectionMessage(Message message) {}

    public static void main(String[] args)
    {
        launch(args);
    }

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
            Pane pane = loader.load();
            GenericInterface controller = loader.getController();
            controller.setGUI(this);
            this.loaderMap.put(controller.getName(), loader);
        }
        this.activate(InitController.name);
        primaryStage.setResizable(true);
        primaryStage.show();
        primaryStage.setFullScreen(false);
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
    public void activate(String name) {
        FXMLLoader loader = this.loaderMap.get(name);
        this.current = loader.getController();
        current.update();
        main.setRoot(loader.getRoot());
    }
}
