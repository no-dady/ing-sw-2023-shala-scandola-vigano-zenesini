package org.gamein.view;

import org.gamein.model.*;
import com.google.gson.Gson;
import java.io.FileReader;
import java.io.IOException;

public class TextUI {
    public String boardArt = "";
    public String bookshelfArt = "";

    public void openTextualArt() {

        Gson gson = new Gson();
        try (FileReader reader = new FileReader("src/main/resources/json/board_art.json")) {
            boardArt = gson.fromJson(reader, String.class);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try (FileReader reader = new FileReader("src/main/resources/json/bookshelf_art.json")) {
            bookshelfArt = gson.fromJson(reader, String.class);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void printArt(Tile[][] slots, String art) {
        int x = 0, y = 0;
        for (int i = 0; i < art.length(); i++) {
            if ( 'X' == art.charAt(i)) {
                System.out.print(slots[x][y].getTileType().getColor() + ConsoleColors.BLACK_BOLD +  slots[x][y].getTileType().getSign() + ConsoleColors.RESET );
                y++;
            }
            else{
                System.out.print(art.charAt(i));
            }
            if (y == slots[0].length && '\n' == art.charAt(i)) {
                x++;
                y = 0;
            }
        }
    }
}