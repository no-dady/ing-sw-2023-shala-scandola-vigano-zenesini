package client.gui.controller;

import client.gui.GUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import server.controller.BoardConfig;
import server.model.Bookshelf;
import server.model.Player;
import setup.ConfigsFromJson;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class BoardController implements GenericInterface, Initializable {

    private GUI gui;

    public static final String name ="board";
    public Label L2; //current player
    public Button menu_button;
    public Button show_ply_button;
    public Button end_turn;
    public Button confirm;

    public Button arrow1;
    public Button arrow2;
    public Button arrow3;
    public Button arrow4;
    public Button arrow5;
    @FXML
    public Pane goal_card;
    @FXML
    public Pane cgc_1;
    @FXML
    public Pane cgc_2;
    @FXML
    public Pane t_2_4, t_2_5, t_3_4, t_3_5, t_3_6, t_4_3, t_4_4, t_4_5, t_4_6,
            t_4_7, t_4_8, t_5_2, t_5_3, t_5_4, t_5_5, t_5_6, t_5_7, t_5_8, t_6_2,
            t_6_3, t_6_4, t_6_5, t_6_6, t_6_7, t_7_4, t_7_5, t_7_6, t_8_5, t_8_6; //two players cells
    @FXML
    public Pane t_1_4, t_3_3, t_3_7, t_4_9, t_6_1, t_7_3, t_7_7, t_9_6; //three players exclusive cells
    @FXML
    public Pane t_1_5, t_2_6, t_4_2, t_5_1, t_5_9, t_6_8, t_8_4, t_9_5; //four players exclusive cells
    @FXML
    public Pane b_1_1, b_1_2, b_1_3, b_1_4, b_1_5, b_1_6,
            b_2_1, b_2_2, b_2_3, b_2_4, b_2_5, b_2_6,
            b_3_1, b_3_2, b_3_3, b_3_4, b_3_5, b_3_6,
            b_4_1, b_4_2, b_4_3, b_4_4, b_4_5, b_4_6,
            b_5_1, b_5_2, b_5_3, b_5_4, b_5_5, b_5_6; //index of tiles on bookshelf

    public BoardController(GUI gui) {
        this.gui = gui;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        int i,j;
        int[][] m;
        int num = gui.getClient().getGame().getNumPlayers();
        Bookshelf bookshelf = null;

        List<Pane> twoPlayers = List.of(t_2_4, t_2_5, t_3_4, t_3_5, t_3_6, t_4_3, t_4_4, t_4_5, t_4_6,
                t_4_7, t_4_8, t_5_2, t_5_3, t_5_4, t_5_5, t_5_6, t_5_7, t_5_8, t_6_2,
                t_6_3, t_6_4, t_6_5, t_6_6, t_6_7, t_7_4, t_7_5, t_7_6, t_8_5, t_8_6);


        List<Pane> threePlayers = new java.util.ArrayList<>(List.of(t_1_4, t_3_3, t_3_7, t_4_9, t_6_1, t_7_3, t_7_7, t_9_6));

        List<Pane> fourPlayers = new java.util.ArrayList<>(List.of(t_1_5, t_2_6, t_4_2, t_5_1, t_5_9, t_6_8, t_8_4, t_9_5));

        List<Pane> bookshelfTiles = List.of(b_1_1, b_1_2, b_1_3, b_1_4, b_1_5, b_1_6,
                b_2_1, b_2_2, b_2_3, b_2_4, b_2_5, b_2_6,
                b_3_1, b_3_2, b_3_3, b_3_4, b_3_5, b_3_6,
                b_4_1, b_4_2, b_4_3, b_4_4, b_4_5, b_4_6,
                b_5_1, b_5_2, b_5_3, b_5_4, b_5_5, b_5_6);

        try {
             m = ConfigsFromJson.getBoardConfig("src/main/resources/json/board_config.json").pattern;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        switch (num){ //board initialization

            case(2):

                for (i = 0; i < gui.getClient().getGame().getBoard().getSlots().length ; i++) {
                    for (j = 0; j < gui.getClient().getGame().getBoard().getSlots()[0].length; j++) {
                        if (m[i][j] != 0 && m[i][j] <= num) {
                            String imageUrl = getClass().getResource("/fxml/images/itemTiles/" + gui.getClient().getGame().getBoard().getTile(i, j).getImage()).toExternalForm();
                            twoPlayers.get(i + j).setStyle("-fx-background-image: url('" + imageUrl + "');");
                        }
                    }
                }

                        break;

            case(3):

                threePlayers.addAll(twoPlayers);

                for (i = 0; i < gui.getClient().getGame().getBoard().getSlots().length ; i++) {
                    for (j = 0; j < gui.getClient().getGame().getBoard().getSlots()[0].length; j++) {
                        if (m[i][j] != 0 && m[i][j] <= num) {
                            String imageUrl = getClass().getResource("/fxml/images/itemTiles/" + gui.getClient().getGame().getBoard().getTile(i, j).getImage()).toExternalForm();
                            threePlayers.get(i + j).setStyle("-fx-background-image: url('" + imageUrl + "');");
                        }
                    }
                }

                        break;

            case(4):

                fourPlayers.addAll(twoPlayers);
                fourPlayers.addAll(threePlayers);

                for (i = 0; i < gui.getClient().getGame().getBoard().getSlots().length ; i++){
                    for (j= 0; j < gui.getClient().getGame().getBoard().getSlots()[0].length ; j++) {
                        if (m[i][j] != 0 && m[i][j] <= num) {
                            String imageUrl = getClass().getResource("/fxml/images/itemTiles/" + gui.getClient().getGame().getBoard().getTile(i, j).getImage()).toExternalForm();
                            fourPlayers.get(i + j).setStyle("-fx-background-image: url('" + imageUrl + "');");
                        }
                    }
                }
                        break;
        }

        for(Player player : gui.getClient().getGame().getPlayers())
        {
            if(player.getUserName().equals(gui.getNickname()))
            {
                bookshelf=player.getBookshelf();
            }
        }

        for(i = 0; i < bookshelf.getRows(); i++) //bookshelf initialization
        {
            for(j = 0; j < bookshelf.getCols(); j++)
            {
                assert bookshelf != null;
                String imageUrl = getClass().getResource("/fxml/images/itemTiles/" + bookshelf.getSlots()[i][j].getImage()).toExternalForm();
                bookshelfTiles.get(i+j).setStyle("-fx-background-image: url('" + imageUrl + "');");
            }

        }

        String goalCard = getClass().getResource("/fxml/images/personal_goal_card/"+ gui.getClient().getGame().getPlayers().get(0).getPersonalGoalCard() +".png").toExternalForm();
        goal_card.setStyle("-fx-background-image: url('" + goalCard + "');");

        String cgc1 = getClass().getResource("/fxml/images/common_goal_cards/"+ gui.getClient().getGame().getBoard().getCommonGoalCards().get(0) +".jpg").toExternalForm();
        cgc_1.setStyle("-fx-background-image: url('" + cgc1 + "');");

        String cgc2 = getClass().getResource("/fxml/images/common_goal_cards/"+ gui.getClient().getGame().getBoard().getCommonGoalCards().get(1) +".jpg").toExternalForm();
        cgc_2.setStyle("-fx-background-image: url('" + cgc2 + "');");




    }

    public void setL2(String str)
    {
        L2.setText(str);
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

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void update() {

    }

    @Override
    public void setGUI(GUI gui) {

    }

}
