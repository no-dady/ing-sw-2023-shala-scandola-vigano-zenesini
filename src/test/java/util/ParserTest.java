package util;

import client.network.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.model.Bookshelf;
import server.model.Game;
import server.model.PersonalGoalCard;
import server.model.Player;
import util.Messages.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
    List<Message> messageList = new ArrayList<>();
    List<String> parsedList = new ArrayList<>();
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
    }

    @Test
    void fromJson() {
    }

    @Test
    void toJson() {
        for(Message msg : messageList) {
            System.out.println(Parser.toJson(msg, Message.class));
        }

    }
}