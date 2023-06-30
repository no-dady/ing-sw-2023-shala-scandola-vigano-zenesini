package it.polimi.client.gui.controller;

import it.polimi.client.gui.GUI;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * <p>MenuScreenController class.</p>
 *
 * @author daniel
 * @version $Id: $Id
 */
public class MenuScreenController implements GenericInterface, Initializable {
    private GUI gui;
    public final String dim = "500x600";

    public Button B1;
    public Button B2;
    public StackPane mainContainer;
    public GridPane mainGrid;
    public Pane menuImage;
    /** Constant <code>name="menu-screen"</code> */
    public static final String name ="menu-screen";

    /** {@inheritDoc} */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    /** {@inheritDoc} */
    @Override
    public String getDimensions() {
        return  this.dim;
    }

    /**
     * <p>onQuitButtonPress.</p>
     *
     * @param event a {@link javafx.event.ActionEvent} object
     */
    public void onQuitButtonPress(ActionEvent event) {
        System.exit(0);
    }

    /**
     * <p>onBackButtonPress.</p>
     *
     * @param event a {@link javafx.event.ActionEvent} object
     */
    public void onBackButtonPress(ActionEvent event) {
        gui.activate(BoardController.name);
    }
    /** {@inheritDoc} */
    @Override
    public String getName() {
        return name;
    }

    /** {@inheritDoc} */
    @Override
    public void update() {

    }
    /** {@inheritDoc} */
    @Override
    public void setStage(Stage stage) {
    }

    /** {@inheritDoc} */
    @Override
    public void setGUI(GUI gui) {
        this.gui = gui;
    }
}
