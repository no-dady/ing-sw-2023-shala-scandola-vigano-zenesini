package client.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class BoardController implements GenericInterface, Initializable {

    public Button menu_button;
    public StackPane mainContainer;
    public GridPane mainGrid;

    public Pane Board;
    public GridPane cgcAndButtons;
    public GridPane bookshelf;
    public Pane bookshelf_image;
    public GridPane menuPlayerButtons;
    public Button end_turn;
    public Button confirm;
    public Button show_ply_button;
    public Pane cgc_1;
    public Pane cgc_2;
    public Pane goal_card;
    public Pane container1;
    public Pane container2;
    public Pane container3;
    public Pane container4;
    public GridPane bookshelfButtons;
    public Button arrow1;
    public Pane container5;
    public Button arrow2;
    public Pane container6;
    public Pane container7;
    public Pane container8;
    public Pane container9;
    public Button arrow3;
    public Button arrow4;
    public Button arrow5;
    public Pane label;
    public GridPane objective;
    public GridPane buttons;
    public GridPane topButtons;
    public GridPane isPlayerXTurn;
    public GridPane bookshelf_grid;
    public GridPane board_grid;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void onPlayerButtonPress(ActionEvent event) {
    }

    public void onMenuButtonPress(ActionEvent event) {
    }

    public void onEndTurnButtonPress(ActionEvent event) {
    }

    public void onConfirmButtonPress(ActionEvent event) {
    }

    public void onArrowOnePress(ActionEvent event) {
    }

    public void onArrowTwoPress(ActionEvent event) {
    }

    public void onArrowThreePress(ActionEvent event) {
    }

    public void onArrowFourPress(ActionEvent event) {
    }

    public void onArrowFivePress(ActionEvent event) {
    }
}
