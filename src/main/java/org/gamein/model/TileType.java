package org.gamein.model;

import java.util.Random;

public enum TileType {
    CAT("green"), TROPHY("cyan"), BOOK("white"), TOYS("orange"), FRAMES("blue"), FLOWERS("pink"), EMPTY("");
    private final String color;
    private int image;

    TileType(String color) {
        this.color = color;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getColor() {
        return this.color;
    }

    public int getImage() {
        return this.image;
    }

}
