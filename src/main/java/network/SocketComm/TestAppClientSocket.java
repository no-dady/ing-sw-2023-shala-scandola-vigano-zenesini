package network.SocketComm;

import network.Client;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Scanner;

/**
 * The type Test app client socket.
 */
public class TestAppClientSocket {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws RemoteException the remote exception
     */
    public static void main(String[] args) throws RemoteException
    {
        //To send the info you have to call the ServerStub's function on client-side
        new Thread(() -> {
            ServerStub serverStub = new ServerStub("localhost", 1234);
            try {
                Client client = new Client(serverStub, false);
                System.out.println("Inserisci una username:");
                Scanner scanner = new Scanner(System.in);
                String nickName = scanner.next();
                serverStub.register(client, nickName);
                serverStub.testSend("Test Socket string from client to server");
                while (true) {
                    try {
                        serverStub.receive(client);
                    } catch (IOException e)
                    {
                        System.err.println("Cannot receive from server. Stopping...");
                        try {
                            serverStub.close();
                        } catch (RemoteException ex) {
                            System.err.println("Cannot close connection with server. Halting...");
                        }
                        System.exit(1);
                    }
                }
            } catch (RemoteException e) { System.out.println(e); }
        }).start();
    }
}
