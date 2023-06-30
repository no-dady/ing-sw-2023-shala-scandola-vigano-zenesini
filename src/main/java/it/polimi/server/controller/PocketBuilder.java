package it.polimi.server.controller;

import it.polimi.server.model.Tile;
import it.polimi.server.model.TileType;
import java.util.Collections;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

/**
 * The type Pocket builder.
 *
 * @author daniel
 * @version $Id: $Id
 */
public class PocketBuilder {
    /**
     * Create tile list pocket array list.
     *
     * @param numberOfTiles the number of tiles
     * @return the array list
     */
    public ArrayList<Tile> createTileListPocket(int numberOfTiles) {

        ArrayList<Tile> result = new ArrayList<>();
        Set<String> x = TileType.values();

        for (int i = 0; i < numberOfTiles; i++) {
            result.add(new Tile(new TileType((String) x.toArray()[i % (x.size() -1)], new Random().nextInt(3)), i));
        }

        Collections.shuffle(result);

        return result;
    }

}
