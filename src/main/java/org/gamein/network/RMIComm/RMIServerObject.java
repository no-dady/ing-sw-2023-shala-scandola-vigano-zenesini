package org.gamein.network.RMIComm;

import org.gamein.model.Board;
import org.gamein.model.Bookshelf;
import org.gamein.model.Tile;

import java.rmi.*;
import java.rmi.server.*;

public class RMIServerObject extends UnicastRemoteObject implements RMIServerInterface {
    RMIServerObject() throws RemoteException {
        super();
    }

    public String send(String string) throws RemoteException {
        System.out.println(string);
        char ch;
        String nstr = "";
        for (int i=0; i<string.length(); i++)
        {
            ch= string.charAt(i);
            nstr= ch+nstr;
        }
        return nstr;
    }

    public void sendShelf(Tile[][] shelf)
    {
        for(int i = 5; i >= 0; i--) {
            for(int j = 0; j < 5; j++) {
                System.out.print(shelf[i][j].getTileType() + "\t|\t");
            }
            System.out.println();
        }
    }

    public void sendBoard(Board board) throws RemoteException
    {

    }

    public void sendBookshelf(Bookshelf bookshelf) throws RemoteException
    {

    }
}
