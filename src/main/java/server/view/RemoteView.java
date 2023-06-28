package server.view;

import client.network.ClientInterface;
import observer.Observer;
import server.network.ServerInterface;
import server.network.SocketComm.ClientSkeleton;
import setup.Setup;
import util.Messages.Message;
import server.controller.actions.Action;
import server.model.Player;
import util.Parser;

import java.io.Serializable;
import java.rmi.RemoteException;

public class RemoteView extends View implements Serializable {

private class MessageReceiver implements Observer<String>, Serializable {



    public MessageReceiver(){
        System.out.println("MessageReceiver created");
    }
        public void update(String info) {
            System.out.println("Received: " + info);
            try {
                Action move = Parser.fromJson(info, Action.class);
                handleMove(move);
            } catch (Exception e) {
                System.out.println("Error MessageReceiver");
            }
        }
}
    private ClientInterface clientConnection;

    public RemoteView(Player player, ClientInterface c) throws RemoteException {
        super(player);
        this.clientConnection = c;
        clientConnection.addObserver(new MessageReceiver());
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