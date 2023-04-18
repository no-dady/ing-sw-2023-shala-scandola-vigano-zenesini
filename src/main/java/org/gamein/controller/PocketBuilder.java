package org.gamein.controller;

import org.gamein.model.Tile;
import org.gamein.model.TileType;
import java.util.Collections;

import java.util.ArrayList;

public class PocketBuilder {
    public ArrayList<Tile> createTileListPocket(int numberOfTiles) {

        ArrayList<Tile> result = new ArrayList<>();
        TileType[] x = TileType.values();

        for (int i = 0; i < numberOfTiles; i++) {
            result.add(new Tile(x[i % 6], i));
        }

        Collections.shuffle(result);

        return result;
    }

}