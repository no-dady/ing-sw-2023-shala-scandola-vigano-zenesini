package client;

import network.Message;

public interface UI {

    void update();

    void setActive();

    void printConnectionMessage(Message message);
}
