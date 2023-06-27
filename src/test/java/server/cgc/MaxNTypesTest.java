package server.cgc;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.model.CommonGoalCardStrategy;
import server.model.Tile;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MaxNTypesTest {

    private CommonGoalCardStrategy testRow;
    private CommonGoalCardStrategy testCol;
    public List<Tile[][]> prova;
    @BeforeEach
    void setUp() {
        testRow = new MaxNTypes(false, 3, 4);
        testCol = new MaxNTypes(true, 3, 3);
        try {
            Reader reader = Files.newBufferedReader(Paths.get("src/test/resources/json/shelves/prova.json"));

            Gson gson = new GsonBuilder().serializeNulls().create();
            prova = gson.fromJson(reader, new TypeToken<List<Tile[][]>>(){}.getType());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    void conditionCheck() {
        Tile[][] shelf = prova.get(0);
        for(int i = 5; i >= 0; i--) {
            for(int j = 0; j < 5; j++) {
                System.out.print(shelf[i][j].getTileType() + "\t|\t");
            }
            System.out.println();
        }

        assertFalse(testRow.conditionCheck(shelf), "SUS");
        assertFalse(testCol.conditionCheck(shelf), "SAS");
    }
}