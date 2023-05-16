package network.RMIComm;

import network.ClientImpl;
import network.Server;

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
            Server server = (Server)Naming.lookup("rmi://localhost:1900" + "/myShelfie");

            String string = "Test RMI string from client to server";

            ClientImpl client = new ClientImpl(server, true);
            server.testSend(string);
        }
        catch (Exception ea)
        {
            System.out.println("Client Error: " + ea);
        }
    }
}
