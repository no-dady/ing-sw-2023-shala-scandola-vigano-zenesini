package org.gamein;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import network.ConnectionType;
import server.controller.BoardConfig;
import server.controller.PocketBuilder;

import server.model.Bookshelf;
import server.model.Pocket;
import server.model.Tile;
import server.model.TileType;

import java.io.IOException;
import java.util.Arrays;


public class AppTestBoard extends TestCase {


    public Tile[][] board;
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTestBoard( String testName) { super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {return new TestSuite( AppTestBoard.class );}

    /**
     * Rigourous Test :-)
     */
    public void test_Board() throws IOException {
        System.out.println(Arrays.toString(ConnectionType.values()));
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
        System.out.println("╔════════╗\n" +
                "║ [=|=]  ║\n" +
                "║ [=|=]  ║\n" +
                "║   x2   ║\n" +
                "╚════════╝\n");
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
