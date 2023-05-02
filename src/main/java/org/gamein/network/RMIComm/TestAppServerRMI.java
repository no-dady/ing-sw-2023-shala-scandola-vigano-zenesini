package org.gamein.network.RMIComm;

import org.gamein.network.ServerImpl;
import org.gamein.network.Client;

import java.rmi.*;
import java.rmi.registry.*;

public class TestAppServerRMI {
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
