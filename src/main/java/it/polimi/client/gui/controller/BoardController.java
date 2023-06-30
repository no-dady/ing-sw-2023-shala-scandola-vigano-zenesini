package it.polimi.client.gui.controller;

import it.polimi.client.gui.GUI;
import it.polimi.client.gui.guiMoves.GUISelectColumn;
import it.polimi.client.gui.guiMoves.GUISelectTiles;
import it.polimi.client.network.State;
import it.polimi.moves.Move;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import it.polimi.moves.MoveSelectColumn;
import it.polimi.moves.MoveSelectTiles;
import it.polimi.server.model.Bookshelf;
import it.polimi.server.model.Coordinates;
import it.polimi.server.model.Player;
import it.polimi.setup.ConfigsFromJson;
import it.polimi.util.Parser;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.*;

public class BoardController implements GenericInterface, Initializable {
    public Label P1;
    public Label P2;
    private GUI gui;
    private GenericInterface popupController;
    public final String dim = "1920x1000";
    public static final String name ="board";
    @FXML //utils label
    public Label L1, L2;
    @FXML //selection buttons
    public Button arrow1, arrow2, arrow3, arrow4, arrow5;
    @FXML //utils buttons
    public Button show_ply_button, end_turn, confirm;
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


    public boolean action = false;
    public int selectedColumn = 0, count = 0;
    public MoveSelectTiles tileMove = null;
    public MoveSelectColumn columnMove = null;
    public List<Pane> selectedIds = new ArrayList<Pane>();
    private FXMLLoader loader;
    public List<Pane> changedBookshelfTiles = new ArrayList<Pane>();
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
            end_turn.setOpacity(1);
            end_turn.setDisable(false);
        }
    }
    public void onArrowTwoPress(ActionEvent event) {
        if(selectedColumn==0){
            selectedColumn = 2;
            update();
            end_turn.setOpacity(1);
            end_turn.setDisable(false);
        }
    }
    public void onArrowThreePress(ActionEvent event) {
        if(selectedColumn==0){
            selectedColumn = 3;
            update();
            end_turn.setOpacity(1);
            end_turn.setDisable(false);
        }
    }
    public void onArrowFourPress(ActionEvent event) {
        if(selectedColumn==0){
            selectedColumn = 4;
            update();
            end_turn.setOpacity(1);
            end_turn.setDisable(false);
        }
    }
    public void onArrowFivePress(ActionEvent event) {
        if(selectedColumn==0){
            selectedColumn = 5;
            update();
            end_turn.setOpacity(1);
            end_turn.setDisable(false);
        }
    }

    public void onPlayerButtonPress(ActionEvent event) throws IOException {
        loader = new FXMLLoader();
        Parent layout;
        // initializing the controller
        switch (gui.getGame().getNumPlayers()) {
            case 2 -> {
                popupController = new TwoPlayersScreenController();
                loader.setLocation(getClass().getResource("/fxml/two-players-screen.fxml"));
            }
            case 3 -> {
                popupController = new ThreePlayersScreenController();
                loader.setLocation(getClass().getResource("/fxml/three-players-screen.fxml"));
            }
            case 4 -> {
                popupController = new FourPlayersScreenController();
                loader.setLocation(getClass().getResource("/fxml/four-players-screen.fxml"));
            }
        }
        loader.setController(popupController);
        popupController.setGUI(gui);
        layout = loader.load();
        Scene scene = new Scene(layout);
        Stage popupStage = new Stage();
        popupController.setStage(popupStage);
        popupController.update();
        popupStage.setWidth(720);
        popupStage.setHeight(480);
        popupStage.setScene(scene);
        popupStage.showAndWait();

    }
    public void onConfirmButtonPress(ActionEvent event) throws RemoteException { //when you confirm your actions
            String temp = "";

            for (Pane entry : selectedIds) {
                char x = (char) (entry.getId().charAt(2) - '1' + 'A');
                char y = (entry.getId().charAt(4));
                temp += (x + "" + y + " ");
                //for (Pane r : changedBookshelfTiles
                //) {
                //    System.out.println(r.getId());
                //
                //}
            }

            //System.out.println(temp);
            tileMove = (MoveSelectTiles) new GUISelectTiles(gui.getNickname(), gui.getClient().getLobbyId() , temp.substring(0,temp.length()-1)).updateGUI(gui.getGame());

            if (tileMove != null) {
                gui.getClient().getClientConnection().getServerInterface().sendAction(Parser.toJson(tileMove, Move.class));

                columnMove = (MoveSelectColumn) new GUISelectColumn(gui.getNickname(), gui.getClient().getLobbyId(), selectedColumn-1).updateGUI(gui.getGame());

                if (columnMove != null) {
                    first_tile.setOpacity(0);
                    second_tile.setOpacity(0);
                    third_tile.setOpacity(0);
                    end_turn.setOpacity(1);
                    end_turn.setDisable(false);
                    end_turn.setText("End turn");
                    confirm.setDisable(true);
                    confirm.setOpacity(0);
                    for (Pane x : selectedIds) {
                        x.setOpacity(0);
                    }

                } else {
                    Alert alert = alertCreator();
                    alert.setContentText("You cannot perform this move, the column is unavailable!");
                    alert.showAndWait();
                    first_tile.setOpacity(0);
                    second_tile.setOpacity(0);
                    third_tile.setOpacity(0);
                    end_turn.setText("End turn");
                    for (Pane x : selectedIds) {
                        x.setOpacity(1);
                        x.setDisable(false);
                    }
                    changedBookshelfTiles.clear();
                    selectedIds.clear();
                    selectedColumn = 0;
                    action = true;
                    count = 0;
                }

            } else {
                Alert alert = alertCreator();
                alert.setContentText("You cannot perform this move, is against the rules!");
                alert.showAndWait();
                first_tile.setOpacity(0);
                second_tile.setOpacity(0);
                third_tile.setOpacity(0);
                end_turn.setText("End turn");
                for (Pane y : changedBookshelfTiles) {
                    y.setOpacity(0);
                }
                for (Pane x : selectedIds) {
                    x.setOpacity(1);
                    x.setDisable(false);
                }
                changedBookshelfTiles.clear();
                selectedIds.clear();
                selectedColumn = 0;
                action = true;
                count = 0;
            }
    }
    @Override
    public String getDimensions() {
        return  this.dim;
    }

    @Override
    public void setStage(Stage stage) {
    }

    public void onEndTurnButtonPress(ActionEvent event1) throws RemoteException { //if you press the end button
        if(end_turn.getText().equals("End turn")){ //when you end your action
            gui.getClient().getClientConnection().getServerInterface().sendAction(Parser.toJson(columnMove, Move.class));
            end_turn.setDisable(true);
            end_turn.setOpacity(0);
            List<Button> arrows = List.of(arrow1,arrow2,arrow3,arrow4,arrow5);
            List<Pane> allPanes = List.of(b_1_1, b_1_2, b_1_3, b_1_4, b_1_5, b_1_6,
                    b_2_1, b_2_2, b_2_3, b_2_4, b_2_5, b_2_6,
                    b_3_1, b_3_2, b_3_3, b_3_4, b_3_5, b_3_6,
                    b_4_1, b_4_2, b_4_3, b_4_4, b_4_5, b_4_6,
                    b_5_1, b_5_2, b_5_3, b_5_4, b_5_5, b_5_6,t_2_4, t_2_5, t_3_4, t_3_5, t_3_6, t_4_3, t_4_4, t_4_5, t_4_6,
                    t_4_7, t_4_8, t_5_2, t_5_3, t_5_4, t_5_5, t_5_6, t_5_7, t_5_8, t_6_2,
                    t_6_3, t_6_4, t_6_5, t_6_6, t_6_7, t_7_4, t_7_5, t_7_6, t_8_5, t_8_6, t_1_4, t_3_3, t_3_7, t_4_9, t_6_1, t_7_3, t_7_7, t_9_6, t_1_5, t_2_6, t_4_2, t_5_1, t_5_9, t_6_8, t_8_4, t_9_5);
            changedBookshelfTiles.clear();
            for (Pane x : selectedIds) {
                x.setOpacity(1);
                x.setDisable(false);
            }
            selectedIds.clear();
            selectedColumn = 0;
            action = false;
            count = 0;
            for (Pane p : allPanes) {
                p.setDisable(true);
                p.setOnMouseEntered(event -> {
                    p.setEffect(null);
                });

                p.setOnMouseExited(event -> {
                    p.setEffect(null);
                });
            }
            for(Button b : arrows){
                b.setDisable(true);
                b.setOnMouseEntered(event -> {
                    b.setEffect(null);
                });
                b.setOnMouseExited(event -> {
                    b.setEffect(null);
                });
            }
        }
        if(end_turn.getText().equals("Cancel")){ //when you cancel your action after selecting some tiles
            first_tile.setOpacity(0);
            second_tile.setOpacity(0);
            third_tile.setOpacity(0);
            end_turn.setText("End turn");
            end_turn.setDisable(true);
            end_turn.setOpacity(0);
            for (Pane y: changedBookshelfTiles) {
                y.setOpacity(0);
            }
            for (Pane x: selectedIds) {
                x.setOpacity(1);
                x.setDisable(false);
            }
            changedBookshelfTiles.clear();
            selectedIds.clear();
            selectedColumn = 0;
            action = true;
            count = 0;
        }
    }

    public void onTilePressed(Pane p) { //this method add tiles to the selected ones and show them on the selection panel
        if(action) {
            List<Pane> frames = List.of(first_tile, second_tile, third_tile);
            if (count < 3) {
                p.setOpacity(0);
                p.setDisable(true);
                frames.get(count).setBackground(p.getBackground());
                frames.get(count).setOpacity(1);
                selectedIds.add(p);
                count++;
                update();
                end_turn.setOpacity(1);
                end_turn.setDisable(false);
                end_turn.setText("Cancel");
            }
        }
    }


    @Override
    public void update() {
        if (gui.getClient().getState().equals(State.MYTURN)) action = true;
        else if (gui.getClient().getState().equals(State.WAITINGFORMYTURN)) action = false;
        List<Pane> listOfTwo = List.of(t_1_4, t_3_3, t_3_7, t_4_9, t_6_1, t_7_3, t_7_7, t_9_6, t_1_5, t_2_6, t_4_2, t_5_1, t_5_9, t_6_8, t_8_4, t_9_5);
        List<Pane> listOfThree = List.of(t_1_5, t_2_6, t_4_2, t_5_1, t_5_9, t_6_8, t_8_4, t_9_5);
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

        int i;
        int[][] m;
        int num = gui.getGame().getNumPlayers();
        Bookshelf bookshelf = null;

        List<Button> arrows = List.of(arrow1,arrow2,arrow3,arrow4,arrow5);

        P1.setText(gui.getClient().getGame().getBoard().getCommonGoalCards().get(0).getPoints().peek().toString());
        P2.setText(gui.getClient().getGame().getBoard().getCommonGoalCards().get(1).getPoints().peek().toString());

        try {
            m = ConfigsFromJson.getBoardConfig(Parser.getResourcePath("json/board_config.json")).pattern;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for(Player player : gui.getClient().getGame().getPlayers()) //initializing some labels
        {
            if(player.getUserName().equals(gui.getNickname()))
            {
                bookshelf=player.getBookshelf();
                L1.setText(gui.getNickname() + "'s goal: ");
            }
            if(player.getUserId() == gui.getClient().getGame().getCurrPlayerId())
            {
                L2.setText("It's "+ player.getUserName() +"'s turn");
            }
            if(action){
                end_turn.setDisable(true);
                end_turn.setOpacity(0);
                confirm.setDisable(false);
                confirm.setOpacity(1);
                for(Button b : arrows){
                    b.setDisable(false);
                    b.setOnMouseEntered(event -> {
                        b.setEffect(dropShadow);
                    });
                    b.setOnMouseExited(event -> {
                        b.setEffect(null);
                    });
                }
            }
            else {
                end_turn.setDisable(true);
                end_turn.setOpacity(0);
                confirm.setDisable(true);
                confirm.setOpacity(0);
                for(Button b : arrows){
                    b.setDisable(true);
                    b.setOnMouseEntered(event -> {
                        b.setEffect(null);
                    });
                    b.setOnMouseExited(event -> {
                        b.setEffect(null);
                    });
                }
            }
        }

        for (Pane p : paneMap.keySet()) { //initializing board + tiles
            int x = paneMap.get(p).x() - 1;
            int y = paneMap.get(p).y() - 1;
            if (m[x][y] != 0 && m[x][y] <= num && (gui.getGame().getBoard().getTile(x,y) != null && !gui.getGame().getBoard().getTile(x,y).Empty())){
                String imageUrl = Objects.requireNonNull(getClass().getResource("/images/itemTiles/" + gui.getGame().getBoard().getTile(x,y).getImage())).toExternalForm();
                p.setStyle("-fx-background-image: url('" + imageUrl + "');");
                p.setOpacity(1);
                p.setDisable(true);
                if(action){
                    if (gui.getGame().getBoard().getTile(x, y).isPickable()) {
                        p.setDisable(false);
                        p.setOnMouseEntered(event -> {
                            p.setEffect(dropShadow);
                        });

                        p.setOnMouseExited(event -> {
                            p.setEffect(null);
                        });

                        p.setOnMouseClicked(event -> onTilePressed(p));
                    }

                    for(i = 0; i < Bookshelf.getCols(); i++){ //initializing arrow buttons - what columns you can select
                        if(Objects.requireNonNull(bookshelf).getEmptyTilesColumn(i) >= selectedIds.size()){
                            arrows.get(i).setDisable(false);
                            int finalI = i;
                            arrows.get(i).setOnMouseEntered(event -> {
                                arrows.get(finalI).setEffect(dropShadow);
                            });
                            arrows.get(i).setOnMouseExited(event -> {
                                arrows.get(finalI).setEffect(null);
                            });
                        }
                        else {
                            arrows.get(i).setDisable(true);
                        }
                    }
                }
            } else if(gui.getGame().getBoard().getTile(x,y) == null || gui.getGame().getBoard().getTile(x,y).Empty()) {
                p.setBackground(null);
                for (Pane k : selectedIds) {
                    k.setOpacity(0);
                }
            }
        }

        if(gui.getGame().getNumPlayers()==2){
            for(Pane p : listOfTwo){
                p.setOpacity(0);
            }
        }
        if(gui.getGame().getNumPlayers()==3){
            for(Pane p : listOfThree){
                p.setOpacity(0);
            }
        }

        for (Pane p : bookshelfMap.keySet()) {
            int x = bookshelfMap.get(p).x() - 1;
            int y = bookshelfMap.get(p).y() -1;
            if (Objects.requireNonNull(bookshelf).getSlots()[y][x] != null && !(bookshelf.getSlots()[y][x].Empty())) {
                String imageUrl = Objects.requireNonNull(getClass().getResource("/images/itemTiles/" + bookshelf.getSlots()[y][x].getImage())).toExternalForm();
                p.setStyle("-fx-background-image: url('" + imageUrl + "');");
            }else {
                p.setBackground(null);
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

        if(action) {
            //preview on the bookshelf
            if (selectedColumn > 0 && selectedColumn <= Bookshelf.getCols() && selectedIds.size() != 0) {
                int ind = 0;
                int first = Objects.requireNonNull(bookshelf).lastTileOnColumnIndex(selectedColumn - 1 ) + 1;
                HashMap<Pane,Integer> k = new HashMap<>();
                    for (Pane x : bookshelfMap.keySet()) {if (bookshelfMap.get(x).x() == selectedColumn) {k.put(x, bookshelfMap.get(x).y());}}
                    while (ind < selectedIds.size()) {
                        for (Pane x : k.keySet()) {
                            if (k.get(x) == first && ind < selectedIds.size()) {
                                x.setBackground(selectedIds.get(ind).getBackground());
                                ind++;
                                first++;
                                changedBookshelfTiles.add(x);
                                x.setOpacity(1);
                            }
                        }
                    }
                }
            }
        }
    }