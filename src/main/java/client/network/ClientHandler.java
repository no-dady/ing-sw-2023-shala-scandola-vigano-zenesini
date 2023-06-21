package client.network;

import client.Client;
import client.UI;
import observer.Observer;
import server.model.Game;
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
    private State currState = State.WaitingForResponse;
    private Thread socketThread;
    private String nickName;

    public ClientHandler(String ip, int port) throws RemoteException, MalformedURLException, NotBoundException {
        super();
        serverInterface = (ServerInterface) Naming.lookup("rmi://localhost:1900" + "/myShelfie");

        initialize(serverInterface);
    }

    public ClientHandler(Client client, String ip, int port) throws RemoteException {
        super();
        ClientSocketMiddleware clientSocketMiddleware = new ClientSocketMiddleware(client, ip, port, this);
        this.serverInterface = clientSocketMiddleware;
        new Thread(clientSocketMiddleware).start();
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

    public void sendToServer(String string) throws RemoteException
    {
        currState = State.WaitingForResponse;
        serverInterface.send(string);
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
                    //System.out.println("Your nickname: ");
                    //String nickName = "";
                    //currState = State.WaitingForResponse;
                    //try{
                    //    BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
                    //    nickName = bufferRead.readLine();
                    //}
                    //catch(IOException e)
                    //{
                    //    e.printStackTrace();
                    //}
                    //this.nickName = nickName;
                    //NicknameMessage nickMessage = new NicknameMessage(nickName);
                    //System.out.println("Sending nickname");
                    //String messageParsed = Parser.toJson(nickMessage, NicknameMessage.class);
                    //System.out.println("Sending parsed message");
                    setState(State.setNick);
                    //serverInterface.send(Parser.toJson(nickMessage, NicknameMessage.class));
                    break;

                case 1:
                    setState(State.SetPlayersNum);
                    System.out.println("Setted playernUm");
                    //System.out.println("You are the admin of the Lobby!\nPlayer number for the new Lobby: ");
                    //currState = State.SetPlayersNum;
                    //int playerNumber = 1;
                    //try{
                    //    BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
                    //    String playerNumberString = bufferRead.readLine();
                    //    playerNumber = Integer.parseInt(playerNumberString);
                    //}
                    //catch(IOException e)
                    //{
                    //    e.printStackTrace();
                    //} catch (NumberFormatException ex){
                    //    ex.printStackTrace();
                    //}
                    //CreateLobbyMessage createLobbyMessage = new CreateLobbyMessage(this.nickName, playerNumber);
                    //serverInterface.send(Parser.toJson(createLobbyMessage, CreateLobbyMessage.class));
                    break;
            }
        } else if(string.contains("errorMessage"))//if (messageReceived instanceof ConfirmMessage trueMessageReceived)
        {
            ErrorMessage trueMessageReceived = Parser.fromJson(string, ErrorMessage.class);
            System.out.println(trueMessageReceived.getErrorMessage());
            if (trueMessageReceived.getErrorMessage().contains("Nickname"))
            {
                //System.out.println("Re-write your nickname: ");
                //String nickName = "";
                //try{
                //    BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
                //    nickName = bufferRead.readLine();
                //}
                //catch(IOException e)
                //{
                //    e.printStackTrace();
                //}
                //this.nickName = nickName;
                //NicknameMessage nickMessage = new NicknameMessage(nickName);
                //System.out.println("Sending nickname");
                //String messageParsed = Parser.toJson(nickMessage, NicknameMessage.class);
                //System.out.println("Sending parsed message");
                //serverInterface.send(Parser.toJson(nickMessage, NicknameMessage.class));
                setState(State.setNick);
            }
        } else
        {
            ConfirmMessage trueMessageReceived = Parser.fromJson(string, ConfirmMessage.class);
            System.out.println(trueMessageReceived.getMessage());
            setState(State.WaitingStart);
        }
    }

    @Override
    public UI getUI() throws RemoteException {
        return null;
    }

    @Override
    public Game getGame() throws RemoteException{
        return null;
    }

    @Override
    public void setGame(Game model) throws RemoteException{

    }

    public synchronized void setState(State state)
    {
        this.currState = state;
    }

    public synchronized State getCurrState() {
        return currState;
    }

    @Override
    public void addObserver(Observer<String> observer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addObserver'");
    }

    @Override
    public void notify(String message) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'notify'");
    }
}
