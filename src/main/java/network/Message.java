package network;

public interface Message {

    void handleMessage(RMIClientClass rmiClient);

    String getName();
}
