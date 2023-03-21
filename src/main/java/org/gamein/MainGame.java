package org.gamein;

import org.gamein.controller.PocketBuilder;
import org.gamein.model.Pocket;
import org.gamein.model.Tile;

import java.util.ArrayList;

import static java.lang.System.*;

public class MainGame
{
    public static void main( String[] args )
    {
        PocketBuilder builder = new PocketBuilder();
        ArrayList<Tile> ret = builder.createTileListPocket(132);
        for (Tile ts : ret)
        {
            out.println(ts.getTileType().toString() + " " + ts.getTileId().orElse(999999));
        }
    }
}
