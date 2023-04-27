package org.gamein.controller;

import org.gamein.model.Pocket;
import org.gamein.model.Tile;
import com.google.gson.Gson;
import org.gamein.model.TileType;

import java.io.FileReader;
import java.io.IOException;

public class BoardFiller {

    public Tile[][] fillBoard(Tile[][] board, Pocket pocket, int playerNum) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader("src/main/resources/json/board_config.json")) {
            BoardConfig boardConfig = gson.fromJson(reader, BoardConfig.class);
            for (int i = 0; i < boardConfig.rows; i++) {
                for (int j = 0; j < boardConfig.cols; j++) {
                    if ((boardConfig.pattern[i][j] <= playerNum  && boardConfig.pattern[i][j] != 0) & board[i][j].getTileType().equals(TileType.EMPTY)) {
                        board[i][j] = pocket.popTiles(1).get(0);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return board;
    }
}