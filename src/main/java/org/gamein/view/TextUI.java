package server.view;

import server.model.*;
import com.google.gson.Gson;
import java.io.FileReader;
import java.io.IOException;

import static org.gamein.model.TileType.getTileMap;

/**
 * The type Text ui.
 */
public class TextUI {
    /**
     * The Board art.
     */
    public String boardArt = "";
    /**
     * The Bookshelf art.
     */
    public String bookshelfArt = "";

    /**
     * Open textual art.
     */
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

    /**
     * Print art.
     *
     * @param slots the slots
     * @param art   the art
     */
    public void printArt(Tile[][] slots, String art) {
        int x = 0, y = 0;
        for (int i = 0; i < art.length(); i++) {
            if ( 'X' == art.charAt(i)) {
                String PRESET = "\033[";
                String color = ConsoleColors.BLUE_BACKGROUND + PRESET + getTileMap().get(slots[x][y].getTileType()).color;
                System.out.print( color + getTileMap().get(slots[x][y].getTileType()).sign + ConsoleColors.RESET );
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