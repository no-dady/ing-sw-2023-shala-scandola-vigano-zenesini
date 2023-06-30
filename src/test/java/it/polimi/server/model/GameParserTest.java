package it.polimi.server.model;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import it.polimi.util.Parser;

/**
 * <p>GameParserTest class.</p>
 *
 * @author daniel
 * @version $Id: $Id
 * @since 1.0
 */
public class GameParserTest extends TestCase {


    /**
     * <p>Constructor for GameParserTest.</p>
     *
     * @param testName a {@link java.lang.String} object
     */
    public GameParserTest( String testName) { super( testName ); }

    /**
     * <p>suite.</p>
     *
     * @return a {@link junit.framework.Test} object
     */
    public static Test suite() { return new TestSuite(GameParserTest.class);
    }


    /**
     * <p>test_gameSerialization.</p>
     *
     */
    public void test_gameSerialization() {
        Game game = new Game();
        String jsongame = Parser.toJson(game, Game.class);
        System.out.println(jsongame);
        String jsonCgcs = Parser.toJson(game.getCgcs().toArray()[0], CommonGoalCardStrategy.class);
        System.out.println(jsonCgcs);

        CommonGoalCardStrategy prova = Parser.fromJson(jsonCgcs, CommonGoalCardStrategy.class);

        assertTrue(true);
    }
}
