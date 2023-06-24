package util.Messages;

import client.Client;
import client.network.ClientInterface;

import java.rmi.RemoteException;
import java.util.Set;

public class DisconnectMessage extends ConnectionMessage implements Message {
    public static final String className = "DisconnectMessage";
    public DisconnectMessage(Set<String> playersName, String playerName) {
        super(playersName, playerName);
    }
    @Override
    public void handleMessage(Client client) throws RemoteException {
        client.getUI().printConnectionMessage(this);
    }

    @Override
    public String toString() {
        String x="";
        x+="AGGIORNAMENTO: "+super.getPlayerName()+" si è disconnesso dalla stanza!\n";
        x+="Giocatori attualmente collegati\n";
        int i=1;
        for (String name: super.getPlayersName()){
            x+="\t"+i+". "+name+"\n";
            i++;
        }
        return x;
    }

    @Override
    public String getName() {
        return className;
    }
}
