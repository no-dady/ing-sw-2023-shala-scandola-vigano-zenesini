package org.gamein.network.RMIComm;

import java.rmi.*;
import java.rmi.registry.*;

public class RMIServerClass {
    public static void main(String arg[])
    {
        try
        {
            RMIServerObject obj = new RMIServerObject();

            LocateRegistry.createRegistry(1900);

            Naming.rebind("rmi://localhost:1900" + "/myShelfie", obj);
        }
        catch (Exception ea)
        {
            System.out.println(ea);
        }
    }
}
