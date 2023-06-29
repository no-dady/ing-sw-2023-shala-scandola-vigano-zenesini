package it.polimi.client;

import java.rmi.RemoteException;

public interface UI {

    void update() throws RemoteException;

    void setActive();

    void printServerMessage(String message);

}
