package it.polimi;

import java.io.IOException;
import java.util.Arrays;

import it.polimi.client.Client;

/**
 * <p>ClientApp class.</p>
 *
 * @author daniel
 * @version $Id: $Id
 */
public class ClientApp {

    /**
     * <p>printHelper.</p>
     */
    public static void printHelper() {
        System.out.println("SYNTAX:");
        System.out.println("\tjava -jar CLIENT_GC49.jar [OPTION]");
        System.out.println("OPTIONS:");
        System.out.println("\t--cli\t Starts it.polimi.client in command line interface");
        System.out.println("\t--gui\t Starts it.polimi.client in gui mode");
    }
    /**
     * <p>main.</p>
     *
     * @param args an array of {@link java.lang.String} objects
     */
    public static void main(String[] args) {
        if(args.length != 1 || (!Arrays.asList(args).contains("--cli") && !Arrays.asList(args).contains("--gui"))) {
            printHelper();
            System.exit(0);
        }

        boolean ui = !Arrays.asList(args).contains("--cli");

        try {
            Client client = new Client(ui);
            client.run();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            System.exit(-1);
        }

        System.exit(0);
    }
}
