package client;

import network.Messages.Message;

public interface UI {

    void update();

    void setActive();

    void printConnectionMessage(Message message);
}
