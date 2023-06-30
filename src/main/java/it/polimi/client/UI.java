package it.polimi.client;

import java.rmi.RemoteException;

public interface UI {

    void update() throws RemoteException;

    void printServerMessage(String message);

}
