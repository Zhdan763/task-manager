package server.util;

public class ClientIdGenerator {
    private static ClientIdGenerator clientIdGenerator;
    private int counter;

    private ClientIdGenerator() {
        this.counter = 0;
    }

    public static synchronized ClientIdGenerator getInstance() {
        if (clientIdGenerator == null) {
            clientIdGenerator = new ClientIdGenerator();
        }
        return clientIdGenerator;
    }

    public int getNextClientId() {
        return counter++;
    }
}
