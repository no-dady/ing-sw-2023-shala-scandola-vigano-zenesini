package server.view;

import client.network.ClientInterface;
import moves.MoveSelectTiles;
import observer.Observer;
import util.Messages.LastMessage;
import util.Messages.Message;
import server.controller.actions.Action;
import server.controller.actions.TileSelectAction;
import server.model.Player;
import util.Parser;

import java.rmi.RemoteException;

public class RemoteView extends View {

    private class MessageReceiver implements Observer<String> {
        public void update(String info) {
            System.out.println("Received: " + info);
            try {
                Action move = Parser.fromJson(info, Action.class);
                handleMove(move);
            // } catch (NullPointerException e){
            //   Setup setupper= Parser.fromJson(info, Setup.class);
            //   ClientSkeleton connection= (ClientSkeleton)clientConnection;
            //   connection.handleSetupper(setupper);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }

    private ClientInterface clientConnection;

    public RemoteView(Player player, ClientInterface c) throws RemoteException {
        super(player);
        this.clientConnection = c;
        c.addObserver(new MessageReceiver());
    }

    public void setClientConnection(ClientInterface clientConnection) throws RemoteException {
        this.clientConnection = clientConnection;
        clientConnection.addObserver(new MessageReceiver());
    }

    @Override
    public void update(Message message){
        if(!this.isOffline()){
            try {
                clientConnection.send(Parser.toJson(message, Message.class));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }else{
            if(message instanceof LastMessage){
                notify(new TileSelectAction(new MoveSelectTiles("sos")));
            }
        }
    }

}