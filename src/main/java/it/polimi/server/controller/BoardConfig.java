package it.polimi.server.controller;

import it.polimi.server.model.Pocket;
import it.polimi.server.model.Tile;
import it.polimi.setup.ConfigsFromJson;
import it.polimi.util.Parser;

/**
 * The type Board config.
 */
public class BoardConfig {

    /**
     * The Rows.
     */
    public int rows;
    /**
     * The Cols.
     */
    public int cols;
    /**
     * The Pattern.
     */
    public int[][] pattern;

    /**
     * New empty board tile [ ] [ ].
     *
     * @return the tile [ ] [ ]
     */
    public static Tile[][] newEmptyBoard() {
        try {
            BoardConfig boardConfig = ConfigsFromJson.getBoardConfig(Parser.getResourcePath("json/board_config.json"));
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
    /**
     * Fill board tile [ ] [ ].
     *
     * @param board     the board
     * @param pocket    the pocket
     * @param playerNum the player num
     * @return the tile [ ] [ ]
     */
    public static Tile[][] fillBoard(Tile[][] board, Pocket pocket, int playerNum) {
        try {
            BoardConfig boardConfig = ConfigsFromJson.getBoardConfig(Parser.getResourcePath("json/board_config.json"));
            for (int i = 0; i < boardConfig.rows; i++) {
                for (int j = 0; j < boardConfig.cols; j++) {
                    if ((boardConfig.pattern[i][j] <= playerNum && boardConfig.pattern[i][j] != 0) & board[i][j].Empty() && pocket.getLeft() != 0) {
                        board[i][j] = pocket.popTiles(1).get(0);
                    }
                }
            }
            return board;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
    }
}