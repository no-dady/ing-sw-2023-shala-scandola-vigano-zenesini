package network;

public interface Message {

    void handleMessage(Client client);

    String getName();
}
