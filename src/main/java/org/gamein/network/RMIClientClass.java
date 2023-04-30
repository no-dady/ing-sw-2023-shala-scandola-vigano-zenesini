package org.gamein.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.gamein.model.Tile;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.*;
import java.util.List;

public class RMIClientClass {

    public static void main(String arg[])
    {
        try
        {
            RMIServerInterface access = (RMIServerInterface)Naming.lookup("rmi://localhost:1900" + "/myShelfie");

            Reader reader = Files.newBufferedReader(Paths.get("src/test/resources/json/shelves/test_cgc_1_false.json"));
            Gson gson = new GsonBuilder().serializeNulls().create();
            List<Tile[][]> shelves = gson.fromJson(reader, new TypeToken<List<Tile[][]>>() {}.getType());
            Tile[][] shelf = shelves.get(0);

            access.sendShelf(shelf);
        }
        catch (Exception ea)
        {
            System.out.println("Client Error: " + ea);
        }
    }
}
