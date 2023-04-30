package org.gamein.controller;

import org.gamein.model.Tile;
import org.gamein.model.TileType;
import java.util.Collections;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

public class PocketBuilder {
    public ArrayList<Tile> createTileListPocket(int numberOfTiles) {

        ArrayList<Tile> result = new ArrayList<>();
        Set<String> x = TileType.values();

        for (int i = 0; i < numberOfTiles; i++) {
            result.add(new Tile(new TileType((String) x.toArray()[i % (x.size() -1)], i%3), i));
        }

        Collections.shuffle(result);

        return result;
    }

}