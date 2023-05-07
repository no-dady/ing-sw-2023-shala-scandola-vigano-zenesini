package server.controller;

import server.model.Pocket;
import server.model.Tile;
import setup.ConfigsFromJson;

/**
 * The type Board filler.
 */
public class BoardFiller {

    /**
     * Fill board tile [ ] [ ].
     *
     * @param board     the board
     * @param pocket    the pocket
     * @param playerNum the player num
     * @return the tile [ ] [ ]
     */
    public Tile[][] fillBoard(Tile[][] board, Pocket pocket, int playerNum) {
        try {
            BoardConfig boardConfig = ConfigsFromJson.getBoardConfig("src/main/resources/json/board_config.json");
            for (int i = 0; i < boardConfig.rows; i++) {
                for (int j = 0; j < boardConfig.cols; j++) {
                    if ((boardConfig.pattern[i][j] <= playerNum && boardConfig.pattern[i][j] != 0) & board[i][j].Empty()) {
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