package org.gamein;
import com.google.gson.Gson;
import org.gamein.controller.GameController;
import org.gamein.controller.PocketBuilder;
import org.gamein.model.PersonalGoalCard;
import org.gamein.model.Pocket;
import org.gamein.model.Tile;
import org.javatuples.Pair;

import java.util.ArrayList;

import static java.lang.System.*;

public class MainGame
{
    public static Gson serializer ;
    public static void main( String[] args )
    {
        serializer = new Gson();

        GameController gc = new GameController();
    }
}
