package client;

import client.network.Client;
import server.network.ServerInterface;

import java.rmi.*;

/**
 * The type Test app client rmi.
 */
public class TestAppClientRMI {

    /**
     * Main.
     *
     * @param arg the arg
     */
    public static void main(String arg[])
    {
        try
        {
            ServerInterface serverInterface = (ServerInterface)Naming.lookup("rmi://localhost:1900" + "/myShelfie");

            String string = "Test RMI string from client to server";

            Client client = new Client(serverInterface, true);
            serverInterface.testSend(string);
        }
        catch (Exception ea)
        {
            System.out.println("Client Error: " + ea);
        }
    }
}
