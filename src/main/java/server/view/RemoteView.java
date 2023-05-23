package server.view;

import network.Message;
import server.controller.actions.Action;
import server.model.Player;
import setup.Setup;

public class RemoteView extends View {

    /**
     * Private internal class that manages the new message received from the client.
     */
    private class MessageReceiver {


        public void update(String info) {
            System.out.println("Received: " + info);
            try{
                Action move= Starter.fromJson(info, Action.class);
                handleMove(move);
            }catch (NullPointerException e){
                Setup setupper= Starter.fromJson(info, Setup.class);
                SocketClientConnection connection= (SocketClientConnection)clientConnection;
                connection.handleSetupper(setupper);
            }
        }

    }

    private ClientConnection clientConnection;

    /**
     * Default constructor. It makes the ClientConnection observable for the internal private class.
     * @param player
     * @param c
     */
    public RemoteView(Player player, ClientConnection c) {
        super(player);
        this.clientConnection = c;
        c.addObserver(new MessageReceiver());
    }

    /**
     * Sets a new clientConnection. It makes the ClientConnection observable for the internal private class.
     * @param clientConnection
     */
    public void setClientConnection(ClientConnection clientConnection) {
        this.clientConnection = clientConnection;
        clientConnection.addObserver(new MessageReceiver());
    }

    /**
     * Sends a class Message to the client to updates its model. When the game is offline notifies a MoveAutoPlay
     * @param message
     */
    @Override
    public void update(Message message){
        if(!this.isOffline()){
            clientConnection.send(Starter.toJson(message, Message.class));
        }else{
            if(message instanceof LastMessage){
                notify(new MoveAutoPlay(getPlayer().getID()));
            }
        }
    }

}