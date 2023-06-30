package it.polimi.client;

import java.rmi.RemoteException;

public interface UI {

    void update();

    void printServerMessage(String message);

}
