package org.gamein.model;

import java.util.Optional;

public class Tile {
    private Integer tileID;
    private final TileType tileType;

    public Tile(TileType type, Integer id) {
        this.tileType = type;
        this.tileID = id;
    }

    public Tile(){
        this.tileType = TileType.EMPTY;
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
