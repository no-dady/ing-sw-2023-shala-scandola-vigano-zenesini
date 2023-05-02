package server.controller.actions;

import server.model.Game;
import server.model.Tile;

import java.util.List;
import java.util.Scanner;

public class ColumnSelectAction implements Action{
    int selectedY;
    Scanner sc = new Scanner(System.in);
    @Override
    public void performAction(Game game) {
        while (!canPerformAction(game))
            game.getPlayers().get(game.getCurrPlayerId()).getBookshelf().setSlots(selectedY, game.getSelectedTiles());
        game.setSelectedTiles(null);
    }

    @Override
    public boolean canPerformAction(Game game) {
        selectedY = sc.nextInt();
        return game.getPlayers().get(game.getCurrPlayerId()).getBookshelf().getEmptyTilesColumn(selectedY) >= game.getSelectedTiles().size();
    }
    @Override
    public int getIdPlayer() {
        return 0;
    }
}
