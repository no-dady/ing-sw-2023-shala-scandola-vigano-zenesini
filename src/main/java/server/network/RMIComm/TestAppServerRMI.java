package server.network.RMIComm;

import client.network.ClientInterface;
import server.network.Server;

import java.rmi.*;
import java.rmi.registry.*;

/**
 * The type Test app server rmi.
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
            Server obj = new Server(true);

            LocateRegistry.createRegistry(1900);

            Naming.rebind("rmi://localhost:1900" + "/myShelfie", obj);
            ClientInterface clientInterface = null;
            while (clientInterface == null)
            {
                clientInterface = obj.getClient();
            }
            System.out.println("Uscito");
            clientInterface.testSend("Test RMI string from server to client");
        }
        catch (Exception ea)
        {
            System.out.println(ea);
        }
    }
}
