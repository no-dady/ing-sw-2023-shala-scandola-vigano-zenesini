package org.gamein;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import server.controller.PocketBuilder;
import server.model.Tile;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

public class PocketBuilderTest extends TestCase {
    public PocketBuilder x;
    public final AtomicReference<ArrayList<Tile>> pocket = new AtomicReference<ArrayList<Tile>>();
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public PocketBuilderTest( String testName) { super( testName );
    }
    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {return new TestSuite( PocketBuilderTest.class );}

    /**
     * Rigourous Test :-)
     */
    public void Pocket_Builder_Test(){
        int i;
        Scanner myObj = new Scanner(System.in);

        System.out.println("Inserisci numero tile da generare: ");
        int n = Integer.parseInt(myObj.nextLine());

        ArrayList<Tile> pocket = x.createTileListPocket(n);
        for(i = n; i >= 0; i--)
        {
            System.out.print(pocket.get(i).getTileType() + "\t|\t");
        }
        boolean ass = true;
        assertFalse("wow it works", ass);

    }

}
