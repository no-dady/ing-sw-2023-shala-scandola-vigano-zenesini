package it.polimi.server.cgc;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import it.polimi.util.Parser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.polimi.server.model.CommonGoalCardStrategy;
import it.polimi.server.model.Tile;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class MaxNTypesTest {

    private CommonGoalCardStrategy testRow;
    private CommonGoalCardStrategy testCol;
    public List<Tile[][]> testShelfList;
    @BeforeEach
    void setUp() {
        testRow = new MaxNTypes(false, 3, 4, "");
        testCol = new MaxNTypes(true, 3, 3, "");
        try {
            assertEquals("D:\\Github\\ing-sw-2023-shala-scandola-vigano-zenesini\\target\\test-classes\\json\\shelves\\bookshelf_test.json", Parser.getResourcePath("json/shelves/bookshelf_test.json"));
            BufferedReader reader = Files.newBufferedReader(Paths.get(Parser.getResourcePath("json\\shelves\\bookshelf_test.json")));
            String json = reader.lines().collect(Collectors.joining());

            Gson gson = new GsonBuilder().serializeNulls().create();
            testShelfList = Parser.fromJson(json, new TypeToken<List<Tile[][]>>(){}.getType());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    void conditionCheck() {
        Tile[][] shelf = testShelfList.get(0);
        for(int i = 5; i >= 0; i--) {
            for(int j = 0; j < 5; j++) {
                System.out.print(shelf[i][j].getTileType() + "\t|\t");
            }
            System.out.println();
        }

        assertFalse(testRow.conditionCheck(shelf));
        assertFalse(testCol.conditionCheck(shelf));
    }
}