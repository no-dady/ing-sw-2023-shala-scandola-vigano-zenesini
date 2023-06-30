package it.polimi.server.controller;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import it.polimi.server.model.Tile;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

/**
 * <p>PocketBuilderTest class.</p>
 *
 * @author daniel
 * @version $Id: $Id
 * @since 1.0
 */
public class PocketBuilderTest extends TestCase {
    public PocketBuilder x = new PocketBuilder();
    public final AtomicReference<ArrayList<Tile>> pocket = new AtomicReference<ArrayList<Tile>>();
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public PocketBuilderTest( String testName) { super( testName );
    }
    /**
     * <p>suite.</p>
     *
     * @return the suite of tests being tested
     */
    public static Test suite() {return new TestSuite( PocketBuilderTest.class );}

    /**
     * Rigourous Test :-)
     *
     */
    public void test_Pocket() {
        int i;
        int n = 132;
        ArrayList<Tile> pocket = x.createTileListPocket(n);
        for(i = 0; i < n; i++)
        {
            System.out.print(pocket.get(i).getTileType().substring(0,2) + "\t|\t");
        }
        boolean ass = true;
        assertTrue("very sad it didn't workk", ass);

    }

}
