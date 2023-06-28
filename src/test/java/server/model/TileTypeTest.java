package server.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import util.Parser;

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
    void getColor() {
    }

    @Test
    void getImage() {
    }

    @Test
    void getSign() {
    }

    @Test
    void values() {
        testTT.getTileMap().keySet().stream().forEach((x) -> System.out.println(x));
    }
}