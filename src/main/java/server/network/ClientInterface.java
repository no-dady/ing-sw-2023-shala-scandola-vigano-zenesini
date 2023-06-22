package network;

import observer.Observer;


/**
 * The interface Client.
 */
public interface ClientInterface
{
    void closeConnection();

    void addObserver(Observer<String> observer);

    void send(String json);
}
