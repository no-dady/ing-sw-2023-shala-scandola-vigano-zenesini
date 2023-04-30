package server.controller.actions;

import server.model.Game;
import server.model.Tile;

import java.util.Scanner;

public class TileSelectAction implements Action{
    int selectedX, selectedY;
    Scanner sc = new Scanner(System.in);
    @Override
    public void performAction(Game game) {
        boolean more;
        more = sc.nextLine().equals("yes");
        while (!more) {
            if (canPerformAction(game)) {
                Tile selectedTile = game.getBoard().getTile(selectedX, selectedY);
                game.getBoard().removeTile(selectedX, selectedY);
                more= sc.nextLine().equals("yes");
            }
            else {
                System.out.println("not a valid tile");
            }

        }
    }

    @Override
    public boolean canPerformAction(Game game) {
        selectedX = sc.nextInt();
        selectedY = sc.nextInt();
        return game.getBoard().getTile(selectedX, selectedY).isPickable();
    }

    @Override
    public int getIdPlayer() {
        return 0;
    }
}
