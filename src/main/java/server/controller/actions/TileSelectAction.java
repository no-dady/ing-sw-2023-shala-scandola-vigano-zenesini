package server.controller.actions;

import moves.MoveSelectTiles;
import server.model.Game;
import server.model.Tile;

import java.util.List;
import java.util.Scanner;

public class TileSelectAction extends MoveSelectTiles implements Action{
    int selectedX, selectedY;

    List<Tile>  selectedTiles;
    Scanner sc = new Scanner(System.in);

    public TileSelectAction(String nickName) {
        super(nickName);
    }

    @Override
    public void performAction(Game game) {
        boolean more;
        more = sc.nextLine().equals("yes");
        while (!more) {
            if (canPerformAction(game)) {
                selectedTiles.add(game.getBoard().getTile(selectedX, selectedY));
                game.getBoard().removeTile(selectedX, selectedY);
                more= sc.nextLine().equals("yes");
            }
            else {
                System.out.println("not a valid tile");
                more= sc.nextLine().equals("yes");
            }
        }
        game.setSelectedTiles(selectedTiles);
    }

    @Override
    public boolean canPerformAction(Game game) {
        selectedX = sc.nextInt();
        selectedY = sc.nextInt();
        return game.getBoard().getTile(selectedX, selectedY).isPickable();
    }

    @Override
    public String getNickName() {
        return "";
    }

}
