package org.gamein.model;

import java.util.Random;

// TODO: Complete enumeration
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
