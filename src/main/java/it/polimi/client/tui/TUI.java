package it.polimi.client.tui;

import it.polimi.client.UI;
import it.polimi.client.tui.tuiMoves.TUISelectColumn;
import it.polimi.client.tui.tuiMoves.TUISelectTiles;
import it.polimi.client.Client;
import it.polimi.client.tui.tuiMoves.TUISetupAll;
import it.polimi.client.tui.tuiMoves.TUISetupFirst;
import it.polimi.moves.Move;
import it.polimi.server.model.*;
import it.polimi.setup.ConfigsFromJson;
import it.polimi.setup.SetupAll;
import it.polimi.setup.SetupFirst;
import it.polimi.util.Parser;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;

public class TUI implements UI, Runnable {
    private String cgcs;
    boolean moveHandled;
    String boardAndBookshelfArt;
    String otherPlayersBookshelfArt;
    final Client client;
    String PRESET = "\033[";
    String BLACK_BOLD = "1;30m";
    String COLOR = "0;100m";
    String RESET = "0m";

    public TUI(Client client){
        this.client = client;
    }
    private String nickname;
    Scanner in = new Scanner(System.in);

    @Override
    public void run() {
        String connectionType;
        String host;
        int port;
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

        System.out.println("[type hostname, press ENTER]");
        while(true)
        {
            host = in.nextLine();
            if (!host.matches("\\b\\d{1,3}(?:\\.\\d{1,3}){3}\\b")) {
                System.out.println("[this is not a valid address, please re-type the address and press ENTER]");
            } else {
                break;
            }
        }

        try {
            System.out.println("[type port and press ENTER]");
            port = Integer.parseInt(in.nextLine());
        } catch (Exception e) {
            System.out.println("this is not a valid port number");
            return;
        }

        try {
            System.out.println("[type RMI or SOCKET in order to choose your preferred connection method then press ENTER]");
            connectionType = in.nextLine();
            connectionType = connectionType.toUpperCase();
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

        while (client.isOnline() && client.isActive())
        {
            if (client.getStateChanged())
            {
                update();
            }
        }
        
    }

    @Override
    public void update() {
        client.setStateChanged(false);
        switch (client.getState()) {
            case SETUP -> {
                SetupAll setup = new TUISetupAll().create(in, true);
                setNickname(setup.getParameter());
                try {
                    client.getClientConnection().getServerInterface().sendSetupAll(Parser.toJson(setup, SetupAll.class));
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            }
            case SETUPAGAIN -> {
                SetupAll setup = new TUISetupAll().create(in, false);
                setNickname(setup.getParameter());
                try {
                    client.getClientConnection().getServerInterface().sendSetupAll(Parser.toJson(setup, SetupAll.class));
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            }
            case INQUEUE -> {
                System.out.println("[Waiting in queue to join a lobby]");
            }
            case MYTURN -> {
                if (cgcs == null){
                    try {
                        cgcs = concatCGCarts(ConfigsFromJson.getArt(Parser.getResourcePath("json/cgcArts/" + client.getGame().getBoard().getCommonGoalCards().get(0).getName() + ".json")), ConfigsFromJson.getArt(Parser.getResourcePath("json/cgcArts/" +client.getGame().getBoard().getCommonGoalCards().get(1).getName() + ".json")));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                if(boardAndBookshelfArt == null){
                    try {
                        boardAndBookshelfArt = ConfigsFromJson.getBoardAndBookshelfArt(Parser.getResourcePath("json/board_bookshelf_pgc_art.json"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    boardAndBookshelfArt = setPGCart(boardAndBookshelfArt);
                }
                System.out.println("[it's now your turn]");
                try {
                    printState();
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
                try {
                    client.getClientConnection().getServerInterface().sendAction(Parser.toJson(new TUISelectTiles(nickname, client.getLobbyId()).updateCLI(client.getGame(), in), Move.class));
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
                try {
                    client.getClientConnection().getServerInterface().sendAction(Parser.toJson(new TUISelectColumn(nickname, client.getLobbyId()).updateCLI(client.getGame(), in), Move.class));
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            }
            case GAMEENDED -> {
                printEndGame();
                System.exit(0);
            }
            case SETUPFIRST -> {
                SetupFirst setup = new TUISetupFirst().create(in, true);
                setNickname(setup.getParameter());
                try {
                    client.getClientConnection().getServerInterface().sendSetupFirst(Parser.toJson(setup, SetupFirst.class));
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            }
            case WAITINGINLOBBY -> {
                System.out.println("[Welcome to the lobby, you'll be waiting for the other players to join]");
                //maybe anche qui aggiungiamo la feature della lista incrementale man mano che la gente entra
            }
            case WAITINGFORMYTURN -> {
                if(otherPlayersBookshelfArt == null) {
                    try {
                        otherPlayersBookshelfArt = createStateOthers();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                printStateOthers();
            }
        }
    }

    private void printStateOthers() {

        System.out.println("[Waiting for other players to finish their turn, here are their current bookshelves]");
        ArrayList<Player> otherPlayersList = new ArrayList<>();
        for (Player p: client.getGame().getPlayers()) {
            if (!p.getUserName().equals(nickname)) {
                otherPlayersList.add(p);
            }
        }
        int playerIndex = - 1;
        int tabCounter = 0;
        int x = 6;
        int y = 0;
        for (int i = 0; i< otherPlayersBookshelfArt.length(); i++) {
            if('X' == otherPlayersBookshelfArt.charAt(i)){
                String color = COLOR;
                Tile[][] slots = otherPlayersList.get(playerIndex).getBookshelf().getSlots();
                if (!(slots[x][y] == null) && !slots[x][y].Empty()){
                    color = TileType.getTileMap().get(slots[x][y].getTileType()).color;
                }
                else {
                    color = RESET;
                }
                System.out.print(PRESET + color + "   "+ PRESET + RESET);
                y++;
            } else {
            System.out.print(otherPlayersBookshelfArt.charAt(i));
            }
            if('\t' == otherPlayersBookshelfArt.charAt(i) && tabCounter < 4){
                tabCounter++;
            }
            if('\t' == otherPlayersBookshelfArt.charAt(i) && tabCounter == 4){
                playerIndex = playerIndex + 1;
                tabCounter = 0;
                y = 0;
            }
            if ('\n' == otherPlayersBookshelfArt.charAt(i)){
                playerIndex = -1;
                if('║' != otherPlayersBookshelfArt.charAt(i-1)) x--;
                y = 0;
            }
        }
    }

    private String createStateOthers() throws IOException {
        String bookshelves = "";
        ArrayList<String[]> artlist = new ArrayList<>();
        for (Player p : client.getGame().getPlayers()) {
            if (!p.getUserName().equals(nickname))
                artlist.add(ConfigsFromJson.getBookshelfArt(Parser.getResourcePath("json/bookshelf_art.json")).split("\n"));
        }
        for(int j = 0; j< ConfigsFromJson.getBookshelfArt(Parser.getResourcePath("json/bookshelf_art.json")).split("\n").length; j++){
        for (int i = 0; i< client.getGame().getPlayers().size() - 1 ; i++) {
                bookshelves += ("\t\t\t\t" + artlist.get(i)[j]);
        }
        bookshelves += "\n";
        }
        return bookshelves;
    }

    private void printEndGame() {
        int maxPoint = 0;
        for (Player p : client.getGame().getPlayers()) {
            System.out.println("[" + p.getUserName() + " has " + p.getScore() + " points]");
            if (maxPoint < p.getScore())
            {
                maxPoint = p.getScore();
            }
        }
        System.out.print("[");
        boolean found = false;
        for (Player p : client.getGame().getPlayers())
        {
            if (p.getScore() == maxPoint)
            {
                if (!found)
                {
                    System.out.print(p.getUserName() + " ");
                    found = true;
                } else {
                    System.out.print("and " + p.getUserName() + " ");
                }
            }
        }
        System.out.print("won the game!]");
    }

    @Override
    public void printServerMessage(String message) {
        System.out.println("[" + message + "]");
    }

    private void setNickname(String nickname){
        this.nickname = nickname;
    }
    private String concatCGCarts(String cgc1, String cgc2){
        String cgc1And2 = "";

        String[] cgc1Array = cgc1.split("\n");
        String[] cgc2Array = cgc2.split("\n");
        for (int i = 0; i< cgc1Array.length; i++) {
            cgc1And2  += ("\t\t\t\t" + cgc1Array[i] + "\t\t\t\t" + cgc2Array[i] + "\n");

        }
        return cgc1And2;
    }

    private String setPGCart(String boardAndBookshelfArt){
        PersonalGoalCard pgc = null;
        String art = boardAndBookshelfArt;
        int x = Bookshelf.getRows()+10, y = 0;
        Game game = this.client.getGame();
        for (Player player: game.getPlayers()) {
            if (player.getUserName().equals(nickname)){
                 pgc = player.getPersonalGoalCard();
        }

        }
        for (int i = 0; i< art.length(); i++) {
            if (art.charAt(i) == 'x') {
                String color = COLOR;
                int found = 0;
                for (int j = 0; j<TileType.getTileMap().values().size()-1; j++) {
                    assert pgc != null;
                    if (pgc.getCoordinates(TileType.getTileMap().keySet().stream().toList().get(j)).y()==y & pgc.getCoordinates(TileType.getTileMap().keySet().stream().toList().get(j)).x()==x){
                        String firstHalf = art.substring(0,i);
                        String secondHalf = art.substring(i+1);
                        art = firstHalf + PRESET + TileType.getTileMap().get(TileType.getTileMap().keySet().stream().toList().get(j)).color + "  " + PRESET + RESET + secondHalf;
                        found = 1;
                    }

                }
                if (found == 0){
                    String firstHalf = art.substring(0,i);
                    String secondHalf = art.substring(i+1);
                    art = firstHalf + "  " + secondHalf;
                }
                y++;
            } else if (art.charAt(i) == '\n') {
                x--;
                y = 0;

            }
        }
        return art;
    }

    public void printState() throws RemoteException {
        Game game = this.client.getGame();
        int x = 0, y = 0;
        Tile[][] slots = game.getBoard().getSlots();
        Tile[][] places = null;
        for (Player player: game.getPlayers()
        ) {
            if (player.getUserName().equals(nickname)) {
                places = player.getBookshelf().getSlots();
            }
        }
        for (int i = 0; i < boardAndBookshelfArt.length(); i++) {
            if ('X' == boardAndBookshelfArt.charAt(i)) {
                String color = COLOR;
                if (y < slots[0].length) {
                    if (slots[x][y].isPickable()) { color = TileType.getTileMap().get(slots[x][y].getTileType()).color;}
                    if (slots[x][y].Empty()) color = RESET;
                    System.out.print(PRESET + color + PRESET + BLACK_BOLD + TileType.getTileMap().get(slots[x][y].getTileType()).sign + PRESET + RESET);
                } else {
                    if (places[Bookshelf.getRows() - (x - (slots.length - 1 - places.length - 1) - 2) - 1 ][y - slots[0].length] != null) {
                        color = TileType.getTileMap().get(places[Bookshelf.getRows() - (x - (slots.length - 1 - places.length - 1) - 2) - 1][y - slots[0].length].getTileType()).color;
                        System.out.print(PRESET + color + PRESET + BLACK_BOLD + TileType.getTileMap().get(places[Bookshelf.getRows() - (x - (slots.length - 1 - places.length - 1) - 2) - 1 ][y - slots[0].length].getTileType()).sign + PRESET + RESET);
                    }
                    else {
                        System.out.print(PRESET + RESET + PRESET + BLACK_BOLD + "   " + PRESET + RESET);
                    }
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
        System.out.println("\n" + cgcs);
    }
}
