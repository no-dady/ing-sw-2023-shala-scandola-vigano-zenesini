package server.view;

import client.network.ClientInterface;
import moves.MoveSelectTiles;
import util.Messages.LastMessage;
import util.Messages.Message;
import server.controller.actions.Action;
import server.controller.actions.TileSelectAction;
import server.model.Player;
import setup.Setup;
import util.Parser;

import java.io.IOException;

public class RemoteView extends View {

    /**
     * Private internal class that manages the new message received from the client.
     */
    private class MessageReceiver {


        public void update(String info) {
            System.out.println("Received: " + info);
            try{
                Action move= Parser.parseFromJson(info, Action.class);
                handleMove(move);
            }catch (NullPointerException e){
                Setup setupper= Starter.fromJson(info, Setup.class);
                SocketClientConnection connection= (SocketClientConnection)clientConnection;
                connection.handleSetupper(setupper);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private ClientInterface clientConnection;

    /**
     * Default constructor. It makes the ClientConnection observable for the internal private class.
     * @param player
     * @param c
     */
    public RemoteView(Player player, ClientInterface c) {
        super(player);
        this.clientConnection = c;
        c.addObserver(new MessageReceiver());
    }

    /**
     * Sets a new clientConnection. It makes the ClientConnection observable for the internal private class.
     * @param clientConnection
     */
    public void setClientConnection(ClientInterface clientConnection) {
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
            clientConnection.send(Parser.toJson(message, Message.class));
        }else{
            if(message instanceof LastMessage){
                notify(new TileSelectAction(new MoveSelectTiles("sos")));
            }
        }
    }

}