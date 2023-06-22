package client.gui.controller;

import client.gui.GUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import server.model.Bookshelf;
import server.model.Coordinates;
import server.model.Player;
import setup.ConfigsFromJson;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class BoardController implements GenericInterface, Initializable {

    private GUI gui;

    public static final String name ="board";
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

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void update() {
        int i,j;
        int[][] m;
        int num = gui.getGame().getNumPlayers();
        Bookshelf bookshelf = null;
        HashMap <Pane, Coordinates> paneMap= new HashMap<>();
        paneMap.put( t_1_4,new Coordinates(1,4));
        paneMap.put( t_1_5,new Coordinates(1,5));
        paneMap.put( t_2_4,new Coordinates(2,4));
        paneMap.put( t_2_5,new Coordinates(2,5));
paneMap.put( t_2_6,new Coordinates(2,6));
paneMap.put( t_3_3,new Coordinates(3,3));
paneMap.put( t_3_4,new Coordinates(3,4));
paneMap.put( t_3_5,new Coordinates(3,5));
paneMap.put( t_3_6,new Coordinates(3,6));
paneMap.put( t_3_7,new Coordinates(3,7));
paneMap.put( t_4_2,new Coordinates(4,2));
paneMap.put( t_4_3,new Coordinates(4,3));
paneMap.put( t_4_4,new Coordinates(4,4));
paneMap.put( t_4_5,new Coordinates(4,5));
paneMap.put( t_4_6,new Coordinates(4,6));
paneMap.put( t_4_7,new Coordinates(4,7));
paneMap.put( t_4_8,new Coordinates(4,8));
paneMap.put( t_4_9,new Coordinates(4,9));
paneMap.put( t_5_1,new Coordinates(5,1));
paneMap.put( t_5_2,new Coordinates(5,2));
paneMap.put( t_5_3,new Coordinates(5,3));
paneMap.put( t_5_4,new Coordinates(5,4));
paneMap.put( t_5_5,new Coordinates(5,5));
paneMap.put( t_5_6,new Coordinates(5,6));
paneMap.put( t_5_7,new Coordinates(5,7));
paneMap.put( t_5_8,new Coordinates(5,8));
paneMap.put( t_5_9,new Coordinates(5,9));
paneMap.put( t_6_1,new Coordinates(6,1));
paneMap.put( t_6_2,new Coordinates(6,2));
paneMap.put( t_6_3,new Coordinates(6,3));
paneMap.put( t_6_4,new Coordinates(6,4));
paneMap.put( t_6_5,new Coordinates(6,5));
paneMap.put( t_6_6,new Coordinates(6,6));
paneMap.put( t_6_7,new Coordinates(6,7));
paneMap.put( t_6_8,new Coordinates(6,8));
paneMap.put( t_7_3,new Coordinates(7,3));
paneMap.put( t_7_4,new Coordinates(7,4));
paneMap.put( t_7_5,new Coordinates(7,5));
paneMap.put( t_7_6,new Coordinates(7,6));
paneMap.put( t_7_7,new Coordinates(7,7));
paneMap.put( t_8_4,new Coordinates(8,4));
paneMap.put( t_8_5,new Coordinates(8,5));
paneMap.put( t_8_6,new Coordinates(8,6));
paneMap.put( t_9_5,new Coordinates(9,5));
paneMap.put( t_9_6,new Coordinates(9,6));


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

        for (Pane p : paneMap.keySet()) {
            int x = paneMap.get(p).x() - 1;
            int y = paneMap.get(p).y() - 1;
            if (m[x][y] != 0 && m[x][y] <= num){
                String imageUrl = Objects.requireNonNull(getClass().getResource("/images/itemTiles/" + gui.getGame().getBoard().getTile(x,y).getImage())).toExternalForm();
                p.setStyle("-fx-background-image: url('" + imageUrl + "');");

            }

        }

        for(Player player : gui.getClient().getGame().getPlayers())
        {
            if(player.getUserName().equals(gui.getNickname()))
            {
                bookshelf=player.getBookshelf();
            }
        }

        for(i = 0; i < Bookshelf.getRows(); i++) //bookshelf initialization
        {
            for(j = 0; j < Bookshelf.getCols(); j++)
            {
                if (bookshelf.getSlots()[i][j] != null) {
                    String imageUrl = Objects.requireNonNull(getClass().getResource("/images/itemTiles/" + bookshelf.getSlots()[i][j].getImage())).toExternalForm();
                    bookshelfTiles.get(i + j).setStyle("-fx-background-image: url('" + imageUrl + "');");
                }
            }

        }
        for (Player player : gui.getGame().getPlayers()){
            if (player.getUserName().equals(gui.getNickname())){
                String goalCard = Objects.requireNonNull(getClass().getResource("/images/personal_goal_card/" + player.getPersonalGoalCard().getFileName() + ".png")).toExternalForm();
                goal_card.setStyle("-fx-background-image: url('" + goalCard + "');");
            }
        }

        String cgc1 = Objects.requireNonNull(getClass().getResource("/images/common_goal_cards/" + gui.getGame().getBoard().getCommonGoalCards().get(0).getName() + ".jpg")).toExternalForm();
        cgc_1.setStyle("-fx-background-image: url('" + cgc1 + "');");

        String cgc2 = Objects.requireNonNull(getClass().getResource("/images/common_goal_cards/" + gui.getGame().getBoard().getCommonGoalCards().get(1).getName()+ ".jpg")).toExternalForm();
        cgc_2.setStyle("-fx-background-image: url('" + cgc2 + "');");

    }

    @Override
    public void setGUI(GUI gui) {
        this.gui = gui;
    }

}
