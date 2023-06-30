package it.polimi.server.controller;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import it.polimi.server.model.*;


/**
 * <p>TestBoardCreationAndFill class.</p>
 *
 * @author daniel
 * @version $Id: $Id
 * @since 1.0
 */
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
     * <p>suite.</p>
     *
     * @return the suite of tests being tested
     */
    public static Test suite() {return new TestSuite( TestBoardCreationAndFill.class );}

    /**
     * Rigourous Test :-)
     *
     */
    public void test_Board() {
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
