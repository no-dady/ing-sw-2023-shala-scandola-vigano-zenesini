package server.controller.actions;

import moves.MoveSelectColum;
import server.model.Game;

import java.util.Scanner;

public class ColumnSelectAction extends MoveSelectColum implements Action{
    int selectedY;
    final Scanner sc = new Scanner(System.in);

    public ColumnSelectAction(String nickName) {
        super(nickName);
    }

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
    public String getNickName() {
        return "";
    }

    @Override
    public boolean canPerform(Game game) {
        return true;
    }
}
