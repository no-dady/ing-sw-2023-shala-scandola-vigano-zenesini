package client.tui;

import client.UI;
import client.tui.tuiMoves.TUISelectColumn;
import client.tui.tuiMoves.TUISelectTiles;
import client.network.State;
import client.Client;
import util.Messages.CreateLobbyMessage;
import util.Messages.Message;
import server.model.*;
import setup.ConfigsFromJson;
import setup.SetupAll;
import setup.SetupFirst;
import util.Messages.NicknameMessage;
import util.Parser;

import java.io.IOException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Scanner;

public class TUI implements UI, Runnable {
    boolean moveHandled;
    final String bookshelfArt = ConfigsFromJson.getBookshelfArt("src/main/resources/json/bookshelf_art.json");
    final String boardAndBookshelfArt = ConfigsFromJson.getBoardAndBookshelfArt("src/main/resources/json/board_&_bookshelf_art.json");
    final Client client;

    public TUI(Client client) throws IOException {
        this.client = client;
    }

    @Override
    public void run() {
        String nickname = "";
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
            if (!host.matches("\\b\\d{1,3}(?:\\.\\d{1,3}){3}\\b")) {
                throw (new Exception());
            }
        } catch (Exception e) {
            System.out.println("this is not a valid hostname");
            return;
        }

        try {
            System.out.println("[type port and press ENTER]");
            port = Integer.valueOf(in.nextLine());
        } catch (Exception e) {
            System.out.println("this is not a valid port number");
            return;
        }

        try {
            System.out.println("[type RMI or SOCKET in order to choose your preferred connection method then press ENTER]");
            connectionType = in.nextLine();
            if (!(connectionType.equals("RMI") || connectionType.equals("SOCKET"))) {
                throw (new Exception());
            }
        } catch (Exception e) {
            System.out.println("this is not a valid connection method");
            return;
        }
        client.setConnection(host, port, connectionType);
        try {
            client.setOnline();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (client.isOnline()) {
            try {
                while (!client.getState().equals(State.setNick)) {
                    System.out.println("not yet");
                }
                ;
                System.out.println("[Insert your nickname and press ENTER]");
                do {
                    nickname = in.nextLine();
                    NicknameMessage nickMessage = new NicknameMessage(nickname);
                    String messageParsed = Parser.toJson(nickMessage, NicknameMessage.class);
                    client.sendToServer(messageParsed);
                    while (client.getState().equals(State.WaitingForResponse)) {
                    }
                    if (client.getState().equals(State.setNick)) {
                        System.out.println("[" + nickname + " was already taken, please insert another nickname and press ENTER]");
                    }
                } while (!client.getState().equals(State.WaitingStart) && !client.getState().equals(State.SetPlayersNum));
            } catch (Exception e) {
                System.out.println(client.getState());//client.receivedMessage);
            }
            while (client.getState().equals(State.WaitingForResponse)) {
                System.out.println(client.getState().toString());
            }
            ;
            if (client.getState().equals(State.SetPlayersNum)) {
                String playerNumberString;
                int playerNumber = 2;
                do {
                    System.out.println("[Welcome, you are the first one to enter the lobby, select the number of players in your game (2-3-4) and press ENTER]");
                    playerNumberString = in.nextLine();
                    playerNumber = Integer.parseInt(playerNumberString);
                } while (playerNumber < 2 || playerNumber > 4);
                CreateLobbyMessage createLobbyMessage = new CreateLobbyMessage(nickname, playerNumber);
                String messageParsed = Parser.toJson(createLobbyMessage, CreateLobbyMessage.class);
                try
                {
                    client.sendToServer(messageParsed);
                } catch (RemoteException e) {
                    System.out.println("Cannot send createLobby Message");
                }
            } else {
                System.out.println("[Welcome to the lobby, you'll be waiting for the other players to join]");
            }
        } else {
            System.out.println("[we were unable to connect to the server, check your internet connection and try later]");
            return;
        }
        System.out.println("0");
        while (client.isActive() && client.isOnline()){
                try {
                    printState();
                }
                catch (NullPointerException e){}
        //COMMENTED THIS LINE vvv ONLY BECAUSE RIGHT NOW WE DONT HAVE A REAL GAME INSIDE THE CLIENT SO getGame LEAD TO A NULLPOINTEREXC
        //while (client.isActive() && client.isOnline() && !this.client.getGame().hasWinner());
        while (client.getState().equals(State.NotMyTurn)) {
            //whatever you want to do whn you are not actively playing
        }
        if (client.getState().equals(State.MyTurn)) {
            Object locker = new Object();
            synchronized (locker) {
                while (!isMoveHandled()) {
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
