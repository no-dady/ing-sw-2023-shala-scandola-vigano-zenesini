package client;

import server.model.Tile;
import server.model.TileType;

public class Temp {
    private UI gui = null;
    private UI tui = null;
    public String boardArt = "";
    public String bookshelfArt = "";
    public void printArt(Tile[][] slots, String art) {
        int x = 0, y = 0;
        for (int i = 0; i < art.length(); i++) {
            if ( 'X' == art.charAt(i)) {
                String PRESET = "\033[";
                String BLACK_BOLD = "\033[1;30m";
                System.out.print( PRESET + TileType.getTileMap().get(slots[x][y].getTileType()).color + BLACK_BOLD + TileType.getTileMap().get(slots[x][y].getTileType()).sign + ConsoleColors.RESET );
                y++;
            }
            else{
                System.out.print(art.charAt(i));
            }
            if (y == slots[0].length && '\n' == art.charAt(i)) {
                x++;
                y = 0;
            }
        }
    }

    public UI getUI() {
        return tui;
    }


}
