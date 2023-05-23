package client.tui;

import client.UI;
import client.tui.tuiMoves.TUISelectColumn;
import client.tui.tuiMoves.TUISelectTiles;
import network.Client;
import network.Message;
import network.ConnectionType;
import network.RMIComm.ClientRMI;
import network.SocketComm.ClientSock;
import server.model.*;
import setup.ConfigsFromJson;
import setup.SetupAll;
import setup.SetupFirst;

import java.io.IOException;
import java.net.Socket;

import java.util.Arrays;
import java.util.Scanner;

import static server.controller.GameController.game;

public class TUI implements UI, Runnable {
    String boardArt = ConfigsFromJson.getBoardArt("src/main/resources/json/board_art.json");
    String bookshelfArt = ConfigsFromJson.getBookshelfArt("src/main/resources/json/bookshelf_art.json");
    Client client;

    public TUI(Client client) throws IOException {
        this.client = client;
    }

    @Override
    public void run() {
        String nickname;
        String connectionType;
        Socket socket;
        Scanner in = new Scanner(System.in);
        boolean valid = false;
        do {
            System.out.println("type hostname, press ENTER then type port and press ENTER");
            String host = in.nextLine();
            int port = in.nextInt();
            System.out.println("type RMI or SOCKET in order to choose your preferred connection method then press ENTER");
            connectionType = in.nextLine();
            if(Arrays.stream(ConnectionType.values()).map(ConnectionType::name).toList().contains(connectionType)){
                valid = true;}
            }while (!valid);


        if (client.getOnline()){
            System.out.println("Insert your nickname and press ENTER");
            nickname = in.nextLine();

            if (client.getIsFirst()) {
                int playerNumber;
                do {
                    System.out.println("Welcome, you are the first one to enter the hub, select the number of players in your game (2-3-4) and press ENTER");
                    playerNumber = in.nextInt();
                } while (playerNumber < 2 || playerNumber > 4);
                //setPlayerNumber(playerNumber);
                client.send(new SetupFirst(nickname,playerNumber));
            }
            else {
                System.out.println("Welcome to the hub, you'll be waiting for the other players to join");
                client.send(new SetupAll(nickname));
            }

        } else{
            System.out.println("we were unable to connect to the server, check your internet connection and try later");
            return;
        }

        while(client.isActive()){
            synchronized (locker){
                while(!isMoveHandled()) {
                    System.out.println("it's now your turn");
                    client.send(new TUISelectTiles(nickname).updateCLI(client.getGameModel,in));
                    client.send(new TUISelectColumn(nickname).updateCLI(client.getGameModel,in));
                    }
                }
                this.setMoveHandled(true);
                locker.notifyAll();
            }
        }


    @Override
    public void update() {}

    @Override
    public void setActive() {}

    @Override
    public void printConnectionMessage(Message message) {}

    @Override
    public void setConnectionType(ConnectionType type) {

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
        GameView game = this.client.getGame();
        int x = 0, y = 0;
        Tile[][] slots = game.getBoard().getSlots();
        for (int i = 0; i < boardArt.length(); i++) {
            if ( 'X' == boardArt.charAt(i)) {
                String PRESET = "\033[";
                String BLACK_BOLD = "1;30m";
                String color = "0;100m";
                String RESET = "0m";
                if (slots[x][y].isPickable()) color = TileType.getTileMap().get(slots[x][y].getTileType()).color;
                System.out.print( PRESET + color + PRESET + BLACK_BOLD + TileType.getTileMap().get(slots[x][y].getTileType()).sign + PRESET + RESET );
                y++;
            }
            else{
                System.out.print(boardArt.charAt(i));
            }
            if (y == slots[0].length && '\n' == boardArt.charAt(i)) {
                x++;
                y = 0;
            }
        }
        slots = game.getPlayers().get(game.getCurrPlayerId()).getBookshelf().getSlots();
        x = 0;
        y = 0;
        for (int i = 0; i < bookshelfArt.length(); i++) {
            if ( 'X' == bookshelfArt.charAt(i)) {
                String PRESET = "\033[";
                String BLACK_BOLD = "1;30m";
                String RESET = "0m";
                String color = TileType.getTileMap().get(slots[x][y].getTileType()).color;
                System.out.print( PRESET + color + PRESET + BLACK_BOLD + TileType.getTileMap().get(slots[x][y].getTileType()).sign + PRESET + RESET );
                y++;
            }
            else{
                System.out.print(bookshelfArt.charAt(i));
            }
            if (y == slots[0].length && '\n' == bookshelfArt.charAt(i)) {
                x++;
                y = 0;
            }
        }
    }



}
