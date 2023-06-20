package client.network;

public enum State {
    setNick,
    SetPlayersNum,
    NotMyTurn,
    MyTurn,
    WaitingForResponse,
    WaitingStart
}