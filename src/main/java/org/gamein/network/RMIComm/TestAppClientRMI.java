package org.gamein.network.RMIComm;

import org.gamein.network.Server;

import java.rmi.*;

public class TestAppClientRMI {

    public static void main(String arg[])
    {
        try
        {
            Server access = (Server)Naming.lookup("rmi://localhost:1900" + "/myShelfie");

            String string = "Ciao";

            access.testSend(string);
        }
        catch (Exception ea)
        {
            System.out.println("Client Error: " + ea);
        }
    }
}
