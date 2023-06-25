package server.model;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;
import util.CommonGoalSerializer;
import util.Parser;

import java.io.IOException;

public class GameParserTest extends TestCase {


    public GameParserTest( String testName) { super( testName ); }

    public static Test suite() { return new TestSuite(GameParserTest.class);
    }


    public void test_gameSerialization()  throws IOException {
        Game game = new Game();
        String jsongame = Parser.toJson(game, Game.class);
        System.out.println(jsongame);
        String jsonCgcs = Parser.toJson(game.getCgcs().toArray()[0], CommonGoalCardStrategy.class);
        System.out.println(jsonCgcs);

        CommonGoalCardStrategy prova = Parser.fromJson(jsonCgcs, CommonGoalCardStrategy.class);

        assertTrue(true);
    }
}