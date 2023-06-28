package server.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import server.model.Board;
import server.model.Pocket;

import static org.junit.jupiter.api.Assertions.*;

class BoardConfigTest {

    Board board;
    Pocket pocket;
    PocketBuilder pocketBuilder = new PocketBuilder();


    @BeforeEach
    void setUp() {
        pocket = new Pocket(pocketBuilder.createTileListPocket(132));
    }

    @Test
    void newEmptyBoard() {
        board = new Board(BoardConfig.newEmptyBoard());
        assertNotNull(board);
    }

    @Test
    void fillBoard() {
    }
}