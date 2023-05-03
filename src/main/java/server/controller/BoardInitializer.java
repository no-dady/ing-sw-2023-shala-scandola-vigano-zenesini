package server.controller;

import server.model.Tile;
import setup.ConfigsFromJson;

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
        try {
            BoardConfig boardConfig = ConfigsFromJson.getBoardConfig("src/main/resources/json/board_config.json");
            Tile[][] board = new Tile[boardConfig.cols][boardConfig.rows];
            for (int i = 0; i < boardConfig.rows; i++) {
                for (int j = 0; j < boardConfig.cols; j++) {
                    board[i][j] = new Tile();
                }
            }
            return board;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}