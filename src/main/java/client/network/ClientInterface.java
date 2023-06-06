package client.network;

import moves.Move;

import java.io.IOException;

public interface ClientInterface {

    void init();
    void sendMove(Move move);
    void sendSetupper();
    void close() throws IOException;
}
