package org.gamein;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import network.ConnectionType;
import server.controller.BoardConfig;
import server.controller.PocketBuilder;

import server.model.*;
import setup.ConfigsFromJson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class TestBoardCreationAndFill extends TestCase {


    public Tile[][] board;
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public TestBoardCreationAndFill(String testName) { super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {return new TestSuite( TestBoardCreationAndFill.class );}

    /**
     * Rigourous Test :-)
     */
    public void test_Board() throws IOException {

        TileType test = new TileType();
        Bookshelf bookshelf = new Bookshelf();
        Tile[][] tiles = bookshelf.getSlots();
        board = BoardConfig.newEmptyBoard();
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                assert board != null;
                System.out.print(board[i][j].getTileType() + "\t|\t");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
        System.out.println();


        //BoardFiller boardFiller = new BoardFiller();
        PocketBuilder pocketBuilder = new PocketBuilder();
        board = BoardConfig.newEmptyBoard();
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                System.out.print(board[i][j].getTileType() + "\t|\t");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
        System.out.println();


        board = BoardConfig.fillBoard(board, new Pocket(pocketBuilder.createTileListPocket(132)), 2);
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                System.out.print(board[i][j].getTileType() + "\t\t|\t");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
        System.out.println();
        board = BoardConfig.newEmptyBoard();
        board = BoardConfig.fillBoard(board, new Pocket(pocketBuilder.createTileListPocket(132)), 3);
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                System.out.print(board[i][j].getTileType() + "\t\t|\t");
            }
            System.out.println();
        }

        System.out.println();
        System.out.println();
        System.out.println();

        board = BoardConfig.newEmptyBoard();
        board = BoardConfig.fillBoard(board, new Pocket(pocketBuilder.createTileListPocket(132)), 4);
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                System.out.print(board[i][j].getTileType() + "\t\t|\t");
            }
            System.out.println();
        }
        boolean assertion = true;
        assertTrue("did it print everything?", assertion);
    }
}
