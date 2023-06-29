package it.polimi.util;

import it.polimi.client.network.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.polimi.server.model.Bookshelf;
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
        messageList.add(new AskSetupMessage());
        messageList.add(new GameMessage(0));
        messageList.add(new BookshelfMessage(0, new Bookshelf()));
        messageList.add(new LastMessage());
        messageList.add(new ConnectionMessage(new HashSet<String>(), "testname"));
        messageList.add(new InitialMessage(new Game()));
        messageList.add(new SetupMessage("testname", 2));
        messageList.add(new ErrorMessage("Test Message"));
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
        assertInstanceOf(AskSetupMessage.class, Parser.fromJson(parsedList.remove(), Message.class));
        assertInstanceOf(GameMessage.class, Parser.fromJson(parsedList.remove(), Message.class));
        assertInstanceOf(BookshelfMessage.class, Parser.fromJson(parsedList.remove(), Message.class));
        assertInstanceOf(LastMessage.class, Parser.fromJson(parsedList.remove(), Message.class));
        assertInstanceOf(ConnectionMessage.class, Parser.fromJson(parsedList.remove(), Message.class));
        assertInstanceOf(InitialMessage.class, Parser.fromJson(parsedList.remove(), Message.class));
        assertInstanceOf(SetupMessage.class, Parser.fromJson(parsedList.remove(), Message.class));
        assertInstanceOf(ErrorMessage.class, Parser.fromJson(parsedList.remove(), Message.class));
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