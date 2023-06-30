package it.polimi.server.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import it.polimi.util.Parser;

import static org.junit.jupiter.api.Assertions.*;

class TileTypeTest {

    static TileType testTT;
    @BeforeAll
    static void setup() {
        testTT = new TileType("CAT", 2);
    }

    @Test
    void getTileMap() {
        System.out.println(Parser.toJson(testTT.getTileMap(), testTT.getTileMap().getClass()));
    }

    @Test
    void getKey() {
        System.out.println(Parser.toJson(testTT, TileType.class));
        assertEquals("CAT", testTT.getKey());
    }

    @Test
    void parseTile() {
        TileType t = new TileType("CAT", 0);
        String test = Parser.toJson(t, TileType.class);
        System.out.println(test);
        assertEquals(t.getKey(), Parser.fromJson(test, TileType.class).getKey());
        assertEquals(t.getImage(), Parser.fromJson(test, TileType.class).getImage());
        assertEquals(t.getColor(), Parser.fromJson(test, TileType.class).getColor());
        assertEquals(t.getSign(), Parser.fromJson(test, TileType.class).getSign());
    }

    @Test
    void values() {
        testTT.getTileMap().keySet().stream().forEach((x) -> System.out.println(x));
    }
}