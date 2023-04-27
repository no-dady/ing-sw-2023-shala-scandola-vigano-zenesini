package org.gamein.model;

import org.gamein.view.ConsoleColors;

import java.util.Random;


public enum TileType {
    CAT(ConsoleColors.GREEN_BACKGROUND, "CAT"), TROPHY(ConsoleColors.CYAN_BACKGROUND, "TRO"), BOOK(ConsoleColors.WHITE_BACKGROUND_BRIGHT, "BOO"), TOYS(ConsoleColors.RED_BACKGROUND_BRIGHT, "TOY"), FRAMES(ConsoleColors.BLUE_BACKGROUND, "FRA"), FLOWERS(ConsoleColors.PURPLE_BACKGROUND_BRIGHT, "FLO"), EMPTY(ConsoleColors.RESET, "   ");
    private final String color;
    private final String sign;
    private int image;

    TileType(String color, String sign) {
        this.color = color;
        this.sign = sign;
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

    public String getSign() {
        return this.sign;
    }
}
