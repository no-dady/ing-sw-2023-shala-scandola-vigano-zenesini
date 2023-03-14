package org.gamein;

import org.gamein.controller.TileBuilder;
import org.gamein.model.Tile;

import java.util.ArrayList;

import static java.lang.System.*;

public class MainGame
{
    public static void main( String[] args )
    {
        TileBuilder builder = new TileBuilder();
        ArrayList<Tile> ret = builder.createTileListPocket(132);
        for (Tile ts : ret)
        {
            out.println(ts.getTileType().toString() + " " + ts.getTileId().orElse(999999));
        }
    }
}
