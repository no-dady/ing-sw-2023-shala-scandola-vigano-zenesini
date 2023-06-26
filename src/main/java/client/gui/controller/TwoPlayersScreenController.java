package client.gui.controller;

import client.gui.GUI;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import server.model.Bookshelf;
import server.model.Coordinates;
import server.model.Player;

import java.net.URL;
import java.util.*;

public class TwoPlayersScreenController implements GenericInterface, Initializable {
    public static final String name ="two-players-screen";
    public Label L3;
    public Pane t2_1_1, t2_3_1, t2_5_1, t2_7_1, t2_9_1, t2_11_1,
                t2_1_3, t2_3_3, t2_5_3, t2_7_3, t2_9_3, t2_11_3,
                t2_1_5, t2_3_5, t2_5_5, t2_7_5, t2_9_5, t2_11_5,
                t2_1_7, t2_3_7, t2_5_7, t2_7_7, t2_9_7, t2_11_7,
                t2_1_9, t2_3_9, t2_5_9, t2_7_9, t2_9_9, t2_11_9;
    private GUI gui;
    public final String dim = "1920x1080";
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        



    }


    public void onBackPress(ActionEvent event) {
        gui.activate(BoardController.name);
    }
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void update() {
        HashMap<Pane, Coordinates> bookshelfMap1 = new HashMap<>();
        bookshelfMap1.put( t2_1_1, new Coordinates(1,6));
        bookshelfMap1.put( t2_3_1, new Coordinates(1,5));
        bookshelfMap1.put( t2_5_1, new Coordinates(1,4));
        bookshelfMap1.put( t2_7_1, new Coordinates(1,3));
        bookshelfMap1.put( t2_9_1, new Coordinates(1,2));
        bookshelfMap1.put( t2_11_1, new Coordinates(1,1));
        bookshelfMap1.put( t2_1_3, new Coordinates(2,6));
        bookshelfMap1.put( t2_3_3, new Coordinates(2,5));
        bookshelfMap1.put( t2_5_3, new Coordinates(2,4));
        bookshelfMap1.put( t2_7_3, new Coordinates(2,3));
        bookshelfMap1.put( t2_9_3, new Coordinates(2,2));
        bookshelfMap1.put( t2_11_3, new Coordinates(2,1));
        bookshelfMap1.put( t2_1_5, new Coordinates(3,6));
        bookshelfMap1.put( t2_3_5, new Coordinates(3,5));
        bookshelfMap1.put( t2_5_5, new Coordinates(3,4));
        bookshelfMap1.put( t2_7_5, new Coordinates(3,3));
        bookshelfMap1.put( t2_9_5, new Coordinates(3,2));
        bookshelfMap1.put( t2_11_5, new Coordinates(3,1));
        bookshelfMap1.put( t2_1_7, new Coordinates(4,6));
        bookshelfMap1.put( t2_3_7, new Coordinates(4,5));
        bookshelfMap1.put( t2_5_7, new Coordinates(4,4));
        bookshelfMap1.put( t2_7_7, new Coordinates(4,3));
        bookshelfMap1.put( t2_9_7, new Coordinates(4,2));
        bookshelfMap1.put( t2_11_7, new Coordinates(4,1));
        bookshelfMap1.put( t2_1_9, new Coordinates(5,6));
        bookshelfMap1.put( t2_3_9, new Coordinates(5,5));
        bookshelfMap1.put( t2_5_9, new Coordinates(5,4));
        bookshelfMap1.put( t2_7_9, new Coordinates(5,3));
        bookshelfMap1.put( t2_9_9, new Coordinates(5,2));
        bookshelfMap1.put( t2_11_9, new Coordinates(5,1));
        ArrayList<Player> playersThatAreNotMe = new ArrayList<>();
        for (Player p : gui.getGame().getPlayers())  if (!p.getUserName().equals(gui.getNickname())) playersThatAreNotMe.add(p);
        for (Player p : playersThatAreNotMe
        ) {
            L3.setText(p.getUserName());
            Bookshelf bookshelf = p.getBookshelf();
            for (Pane pl : bookshelfMap1.keySet()) {
                int x = bookshelfMap1.get(pl).x() - 1;
                int y = bookshelfMap1.get(pl).y() - 1;
                if (bookshelf.getSlots()[y][x] != null) {
                    String imageUrl = Objects.requireNonNull(getClass().getResource("/images/itemTiles/" + bookshelf.getSlots()[y][x].getImage())).toExternalForm();
                    pl.setStyle("-fx-background-image: url('" + imageUrl + "');");
                }
            }
        }
    }
    @Override
    public String getDimensions() {
        return  this.dim;
    }

    @Override
    public void setGUI(GUI gui) {
        this.gui = gui;

    }
}