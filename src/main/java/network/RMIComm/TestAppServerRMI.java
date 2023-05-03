package network.RMIComm;

import network.ServerImpl;
import network.Client;

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
            ServerImpl obj = new ServerImpl();

            LocateRegistry.createRegistry(1900);

            Naming.rebind("rmi://localhost:1900" + "/myShelfie", obj);
            Client client = null;
            while (client == null)
            {
                client = obj.getClient();
            }
            System.out.println("Uscito");
            client.testSend("Test RMI string from server to client");
        }
        catch (Exception ea)
        {
            System.out.println(ea);
        }
    }
}
