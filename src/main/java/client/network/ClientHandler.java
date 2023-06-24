package client.network;

import client.UI;
import observer.Observer;
import server.model.Game;
import server.network.ServerInterface;
import util.Messages.*;
import util.Parser;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import client.Client;

/**
 * The type Client.
 */
public class ClientHandler extends UnicastRemoteObject implements ClientInterface, Serializable {
    private ServerInterface serverInterface;
    private Client client;
    private List<String> playerInLobby;

    public void setClient(client.Client client) {
        this.client = client;
    }

    private Game game;
    private Thread socketThread;
    private String nickName;

    public ClientHandler(String ip, int port) throws RemoteException, MalformedURLException, NotBoundException {
        super();
        this.serverInterface = (ServerInterface) Naming.lookup("rmi://" + ip + ":" + port + "/myShelfie");
        this.playerInLobby = new ArrayList<String>();

        initialize(serverInterface);
    }

    public ClientHandler(client.Client client, String ip, int port) throws RemoteException {
        super();
        ClientSocketMiddleware clientSocketMiddleware = new ClientSocketMiddleware(client, ip, port, this);
        this.serverInterface = clientSocketMiddleware;
        this.playerInLobby = new ArrayList<String>();
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
        client.setState(State.WAITINGFORRESPONSE);
        serverInterface.send(string);
    }

    @Override
    public void send(String string) throws RemoteException
    {
        System.out.println("Ricevuto: " + string);
        Message msg = Parser.fromJson(string, Message.class);
        msg.handleMessage(client);
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
                    setState(State.SETTINGNICKNAME);
                    //serverInterface.send(Parser.toJson(nickMessage, NicknameMessage.class));
                    break;

                case 1:
                    setState(State.SETTINGPLAYERSNUMBER);
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
                setState(State.SETTINGNICKNAME);
            }
        } else if (string.contains("nicknameJoined"))
        {
            JoinedMessage trueMessageReceived = Parser.fromJson(string, JoinedMessage.class);
            playerInLobby.add(trueMessageReceived.getNicknameJoined());
            System.out.println("Ho aggiunto " + trueMessageReceived.getNicknameJoined());
        } else if (string.contains("stateToSend"))
        {
            StateMessage trueMessageReceived = Parser.fromJson(string, StateMessage.class);
            setState(trueMessageReceived.getState());
            System.out.println("State Arrived");
        } else
        {
            ConfirmMessage trueMessageReceived = Parser.fromJson(string, ConfirmMessage.class);
            switch(trueMessageReceived.getConfirmNumber())
            {
                case 0:
                case 1:
                    setState(State.WAITINGINLOBBY);
                    System.out.println("Waiting in lobby");
                    break;

                case 2:
                case 3:
                case 4:
                case 5:
                    System.out.println(trueMessageReceived.getMessage());
                    if (client.getState().equals(State.MYTURN) || client.getState().equals(State.WAITINGFORMYTURN)) client.getUI().update();
                    break;

            }
        }
    }

    public List<String> getPlayerInLobby() {
        return playerInLobby;
    }

    @Override
    public UI getUI() throws RemoteException {
        return null;
    }


    @Override
    public Game getGame() throws RemoteException{
        return this.game;

    }

    @Override
    public void setGame(Game model) throws RemoteException{
        this.game = model;
    }

    public synchronized void setState(State state) throws RemoteException
    {
        this.client.setState(state);
    }

    public synchronized State getState()throws RemoteException {
        return this.client.getState();
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
