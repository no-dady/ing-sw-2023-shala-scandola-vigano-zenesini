package org.gamein.network.RMIComm;

import org.gamein.network.ServerImpl;

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
        }
        catch (Exception ea)
        {
            System.out.println(ea);
        }
    }
}
