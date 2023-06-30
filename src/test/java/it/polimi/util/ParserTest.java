package it.polimi.util;

import it.polimi.client.network.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.polimi.server.model.Game;
import it.polimi.server.model.Player;
import it.polimi.util.Messages.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
    Queue<Message> messageList = new LinkedList<>();
    Queue<String> parsedList = new LinkedList<>();
    Set<Player> players = new HashSet<>();

    @BeforeEach
    void setUp() {
        messageList.add(new GameMessage(0));
        messageList.add(new ConnectionMessage(new HashSet<String>(), "testname"));
        messageList.add(new InitialMessage(new Game()));
        messageList.add(new DisconnectMessage(new HashSet<String>(),"testname"));
        messageList.add(new JoinedMessage("testname", 0));
        messageList.add(new CurrentPlayerMessage("testname"));
        messageList.add(new StateMessage(State.WAIT));
        for(Message msg : messageList) {
            parsedList.add(Parser.toJson(msg, Message.class));
        }
    }

    @Test
    void fromJson() {
        assertInstanceOf(GameMessage.class, Parser.fromJson(parsedList.remove(), Message.class));
        assertInstanceOf(ConnectionMessage.class, Parser.fromJson(parsedList.remove(), Message.class));
        assertInstanceOf(InitialMessage.class, Parser.fromJson(parsedList.remove(), Message.class));
        assertInstanceOf(DisconnectMessage.class, Parser.fromJson(parsedList.remove(), Message.class));
        assertInstanceOf(JoinedMessage.class, Parser.fromJson(parsedList.remove(), Message.class));
        assertInstanceOf(CurrentPlayerMessage.class, Parser.fromJson(parsedList.remove(), Message.class));
        assertInstanceOf(StateMessage.class, Parser.fromJson(parsedList.remove(), Message.class));
    }

    @Test
    void toJson() {
        for(Message msg : messageList) {
            System.out.println(Parser.toJson(msg, Message.class));
        }

    }
}