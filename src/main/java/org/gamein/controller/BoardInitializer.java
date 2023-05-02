package org.gamein.controller;

import com.google.gson.Gson;
import org.gamein.model.Tile;
import org.gamein.model.TileType;


import java.io.FileReader;
import java.io.IOException;

/**
 * The type Board initializer.
 */
public class BoardInitializer {
    /**
     * New empty board tile [ ] [ ].
     *
     * @return the tile [ ] [ ]
     */
    public static Tile[][] newEmptyBoard() {
        Gson gson = new Gson();
        Tile[][] board = new Tile[0][0];
        try (FileReader reader = new FileReader("src/main/resources/json/board_config.json")) {
            BoardConfig boardConfig = gson.fromJson(reader, BoardConfig.class);
             board = new Tile[boardConfig.cols][boardConfig.rows];
             for (int i = 0; i < boardConfig.rows; i++) {
                for (int j = 0; j < boardConfig.cols; j++) {
                     board[i][j] = new Tile();
                    }
                }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            }
        return board;
    }
}