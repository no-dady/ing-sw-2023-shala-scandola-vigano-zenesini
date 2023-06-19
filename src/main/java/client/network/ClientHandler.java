package client.network;

import client.Client;
import server.network.ServerInterface;
import util.Messages.*;
import util.Parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * The type Client.
 */
public class ClientHandler extends UnicastRemoteObject implements ClientInterface, Serializable {
    private ServerInterface serverInterface;
    private Thread socketThread;
    private String nickName;
    public ClientHandler(String ip, int port) throws RemoteException, MalformedURLException, NotBoundException {
        super();
        serverInterface = (ServerInterface) Naming.lookup("rmi://localhost:1900" + "/myShelfie");

        initialize(serverInterface);
    }

    public ClientHandler(Client client, String ip, int port) throws RemoteException {
        super();
        this.serverInterface = new ClientSocketMiddleware(client, ip, port, this);
    }

    /**
     * Initialize.
     *
     * @param serverInterface the server
     * @throws RemoteException the remote exception
     */
    public void initialize(ServerInterface serverInterface) throws RemoteException
    {
        if (serverInterface instanceof ClientSocketMiddleware) {
            socketThread = new Thread((ClientSocketMiddleware) serverInterface);
            socketThread.start();
        } else {
            serverInterface.register(this);
        }
    }

    public ServerInterface getServerInterface()
    {
        return this.serverInterface;
    }


    @Override
    public void send(String string) throws RemoteException
    {
        System.out.println("Ricevuto: " + string);
        //Message messageReceived = Parser.parseFromJsonString(string, AskMoveMessage.class);
        //if(messageReceived instanceof AskMoveMessage trueMessageReceived)
        if (string.contains("moveTypeNumber"))
        {
            AskMoveMessage trueMessageReceived = Parser.fromJson(string, AskMoveMessage.class);
            switch(trueMessageReceived.getMoveTypeNumber())
            {
                case 0:
                    System.out.println("Your nickname: ");
                    String nickName = "";
                    try{
                        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
                        nickName = bufferRead.readLine();
                    }
                    catch(IOException e)
                    {
                        e.printStackTrace();
                    }
                    this.nickName = nickName;
                    NicknameMessage nickMessage = new NicknameMessage(nickName);
                    System.out.println("Sending nickname");
                    String messageParsed = Parser.toJson(nickMessage, NicknameMessage.class);
                    System.out.println("Sending parsed message");
                    serverInterface.send(Parser.toJson(nickMessage, NicknameMessage.class));
                    break;

                case 1:
                    System.out.println("You are the admin of the Lobby!\nPlayer number for the new Lobby: ");
                    int playerNumber = 1;
                    try{
                        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
                        String playerNumberString = bufferRead.readLine();
                        playerNumber = Integer.parseInt(playerNumberString);
                    }
                    catch(IOException e)
                    {
                        e.printStackTrace();
                    } catch (NumberFormatException ex){
                        ex.printStackTrace();
                    }
                    CreateLobbyMessage createLobbyMessage = new CreateLobbyMessage(this.nickName, playerNumber);
                    serverInterface.send(Parser.toJson(createLobbyMessage, CreateLobbyMessage.class));
                    break;
            }
        } else if(string.contains("errorMessage"))//if (messageReceived instanceof ConfirmMessage trueMessageReceived)
        {
            ErrorMessage trueMessageReceived = Parser.fromJson(string, ErrorMessage.class);
            System.out.println(trueMessageReceived.getErrorMessage());
            if (trueMessageReceived.getErrorMessage().contains("Nickname"))
            {
                System.out.println("Re-write your nickname: ");
                String nickName = "";
                try{
                    BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
                    nickName = bufferRead.readLine();
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
                this.nickName = nickName;
                NicknameMessage nickMessage = new NicknameMessage(nickName);
                System.out.println("Sending nickname");
                String messageParsed = Parser.toJson(nickMessage, NicknameMessage.class);
                System.out.println("Sending parsed message");
                serverInterface.send(Parser.toJson(nickMessage, NicknameMessage.class));
            }
        } else
        {
            ConfirmMessage trueMessageReceived = Parser.fromJson(string, ConfirmMessage.class);
            System.out.println(trueMessageReceived.getMessage());
        }
    }

    @Override
    public UI getUI() {
        return null;
    }

    @Override
    public Game getGame() {
        return null;
    }

    @Override
    public void setGame(Game model) {

    }
}
