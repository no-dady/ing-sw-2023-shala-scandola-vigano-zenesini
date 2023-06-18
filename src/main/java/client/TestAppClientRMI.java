package client;

import client.network.ClientHandler;
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

            //String string = "Test RMI string from client to server";

            ClientHandler client = new ClientHandler("rmi://localhost", 1900);
            //serverInterface.send(string);
        }
        catch (Exception ea)
        {
            System.out.println("Client Error: " + ea);
        }
    }
}
