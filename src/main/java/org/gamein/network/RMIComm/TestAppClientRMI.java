package org.gamein.network.RMIComm;

import org.gamein.network.ClientImpl;
import org.gamein.network.Server;

import java.rmi.*;

public class TestAppClientRMI {

    public static void main(String arg[])
    {
        try
        {
            Server server = (Server)Naming.lookup("rmi://localhost:1900" + "/myShelfie");

            String string = "Test RMI string from client to server";

            ClientImpl client = new ClientImpl(server);
            server.testSend(string);
        }
        catch (Exception ea)
        {
            System.out.println("Client Error: " + ea);
        }
    }
}
