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
        try (FileReader reader = new FileReader("src/test/resources/json/shelves/board_config.json")) {
            BoardConfig boardConfig = gson.fromJson(reader, BoardConfig.class);
            for (int i = 0; i < boardConfig.rows; i++) {
                for (int j = 0; j < boardConfig.cols; j++) {
                    switch (playerNum) {
                        case 2 -> {
                            if ((boardConfig.pattern[i][j] == playerNum) & board[i][j].getTileType().equals(TileType.EMPTY)) {
                                System.out.println(" sono alla posizione " + i + " " + j + " " + playerNum + " è uguale a " + boardConfig.pattern[i][j] + " e qui ho tile di tipo " + board[i][j].getTileType());
                                board[i][j] = pocket.popTiles(1).get(0);
                                System.out.println(" ho posizionato " + board[i][j].getTileType());
                            }
                        }
                        case 3 -> {
                            if ((boardConfig.pattern[i][j] == playerNum || boardConfig.pattern[i][j] == playerNum - 1) & board[i][j].getTileType().equals(TileType.EMPTY)) {
                                board[i][j] = pocket.popTiles(1).get(0);
                            }
                        }
                        case 4 -> {
                            if ((boardConfig.pattern[i][j] == playerNum || boardConfig.pattern[i][j] == playerNum - 1 || boardConfig.pattern[i][j] == playerNum - 2) & board[i][j].getTileType().equals(TileType.EMPTY)) {
                                board[i][j] = pocket.popTiles(1).get(0);
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return board;
    }
}