package client.gui.controller;

import client.gui.GUI;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import server.model.Bookshelf;
import server.model.Coordinates;
import server.model.Player;
import setup.ConfigsFromJson;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class BoardController implements GenericInterface, Initializable {
    private GUI gui;

    public static final String name ="board";
    @FXML //utils label
    public Label L1, L2;
    @FXML //selection buttons
    public Button arrow1, arrow2, arrow3, arrow4, arrow5;
    @FXML //utils buttons
    public Button menu_button, show_ply_button, end_turn, confirm;
    @FXML //utils pane
    public Pane playerTurn, first_tile, second_tile, third_tile, goal_card, cgc_1, cgc_2;
    @FXML //two players cells
    public Pane t_2_4, t_2_5, t_3_4, t_3_5, t_3_6, t_4_3, t_4_4, t_4_5, t_4_6,
                t_4_7, t_4_8, t_5_2, t_5_3, t_5_4, t_5_5, t_5_6, t_5_7, t_5_8, t_6_2,
                t_6_3, t_6_4, t_6_5, t_6_6, t_6_7, t_7_4, t_7_5, t_7_6, t_8_5, t_8_6;
    @FXML //three players exclusive cells
    public Pane t_1_4, t_3_3, t_3_7, t_4_9, t_6_1, t_7_3, t_7_7, t_9_6;
    @FXML //four players exclusive cells
    public Pane t_1_5, t_2_6, t_4_2, t_5_1, t_5_9, t_6_8, t_8_4, t_9_5;
    @FXML //index of tiles on bookshelf
    public Pane b_1_1, b_1_2, b_1_3, b_1_4, b_1_5, b_1_6,
                b_2_1, b_2_2, b_2_3, b_2_4, b_2_5, b_2_6,
                b_3_1, b_3_2, b_3_3, b_3_4, b_3_5, b_3_6,
                b_4_1, b_4_2, b_4_3, b_4_4, b_4_5, b_4_6,
                b_5_1, b_5_2, b_5_3, b_5_4, b_5_5, b_5_6;

    public boolean action = true;
    public int selectedColumn = 0, count = 0;
    public List<Pane> selectedIds = new ArrayList<Pane>();
    public List<Pane> changedBookshelfTiles = new ArrayList<Pane>();
    public HashMap <Character, Character> selectedTiles= new HashMap<>();

    public DropShadow dropShadow = new DropShadow(BlurType.GAUSSIAN, Color.WHITE, 15, 0.5, 0, 1);




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
    @Override
    public void setGUI(GUI gui) {
        this.gui = gui;
    }
    @Override
    public String getName() {
        return name;
    }

    public void onArrowOnePress(ActionEvent event) {
        if(selectedColumn==0){
            selectedColumn = 1;
            update();
        }
    }
    public void onArrowTwoPress(ActionEvent event) {
        if(selectedColumn==0){
            selectedColumn = 2;
            update();
        }
    }
    public void onArrowThreePress(ActionEvent event) {
        if(selectedColumn==0){
            selectedColumn = 3;
            update();
        }
    }
    public void onArrowFourPress(ActionEvent event) {
        if(selectedColumn==0){
            selectedColumn = 4;
            update();
        }
    }
    public void onArrowFivePress(ActionEvent event) {
        if(selectedColumn==0){
            selectedColumn = 5;
            update();
        }
    }

    public void onMenuButtonPress(ActionEvent event) {
        gui.activate(MenuScreenController.name);
        update();
    }
    public void onPlayerButtonPress(ActionEvent event) {
        switch (gui.getClient().getGame().getNumPlayers()) {
            case(2):
                gui.activate(TwoPlayersScreenController.name);
                    break;
            case(3):
                gui.activate(ThreePlayersScreenController.name);
                    break;
            case(4):
                gui.activate(FourPlayersScreenController.name);
                    break;
        }
    }
    public void onConfirmButtonPress(ActionEvent event) { //when you confirm your actions
        for (Map.Entry<Character, Character> entry : selectedTiles.entrySet()) {
            System.out.println("x:" + entry.getKey() + ", y:" + entry.getValue());
        }
        first_tile.setBackground(null);
        second_tile.setBackground(null);
        third_tile.setBackground(null);
        selectedColumn=0;
        action = false;
        end_turn.setText("End_turn");
        update();
    }

    public void onEndTurnButtonPress(ActionEvent event) { //if you press the end button
        if(end_turn.getText().equals("End turn")){ //when you end your action
            for (Map.Entry<Character, Character> entry : selectedTiles.entrySet()) {
                System.out.println("x:" + entry.getKey() + ", y:" + entry.getValue());
            }
            System.out.println(selectedColumn);
        }
        if(end_turn.getText().equals("Cancel")){ //when you cancel your action after selecting some tiles
            first_tile.setBackground(null);
            second_tile.setBackground(null);
            third_tile.setBackground(null);
            end_turn.setText("End_turn");
            for (Pane y: changedBookshelfTiles) {
                y.setBackground(null);
            }
            for (Pane x: selectedIds) {
                x.setOpacity(1);
                x.setDisable(false);
            }
            changedBookshelfTiles.clear();
            selectedTiles.clear();
            selectedIds.clear();
            selectedColumn = 0;
            action = true;
            count = 0;
            update();
        }
    }

    public int getEmptyColumns(HashMap<Pane, Coordinates> bookshelfMap, int x){ //this method return the number of free cells in a column
        int num = 0;
        for (int i = 0; i < Bookshelf.getRows(); i++){
            for(Pane p: bookshelfMap.keySet()) {
                if(bookshelfMap.get(p).x() == (i+1) && bookshelfMap.get(p).y() == x){
                    if(p.getBackground()==null){
                        num++;
                    }
                }
            }
        }
        return num;
    }

    private void onTilePressed(Pane p) { //this method add tiles to the selected ones and show them on the selection panel
        if(action) {
            List<Pane> frames = List.of(first_tile, second_tile, third_tile);
            if (count < 3) {
                end_turn.setText("Cancel");
                p.setOpacity(0);
                p.setDisable(true);
                frames.get(count).setBackground(p.getBackground());
                selectedTiles.put(p.getId().charAt(2), p.getId().charAt(4));
                selectedIds.add(p);

                count++;
                update();
            }
        }
    }


    @Override
    public void update() {
        int i,j,n=0;
        int[][] m;
        int num = gui.getGame().getNumPlayers();

        Bookshelf bookshelf = null;
        HashMap <Pane, Coordinates> paneMap= new HashMap<>();
        HashMap <Pane, Coordinates> bookshelfMap = new HashMap<>();
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

        bookshelfMap.put( b_1_1, new Coordinates(1,1));
        bookshelfMap.put( b_1_2, new Coordinates(1,2));
        bookshelfMap.put( b_1_3, new Coordinates(1,3));
        bookshelfMap.put( b_1_4, new Coordinates(1,4));
        bookshelfMap.put( b_1_5, new Coordinates(1,5));
        bookshelfMap.put( b_1_6, new Coordinates(1,6));
        bookshelfMap.put( b_2_1, new Coordinates(2,1));
        bookshelfMap.put( b_2_2, new Coordinates(2,2));
        bookshelfMap.put( b_2_3, new Coordinates(2,3));
        bookshelfMap.put( b_2_4, new Coordinates(2,4));
        bookshelfMap.put( b_2_5, new Coordinates(2,5));
        bookshelfMap.put( b_2_6, new Coordinates(2,6));
        bookshelfMap.put( b_3_1, new Coordinates(3,1));
        bookshelfMap.put( b_3_2, new Coordinates(3,2));
        bookshelfMap.put( b_3_3, new Coordinates(3,3));
        bookshelfMap.put( b_3_4, new Coordinates(3,4));
        bookshelfMap.put( b_3_5, new Coordinates(3,5));
        bookshelfMap.put( b_3_6, new Coordinates(3,6));
        bookshelfMap.put( b_4_1, new Coordinates(4,1));
        bookshelfMap.put( b_4_2, new Coordinates(4,2));
        bookshelfMap.put( b_4_3, new Coordinates(4,3));
        bookshelfMap.put( b_4_4, new Coordinates(4,4));
        bookshelfMap.put( b_4_5, new Coordinates(4,5));
        bookshelfMap.put( b_4_6, new Coordinates(4,6));
        bookshelfMap.put( b_5_1, new Coordinates(5,1));
        bookshelfMap.put( b_5_2, new Coordinates(5,2));
        bookshelfMap.put( b_5_3, new Coordinates(5,3));
        bookshelfMap.put( b_5_4, new Coordinates(5,4));
        bookshelfMap.put( b_5_5, new Coordinates(5,5));
        bookshelfMap.put( b_5_6, new Coordinates(5,6));

        /*List<Pane> bookshelfTiles = List.of(b_1_1, b_1_2, b_1_3, b_1_4, b_1_5, b_1_6,
                b_2_1, b_2_2, b_2_3, b_2_4, b_2_5, b_2_6,
                b_3_1, b_3_2, b_3_3, b_3_4, b_3_5, b_3_6,
                b_4_1, b_4_2, b_4_3, b_4_4, b_4_5, b_4_6,
                b_5_1, b_5_2, b_5_3, b_5_4, b_5_5, b_5_6);*/

        List<Button> arrows = List.of(arrow1,arrow2,arrow3,arrow4,arrow5);

        try {
            m = ConfigsFromJson.getBoardConfig("src/main/resources/json/board_config.json").pattern;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (Pane p : paneMap.keySet()) { //initializing board + tiles
            int x = paneMap.get(p).x() - 1;
            int y = paneMap.get(p).y() - 1;
            if (m[x][y] != 0 && m[x][y] <= num){
                String imageUrl = Objects.requireNonNull(getClass().getResource("/images/itemTiles/" + gui.getGame().getBoard().getTile(x,y).getImage())).toExternalForm();
                p.setStyle("-fx-background-image: url('" + imageUrl + "');");
                if(gui.getGame().getPlayers().get(0).getUserId() == gui.getGame().getCurrPlayerId() && gui.getNickname().equals(gui.getGame().getPlayers().get(0).getUserName())) {
                    if (gui.getGame().getBoard().getTile(x, y).isPickable()) {

                        p.setOnMouseEntered(event -> {
                            p.setEffect(dropShadow);
                        });

                        p.setOnMouseExited(event -> {
                            p.setEffect(null);
                        });
                        if(action){
                            p.setOnMouseClicked(event -> onTilePressed(p));
                        }
                    }
                }
            }
        }

        for(Player player : gui.getClient().getGame().getPlayers()) //initializing some labels
        {
            if(player.getUserName().equals(gui.getNickname()) /*&& player.getUserId() == gui.getGame().getCurrPlayerId()*/)
            {
                bookshelf=player.getBookshelf();
                L1.setText(gui.getNickname() + "'s goal: ");
            }
            if(player.getUserId() == gui.getClient().getGame().getCurrPlayerId())
            {
                L2.setText("It's "+player.getUserName()+"'s turn");
            }
        }

        for (Pane p : bookshelfMap.keySet()) {
            int x = bookshelfMap.get(p).x() - 1;
            int y = bookshelfMap.get(p).y() - 1;
            if (bookshelf.getSlots()[y][x] != null) {
                String imageUrl = Objects.requireNonNull(getClass().getResource("/images/itemTiles/" + bookshelf.getSlots()[y][x].getImage())).toExternalForm();
                p.setStyle("-fx-background-image: url('" + imageUrl + "');");
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

        for(i = 0; i < Bookshelf.getCols(); i++){ //initializing arrow buttons - what columns you can select
            if(getEmptyColumns(bookshelfMap,i+1)>=selectedTiles.size()){
                arrows.get(i).setDisable(false);
                int finalI = i;
                arrows.get(i).setOnMouseEntered(event -> {
                    arrows.get(finalI).setEffect(dropShadow);
                });

                int finalI1 = i;
                arrows.get(i).setOnMouseExited(event -> {
                    arrows.get(finalI1).setEffect(null);
                });
            }
            else {
                arrows.get(i).setDisable(true);
            }
        }

        if(action) {
            //preview on the bookshelf
            if (selectedColumn > 0 && selectedTiles.size() != 0) {
                int s = Bookshelf.getRows() - getEmptyColumns(bookshelfMap, selectedColumn);
                System.out.println(s);
                for(int z = 0; z < Bookshelf.getRows(); z++){
                    for (Pane x : bookshelfMap.keySet()) {
                        if (bookshelfMap.get(x).x() == selectedColumn && bookshelfMap.get(x).y() == s && z < selectedIds.size()) {
                            System.out.println(s);
                            x.setBackground(selectedIds.get(z).getBackground());
                            changedBookshelfTiles.add(x);
                            s++;
                        }
                    }
                }
            }
        }
    }
}
