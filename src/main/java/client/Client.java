package client;

import network.RMIClientClass;

public class Client extends RMIClientClass {
    private UI gui = null;
    private UI tui = null;

    public UI getUI(){
        if(gui==null)
            return tui;
        return gui;
    }
}
