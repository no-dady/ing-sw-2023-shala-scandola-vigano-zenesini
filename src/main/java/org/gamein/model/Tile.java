package org.gamein.model;

import java.util.Optional;

public class Tile {
    private Optional<Integer> tileID;
    private TileType tileType;

    public TileType getTileType()
    {
        return this.tileType;
    }

    public Optional<Integer> getTileId()
    {
        return this.tileID;
    }
}
