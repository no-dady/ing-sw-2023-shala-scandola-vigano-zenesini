package org.gamein.model;

import java.util.Random;

// TODO: Completare enumerazione con tutte le tiles
public enum TileType {
    ;

    private final int image;

    TileType()
    {
        Random r = new Random();
        image = r.ints(1,3).iterator().nextInt();
    }
    public int getImage()
    {
        return this.image;
    }

}
