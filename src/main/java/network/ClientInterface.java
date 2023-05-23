package network;

import server.model.Board;
import server.model.Bookshelf;
import server.model.Tile;

import java.rmi.*;

/**
 * The interface Client.
 */
public interface ClientInterface extends Remote
{
    /**
     * Send shelf.
     *
     * @param shelf the shelf
     * @throws RemoteException the remote exception
     */
//Send the shelf from server to client
    public void sendShelf(Tile[][] shelf) throws RemoteException;

    /**
     * Send board.
     *
     * @param board the board
     * @throws RemoteException the remote exception
     */
//Send the Board from server to client
    public void sendBoard(Board board) throws RemoteException;

    /**
     * Send bookshelf.
     *
     * @param bookshelf the bookshelf
     * @throws RemoteException the remote exception
     */
//Send the Bookshelf from server to client
    public void sendBookshelf(Bookshelf bookshelf) throws RemoteException;

    /**
     * Test send.
     *
     * @param string the string
     * @throws RemoteException the remote exception
     */
//Test function
    public void testSend(String string) throws RemoteException;
}
