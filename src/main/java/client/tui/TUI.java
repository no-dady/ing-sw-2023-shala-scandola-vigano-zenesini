package client.tui;

import client.UI;
import client.tui.tuiMoves.TUISelectColumn;
import client.tui.tuiMoves.TUISelectTiles;
import client.Client;
import util.Messages.Message;
import server.model.*;
import setup.ConfigsFromJson;
import setup.SetupAll;
import setup.SetupFirst;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class TUI implements UI, Runnable {
    boolean moveHandled;
    final String bookshelfArt = ConfigsFromJson.getBookshelfArt("src/main/resources/json/bookshelf_art.json");
    final String boardAndBookshelfArt  = ConfigsFromJson.getBoardAndBookshelfArt("src/main/resources/json/board_&_bookshelf_art.json");
    final Client client;

    public TUI(Client client) throws IOException {
        this.client = client;
    }

    @Override
    public void run() {
        String nickname;
        String connectionType;
        String host;
        int port;
        Scanner in = new Scanner(System.in);
        boolean valid = false;
        System.out.println("""
                       \s
                ███╗   ███╗██╗   ██╗    ███████╗██╗  ██╗███████╗██╗     ███████╗██╗███████╗
                ████╗ ████║╚██╗ ██╔╝    ██╔════╝██║  ██║██╔════╝██║     ██╔════╝██║██╔════╝
                ██╔████╔██║ ╚████╔╝     ███████╗███████║█████╗  ██║     █████╗  ██║█████╗ \s
                ██║╚██╔╝██║  ╚██╔╝      ╚════██║██╔══██║██╔══╝  ██║     ██╔══╝  ██║██╔══╝ \s
                ██║ ╚═╝ ██║   ██║       ███████║██║  ██║███████╗███████╗██║     ██║███████╗
                ╚═╝     ╚═╝   ╚═╝       ╚══════╝╚═╝  ╚═╝╚══════╝╚══════╝╚═╝     ╚═╝╚══════╝
                """);
        System.out.println();
        System.out.println();
        System.out.println();

        try {
            System.out.println("[type hostname, press ENTER]");
            host = in.nextLine();
            if (!host.matches("\\b\\d{1,3}(?:\\.\\d{1,3}){3}\\b")){
                throw (new Exception()) ;
            }
        }catch( Exception e){
            System.out.println("no");
            return;
        }

        try {
            System.out.println("[type port and press ENTER]");
            port = Integer.valueOf(in.nextLine());
        }catch( Exception e){
            System.out.println("no");
            return;
        }

        try {
            System.out.println("[type RMI or SOCKET in order to choose your preferred connection method then press ENTER]");
            connectionType = in.nextLine();
            if (!(connectionType.equals("RMI") || connectionType.equals("SOCKET"))){
                throw (new Exception()) ;
            }
        }catch( Exception e){
            System.out.println("no");
            return;
        }
        client.setConnection(host, port, connectionType);
        try {
            client.setOnline();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (client.isOnline()){
            System.out.println("[Insert your nickname and press ENTER]");
            nickname = in.nextLine();

            if (client.getIsFirst()) {
                String playerNumber;
                do {
                    System.out.println("[Welcome, you are the first one to enter the hub, select the number of players in your game (2-3-4) and press ENTER]");
                    playerNumber = in.nextLine();
                } while (Integer.valueOf(playerNumber) < 2 || Integer.valueOf(playerNumber) > 4);
                //client.send(new SetupFirst(playerNumber));
            }
            else {
                System.out.println("[Welcome to the hub, you'll be waiting for the other players to join]");
                //client.send(new SetupAll(nickname));
            }
        } else{
            System.out.println("[we were unable to connect to the server, check your internet connection and try later]");
            return;
        }

        while(client.isActive() && !this.client.getGame().hasWinner()){
            Object locker = new Object();
            synchronized (locker){
                while(!isMoveHandled()) {
                    System.out.println("[it's now your turn]");
                    printState();
                   // client.send(new TUISelectTiles(nickname).updateCLI(client.getGame(),in));
                   // client.send(new TUISelectColumn(nickname).updateCLI(client.getGame(),in));
                    this.setMoveHandled(true);
                }
            }
            this.setMoveHandled(false);
            locker.notifyAll();
        }

    }

    private boolean isMoveHandled() {
        return this.moveHandled;
    }

    private void setMoveHandled(boolean b) {
        this.moveHandled = b;
    }


    @Override
    public void update() {}

    @Override
    public void setActive() {}

    @Override
    public void printConnectionMessage(Message message) {

    }

    @Override
    public void setNickname(String nickname) {

    }

    @Override
    public void setPlayerNumber(int playerNumber) {

    }

    @Override
    public void setSelectedTiles(String selectedTiles) {

    }

    @Override
    public void setSelectedColum(int selectedColum) {

    }

    @Override
    public void getInfoAboutOtherPlayers(String playerNickname) {

    }

    public void printState(){
        Game game = this.client.getGame();
        int x = 0, y = 0;
        Tile[][] slots = game.getBoard().getSlots();
        Tile[][] places = game.getPlayers().get(game.getCurrPlayerId()).getBookshelf().getSlots();
        for (int i = 0; i < boardAndBookshelfArt.length(); i++) {
            if ('X' == boardAndBookshelfArt.charAt(i)) {
                String PRESET = "\033[";
                String BLACK_BOLD = "1;30m";
                String color = "0;100m";
                String RESET = "0m";
                if (y < slots[0].length) {
                    if (slots[x][y].isPickable()) color = TileType.getTileMap().get(slots[x][y].getTileType()).color;
                    System.out.print(PRESET + color + PRESET + BLACK_BOLD + TileType.getTileMap().get(slots[x][y].getTileType()).sign + PRESET + RESET);
                } else{
                    System.out.print(slots.length + " " +  places.length + " " +places[0].length +" " +x+ " " + y);
                    color = TileType.getTileMap().get(places[x - (slots.length - places.length) ][y - slots[0].length].getTileType()).color;
                    System.out.print(PRESET + color + PRESET + BLACK_BOLD + TileType.getTileMap().get(places[x - (slots.length - places.length) ][y - slots[0].length].getTileType()).sign + PRESET + RESET);
                }
                y++;
            } else {
                System.out.print(boardAndBookshelfArt.charAt(i));
            }
            if ('\n' == boardAndBookshelfArt.charAt(i) && (y == slots[0].length + 5 || y == slots[0].length)) {
                x++;
                y = 0;
            }
        }
    }
}
