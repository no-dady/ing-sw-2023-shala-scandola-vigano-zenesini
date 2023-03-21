package org.gamein.controller;

import org.gamein.model.*;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

public class GameController {
    ArrayList<Player> players = null;
    GameState gameState;
    Board board;
    Pocket pocket;

    void createLobby(int playerNumber) throws IllegalPlayersNumberException {
        PersonalGoalCard personalGoalCard;
        PocketBuilder builder = new PocketBuilder();
        pocket = new Pocket(builder.createTileListPocket(132));
        for (int i = 0; i < playerNumber; i++) {
            personalGoalCard.select();
            players.add(new Player(i, "player" + i, new Bookshelf(), personalGoalCard));
        }

        switch (playerNumber) {
            case 2:
                board = BoardBuilder2();
            case 3:
                board = BoardBuilder3();
            case 4:
                board = BoardBuilder4();
            default:
                throw new IllegalPlayersNumberException("wait, you are doing something wrong");
        }
        gameState = new GameState(players, board, pocket);
    }
    void start(List<String> usernames) {
            for (int i = 0; i < playerNumber; i++){
                players.get(i).setUserName(usernames.get(i));
            }
        }

    }

}
