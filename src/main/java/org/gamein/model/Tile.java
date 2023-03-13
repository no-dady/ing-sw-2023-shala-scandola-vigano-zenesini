package org.gamein.model;

import java.util.Optional;

public class Tile {
    private Integer tileID;
    private final TileType tileType;

    public Tile(TileType type) {
        this.tileType = type;
    }

    public TileType getTileType()
    {
        return this.tileType;
    }

    public Optional<Integer> getTileId()
    {
        return Optional.of(this.tileID);
    }
}
