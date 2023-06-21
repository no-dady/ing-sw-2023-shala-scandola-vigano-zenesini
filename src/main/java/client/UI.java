package client;

import util.Messages.Message;
public interface UI {

    void update();

    void setActive();

    void printConnectionMessage(Message message);

}
