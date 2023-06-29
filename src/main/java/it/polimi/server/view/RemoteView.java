package it.polimi.server.view;

import it.polimi.client.network.ClientInterface;
import it.polimi.observer.Observer;
import it.polimi.util.Messages.Message;
import it.polimi.server.controller.actions.Action;
import it.polimi.server.model.Player;
import it.polimi.util.Parser;

import java.rmi.RemoteException;

public class RemoteView extends View {

    private class MessageReceiver implements Observer<String> {
        public void update(String info) {
            System.out.println("Received: " + info);
            try {
                Action move = Parser.fromJson(info, Action.class);
                handleMove(move);
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
        }
    }

}