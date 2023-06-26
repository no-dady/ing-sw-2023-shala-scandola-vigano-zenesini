package client;

import client.network.State;
import util.Messages.Message;

import java.rmi.RemoteException;

public interface UI {

    void update() throws RemoteException;

    void setActive();

    void printConnectionMessage(Message message);

}
