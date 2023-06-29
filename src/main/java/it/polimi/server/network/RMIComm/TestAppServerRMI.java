package it.polimi.server.network.RMIComm;

import it.polimi.server.network.Server;

import java.rmi.*;
import java.rmi.registry.*;

/**
 * The type Test app it.polimi.server rmi.
 */
public class TestAppServerRMI {
    /**
     * Main.
     *
     * @param arg the arg
     */
    public static void main(String arg[])
    {
        try
        {
            Server obj = new Server(1900, 1273);

            LocateRegistry.createRegistry(1900);

            Naming.rebind("rmi://localhost:1900" + "/myShelfie", obj);
        }
        catch (Exception ea)
        {
            System.out.println(ea);
        }
    }
}
