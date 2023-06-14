package org.gamein;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class GuiTest extends Application {


    public static void main(String[] args) {
        launch();
    }


    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GuiTest.class.getResource("/resources/menu-screen.fxml")); //init-screen.fxml
        Scene scene = new Scene(fxmlLoader.load()); //1386,400 - 1000,800
        primaryStage.getIcons().add(new Image(this.getClass().getResource("/resources/images/icon.png").toString()));
        primaryStage.setTitle("My Shelfie");
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        //primaryStage.setMaximized(true);
        primaryStage.show();

        primaryStage.setFullScreen(true);
    }
}
