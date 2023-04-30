package network;

import server.model.Tile;
import server.model.TileType;

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

    public Tile[][] testStrangeObj(Tile[][] shelf)
    {
        for(int i = 5; i >= 0; i--) {
            for(int j = 0; j < 5; j++) {
                System.out.print(shelf[i][j].getTileType() + "\t|\t");
            }
            System.out.println();
        }

        shelf[1][1] = new Tile(TileType.TROPHY, 56);
        return shelf;
    }
}
