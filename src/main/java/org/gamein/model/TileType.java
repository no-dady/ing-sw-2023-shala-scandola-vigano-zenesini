package org.gamein.model;

import java.util.Random;

// TODO: Complete enumeration
public enum TileType {
    CAT("green"), TROPHY("cyan"), BOOK("white"), TOYS("orange"), FRAMES("blue"), FLOWERS("pink");
    private final String color;
    private final int image;

    TileType(String color)
    {
        Random r = new Random();
        image = r.ints(1,3).iterator().nextInt();
        this.color = color;
    }

    public String getColor() {
        return this.color;
    }
    public int getImage()
    {
        return this.image;
    }

}
