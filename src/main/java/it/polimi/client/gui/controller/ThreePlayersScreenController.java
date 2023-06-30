package it.polimi.client.gui.controller;

import it.polimi.client.gui.GUI;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import it.polimi.server.model.Bookshelf;
import it.polimi.server.model.Coordinates;
import it.polimi.server.model.Player;

import java.net.URL;
import java.util.*;

/**
 * <p>ThreePlayersScreenController class.</p>
 *
 * @author daniel
 * @version $Id: $Id
 */
public class ThreePlayersScreenController implements GenericInterface, Initializable {
    /** Constant <code>name="three-players-screen"</code> */
    public static final String name ="three-players-screen";
    private GUI gui;
    public Label L3;
    public Label L4;
    public final String dim = "1920x1080";
    private Stage stage;
    /** {@inheritDoc} */
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public Pane t2_1_1, t2_3_1, t2_5_1, t2_7_1, t2_9_1, t2_11_1,
                t2_1_3, t2_3_3, t2_5_3, t2_7_3, t2_9_3, t2_11_3,
                t2_1_5, t2_3_5, t2_5_5, t2_7_5, t2_9_5, t2_11_5,
                t2_1_7, t2_3_7, t2_5_7, t2_7_7, t2_9_7, t2_11_7,
                t2_1_9, t2_3_9, t2_5_9, t2_7_9, t2_9_9, t2_11_9;

    public Pane t3_1_1, t3_3_1, t3_5_1, t3_7_1, t3_9_1, t3_11_1,
                t3_1_3, t3_3_3, t3_5_3, t3_7_3, t3_9_3, t3_11_3,
                t3_1_5, t3_3_5, t3_5_5, t3_7_5, t3_9_5, t3_11_5,
                t3_1_7, t3_3_7, t3_5_7, t3_7_7, t3_9_7, t3_11_7,
                t3_1_9, t3_3_9, t3_5_9, t3_7_9, t3_9_9, t3_11_9;
    /** {@inheritDoc} */
    @Override
    public void setGUI(GUI gui) {
        this.gui = gui;
    }
    /** {@inheritDoc} */
    @Override
    public String getDimensions() {
        return  this.dim;
    }
    /** {@inheritDoc} */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**
     * <p>onBackPress.</p>
     *
     * @param event a {@link javafx.event.ActionEvent} object
     */
    public void onBackPress(ActionEvent event) {
        stage.hide();
    }
    /** {@inheritDoc} */
    @Override
    public String getName() {
        return name;
    }

    /** {@inheritDoc} */
    @Override
    public void update() {
        List<Label> textFields = List.of(L3, L4);
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
        HashMap<Pane, Coordinates> bookshelfMap2 = new HashMap<>();
        bookshelfMap2.put( t3_1_1, new Coordinates(1,6));
        bookshelfMap2.put( t3_3_1, new Coordinates(1,5));
        bookshelfMap2.put( t3_5_1, new Coordinates(1,4));
        bookshelfMap2.put( t3_7_1, new Coordinates(1,3));
        bookshelfMap2.put( t3_9_1, new Coordinates(1,2));
        bookshelfMap2.put( t3_11_1, new Coordinates(1,1));
        bookshelfMap2.put( t3_1_3, new Coordinates(2,6));
        bookshelfMap2.put( t3_3_3, new Coordinates(2,5));
        bookshelfMap2.put( t3_5_3, new Coordinates(2,4));
        bookshelfMap2.put( t3_7_3, new Coordinates(2,3));
        bookshelfMap2.put( t3_9_3, new Coordinates(2,2));
        bookshelfMap2.put( t3_11_3, new Coordinates(2,1));
        bookshelfMap2.put( t3_1_5, new Coordinates(3,6));
        bookshelfMap2.put( t3_3_5, new Coordinates(3,5));
        bookshelfMap2.put( t3_5_5, new Coordinates(3,4));
        bookshelfMap2.put( t3_7_5, new Coordinates(3,3));
        bookshelfMap2.put( t3_9_5, new Coordinates(3,2));
        bookshelfMap2.put( t3_11_5, new Coordinates(3,1));
        bookshelfMap2.put( t3_1_7, new Coordinates(4,6));
        bookshelfMap2.put( t3_3_7, new Coordinates(4,5));
        bookshelfMap2.put( t3_5_7, new Coordinates(4,4));
        bookshelfMap2.put( t3_7_7, new Coordinates(4,3));
        bookshelfMap2.put( t3_9_7, new Coordinates(4,2));
        bookshelfMap2.put( t3_11_7, new Coordinates(4,1));
        bookshelfMap2.put( t3_1_9, new Coordinates(5,6));
        bookshelfMap2.put( t3_3_9, new Coordinates(5,5));
        bookshelfMap2.put( t3_5_9, new Coordinates(5,4));
        bookshelfMap2.put( t3_7_9, new Coordinates(5,3));
        bookshelfMap2.put( t3_9_9, new Coordinates(5,2));
        bookshelfMap2.put( t3_11_9, new Coordinates(5,1));

        List<HashMap<Pane, Coordinates>> bookshelfTiles = List.of(bookshelfMap1, bookshelfMap2);
        ArrayList<Player> playersThatAreNotMe = new ArrayList<>();
        for (Player p : gui.getGame().getPlayers())  if (!p.getUserName().equals(gui.getNickname())) playersThatAreNotMe.add(p);

        for (Player p : playersThatAreNotMe
        ) {
            textFields.get(playersThatAreNotMe.indexOf(p)).setText(p.getUserName());
            Bookshelf bookshelf = p.getBookshelf();
            for (Pane pl : bookshelfTiles.get(playersThatAreNotMe.indexOf(p)).keySet()) {
                int x = bookshelfTiles.get(playersThatAreNotMe.indexOf(p)).get(pl).x() - 1;
                int y = bookshelfTiles.get(playersThatAreNotMe.indexOf(p)).get(pl).y() -1;
                if (bookshelf.getSlots()[y][x] != null && !bookshelf.getSlots()[y][x].Empty()) {
                    String imageUrl = Objects.requireNonNull(getClass().getResource("/images/itemTiles/" + bookshelf.getSlots()[y][x].getImage())).toExternalForm();
                    pl.setStyle("-fx-background-image: url('" + imageUrl + "');");
                }
            }
        }
    }
}
