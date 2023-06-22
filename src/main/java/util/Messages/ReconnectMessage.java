package util.Messages;

import client.Client;
import client.network.ClientInterface;

import java.rmi.RemoteException;
import java.util.Set;

public class ReconnectMessage extends ConnectionMessage implements Message {
    public static final String className = "ReconnectMessage";

    public ReconnectMessage(Set<String> playersName, String playerName) {
        super(playersName, playerName);
    }

    @Override
    public void handleMessage(ClientInterface client) throws RemoteException {
        client.getUI().printConnectionMessage(this);
    }

    @Override
    public String getName() {
        return className;
    }


    @Override
    public String toString() {
        String x="";
        x+="AGGIORNAMENTO: "+super.getPlayerName()+" si è riconnesso nella stanza!\n";
        x+="Giocatori attualmente collegati\n";
        int i=1;
        for (String name: super.getPlayersName()){
            x+="\t"+i+". "+name+"\n";
            i++;
        }
        return x;
    }
}
